package com.sysmonitor.task;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.sysmonitor.common.Constants;
import com.sysmonitor.snmp.IfEntry;
import com.sysmonitor.snmp.NetFlowTableListener;
import com.sysmonitor.snmp.SnmpRequest;
import com.sysmonitor.util.ChartInfo;
import com.sysmonitor.util.ChartUtil;
import com.sysmonitor.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 4, 2012 10:59:16 AM
 */
public class NetFlowChartTask extends AbstractChartTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String subOID;

    private Map<String, TimeSeriesCollection> tscMap = new ConcurrentHashMap<String, TimeSeriesCollection>();

    public NetFlowChartTask(SnmpRequest sr, long period) {
        super(sr, period);
    }

    public NetFlowChartTask(SnmpRequest sr, String subOID, long period) {
        super(sr, period);
        this.subOID = subOID;
    }

    public NetFlowChartTask(SnmpRequest sr) {
        super(sr);
    }

    public void run() {
        fetchData();
        // show all device chart
        if (null == subOID || subOID.trim().length() == 0) {
            for (Iterator<String> iterator = dataMap.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (key.startsWith(Constants.IFINDEX)) {
                    createChart(key.substring(key.lastIndexOf(Constants.DOT) + 1));
                }
            }
        } else {
            createChart(subOID);
        }
    }

    private void fetchData() {
        int ifNumber = sr.getIfNumber();
        logger.info("ifNumber = " + ifNumber);
        //
        if (ifNumber > 0) {
            sr.getVbs().clear();
            sr.getVbs().add(new VariableBinding(new OID(Constants.IFINDEX)));
            sr.getVbs().add(new VariableBinding(new OID(Constants.IFDESCR)));
            sr.getVbs().add(new VariableBinding(new OID(Constants.IFTYPE)));
            sr.getVbs().add(new VariableBinding(new OID(Constants.IFINOCTETS)));
            sr.getVbs().add(new VariableBinding(new OID(Constants.IFOUTOCTETS)));
            try {
                sr.table(new NetFlowTableListener(dataMap));
            } catch (IOException e) {
                logger.error("fetchData", e);
            } finally {
                sr.getVbs().clear();
            }
        }
    }

    private void createChart(String _subOID) {
        ChartInfo ci = new ChartInfo();
        String dsKey = sr.getAddress() + Constants.UNDERLINE + _subOID;
        TimeSeriesCollection datasets = tscMap.get(dsKey);
        if (null == datasets) {
            datasets = new TimeSeriesCollection();
            tscMap.put(dsKey, datasets);
            //
            TimeSeries inSeries = new TimeSeries("In");
            // inSeries.setMaximumItemCount(30);
            datasets.addSeries(inSeries);
            //
            TimeSeries outSeries = new TimeSeries("Out");
            // outSeries.setMaximumItemCount(30);
            datasets.addSeries(outSeries);
        }

        //
        int exponent = 0;
        double inRate = 0;
        double outRate = 0;
        IfEntry entryIn = (IfEntry) dataMap.get(Constants.IFINOCTETS + _subOID);
        IfEntry entryOut = (IfEntry) dataMap.get(Constants.IFOUTOCTETS + _subOID);
        if (null != entryIn) {
            inRate = (entryIn.getIfInOctets() - entryIn.getLastIfInOctets() * 1.0) / (getPeriod() / 1000);
            exponent = Math.max(exponent, Math.max(entryIn.getExponent(), Utils.getExponent(inRate)));
            entryIn.setExponent(exponent);
        }
        if (null != entryOut) {
            outRate = (entryOut.getIfOutOctets() - entryOut.getLastIfOutOctets() * 1.0) / (getPeriod() / 1000);
            exponent = Math.max(exponent, Math.max(entryOut.getExponent(), Utils.getExponent(outRate)));
            entryOut.setExponent(exponent);
        }

        //
        if (null != entryIn) {
            TimeSeries inSeries = datasets.getSeries(0);
            inSeries.addOrUpdate(new Second(), inRate / Math.pow(10, exponent));
            logger.debug("ifInOctets=" + entryIn.getIfInOctets() + "\tlastIfInOctets=" + entryIn.getLastIfInOctets()
                    + "\tinRate=" + inRate);
            entryIn.setTotalIfInOctets(entryIn.getTotalIfInOctets() + entryIn.getIfInOctets()
                    - entryIn.getLastIfInOctets());
            entryIn.setLastIfInOctets(entryIn.getIfInOctets());
            //
            Calendar cal = Calendar.getInstance();
            double totalTime = (cal.getTimeInMillis() - entryIn.getTotalTime());
            totalTime = totalTime == 0 ? 1 : totalTime;
            cal.clear();
            cal.setTimeInMillis((long) totalTime + cal.getTimeInMillis());
            entryIn.setStrTotalTime(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
            entryIn.setStrAvgIfInOctets(Utils.fmtData(entryIn.getTotalIfInOctets() / (totalTime / 1000)));
            entryIn.setStrTotalIfInOctets(Utils.fmtData(entryIn.getTotalIfInOctets()));
            //
            StringBuilder sb = new StringBuilder("In ");
            sb.append("    Now: " + Utils.fmtData(inRate));
            sb.append("    Avg: " + entryIn.getStrAvgIfInOctets());
            sb.append("    Total: " + entryIn.getStrTotalIfInOctets());
            inSeries.setKey(sb.toString());
        }
        if (null != entryOut) {
            TimeSeries outSeries = datasets.getSeries(1);
            outSeries.addOrUpdate(new Second(), outRate / Math.pow(10, exponent));
            logger.debug("ifOutOctets=" + entryOut.getIfOutOctets() + "\tlastIfOutOctets="
                    + entryOut.getLastIfOutOctets() + "\toutRate=" + outRate);
            entryOut.setTotalIfOutOctets(entryOut.getTotalIfOutOctets() + entryOut.getIfOutOctets()
                    - entryOut.getLastIfOutOctets());
            entryOut.setLastIfOutOctets(entryOut.getIfOutOctets());
            //
            Calendar cal = Calendar.getInstance();
            double totalTime = (cal.getTimeInMillis() - entryOut.getTotalTime());
            totalTime = totalTime == 0 ? 1 : totalTime;
            cal.clear();
            cal.setTimeInMillis((long) totalTime + cal.getTimeInMillis());
            entryOut.setStrTotalTime(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
            entryOut.setStrAvgIfOutOctets(Utils.fmtData(entryOut.getTotalIfOutOctets() / (totalTime / 1000)));
            entryOut.setStrTotalIfOutOctets(Utils.fmtData(entryOut.getTotalIfOutOctets()));
            //
            StringBuilder sb = new StringBuilder("Out");
            sb.append("    Now: " + Utils.fmtData(inRate));
            sb.append("    Avg: " + entryOut.getStrAvgIfOutOctets());
            sb.append("    Total: " + entryOut.getStrTotalIfOutOctets());
            outSeries.setKey(sb.toString());
        }

        //
        IfEntry entryDesc = (IfEntry) dataMap.get(Constants.IFDESCR + _subOID);
        ci.setTitle(entryDesc.getIfDescr());
        // ci.setTitle("");
        ci.setSubTitle("");
        ci.setYName(new String[] { "In/Out (" + Utils.getUnit(exponent) + "/s)" });

        //
        ci.setSaveFilepath(getSaveChartImgPath(_subOID));
        ChartUtil.writeChartAsPNG(ci, ChartUtil.createChart(ci, datasets));;
    }

    // public String getSaveChartImgPath(String oid) {
    // String chartPath = "d:/" + Constants.SUB_CHART_SAVE_PATH +
    // sr.getAddress() + "/" + oid;
    // return chartPath;
    // }

}
