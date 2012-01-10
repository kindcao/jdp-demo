package com.netmonitor.task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netmonitor.common.Constants;
import com.netmonitor.snmp.IfEntry;
import com.netmonitor.snmp.SnmpRequest;
import com.netmonitor.util.ChartInfo;
import com.netmonitor.util.ChartUtil;
import com.netmonitor.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 4, 2012 10:59:16 AM
 */
public class ChartTimerTask2 extends AbstractChartTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private ConcurrentMap<String, TimeSeriesCollection> tscMap = new ConcurrentHashMap<String, TimeSeriesCollection>();

    public ChartTimerTask2(SnmpRequest sr, long period) {
        super(sr, period);
    }

    public ChartTimerTask2(SnmpRequest sr, String subOID, long period) {
        super(sr, subOID, period);
    }

    public ChartTimerTask2(SnmpRequest sr) {
        super(sr);
    }

    @Override
    public void createChart(String _subOID) {
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
        IfEntry entryIn = (dataMap.get(Constants.IFINOCTETS + _subOID));
        IfEntry entryOut = (dataMap.get(Constants.IFOUTOCTETS + _subOID));
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
            entryIn.setStrAvgIfInOctets(Utils.fmtData(entryIn.getTotalIfInOctets() / totalTime));
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
            entryOut.setStrAvgIfOutOctets(Utils.fmtData(entryOut.getTotalIfOutOctets() / totalTime));
            entryOut.setStrTotalIfOutOctets(Utils.fmtData(entryOut.getTotalIfOutOctets()));
            //
            StringBuilder sb = new StringBuilder("Out");
            sb.append("    Now: " + Utils.fmtData(inRate));
            sb.append("    Avg: " + entryOut.getStrAvgIfOutOctets());
            sb.append("    Total: " + entryOut.getStrTotalIfOutOctets());
            outSeries.setKey(sb.toString());
        }

        //
        IfEntry entryDesc = dataMap.get(Constants.IFDESCR + _subOID);
        ci.setTitle(entryDesc.getIfDescr());
        // ci.setTitle("");
        ci.setSubTitle("");
        ci.setYName(new String[] { "In/Out (" + Utils.getUnit(exponent) + "/s)" });

        //
        ci.setSaveFilepath(getSaveChartImgPath(_subOID));
        ChartUtil.writeChartAsPNG(ci, ChartUtil.createChart(ci, datasets));;
    }
    //
    // public String getSaveChartImgPath(String oid) {
    // String chartPath = "d:/" + Constants.SUB_CHART_SAVE_PATH +
    // sr.getAddress() + "/" + oid;
    // return chartPath;
    // }

}
