package com.netmonitor.task;

import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netmonitor.common.Constants;
import com.netmonitor.snmp.IfEntry;
import com.netmonitor.snmp.SnmpRequest;
import com.netmonitor.util.ChartUtil;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:30:03 PM
 */
public class ChartTimerTask extends TimerTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String[] yName;

    private SnmpRequest req;

    private long period;

    private String subOID;

    private ChartUtil cu = new ChartUtil();

    private ConcurrentMap<String, IfEntry> dataMap = new ConcurrentHashMap<String, IfEntry>();

    private ConcurrentMap<String, TimeSeriesCollection[]> tscMap = new ConcurrentHashMap<String, TimeSeriesCollection[]>();

    public ChartTimerTask(SnmpRequest req, long period) {
        this(req, null, period);
    }

    public ChartTimerTask(SnmpRequest req, String subOID) {
        this(req, subOID, 1);
    }

    public ChartTimerTask(SnmpRequest req, String subOID, long period) {
        this.req = req;
        this.subOID = subOID;
        this.period = period;
        //
        yName = new String[Constants.CHAET_NUM];
        this.yName[0] = "In(B/s)";
        this.yName[1] = "Out(B/s)";
    }

    public void run() {
        //
        req.fetchData(dataMap);
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

    private void createChart(String _subOID) {
        String dsKey = req.getAddress() + Constants.UNDERLINE + _subOID;
        TimeSeriesCollection[] datasets = tscMap.get(dsKey);
        if (null == datasets) {
            datasets = new TimeSeriesCollection[Constants.CHAET_NUM];
            tscMap.put(dsKey, datasets);
            //
            TimeSeries inSeries = new TimeSeries("In");
            inSeries.setMaximumItemCount(30);
            datasets[0] = new TimeSeriesCollection(inSeries);
            //
            TimeSeries outSeries = new TimeSeries("Out");
            outSeries.setMaximumItemCount(30);
            datasets[1] = new TimeSeriesCollection(outSeries);
        }
        //
        IfEntry entryDesc = dataMap.get(Constants.IFDESCR + _subOID);
        cu.setTitle(entryDesc.getIfDescr());
        cu.setYName(yName);
        // In
        IfEntry entryIn = (dataMap.get(Constants.IFINOCTETS + _subOID));
        if (null != entryIn) {
            double inRate = (entryIn.getIfInOctets() - entryIn.getLastIfInOctets() * 1.0) / (period / 1000);
            datasets[0].getSeries(0).addOrUpdate(new Second(), inRate);
            logger.debug("ifInOctets=" + entryIn.getIfInOctets() + "\tlastIfInOctets=" + entryIn.getLastIfInOctets()
                    + "\tinRate=" + inRate);
            entryIn.setLastIfInOctets(entryIn.getIfInOctets());
        }

        // out
        IfEntry entryOut = (dataMap.get(Constants.IFOUTOCTETS + _subOID));
        if (null != entryOut) {
            double outRate = (entryOut.getIfOutOctets() - entryOut.getLastIfOutOctets() * 1.0) / (period / 1000);
            datasets[1].getSeries(0).addOrUpdate(new Second(), outRate);
            logger.debug("ifOutOctets=" + entryOut.getIfOutOctets() + "\tlastIfOutOctets="
                    + entryOut.getLastIfOutOctets() + "\toutRate=" + outRate);
            entryOut.setLastIfOutOctets(entryOut.getIfOutOctets());
        }

        //
        JFreeChart chart = cu.createChart(datasets);
        String chartPath = System.getProperty("webApp.root") + "/images/chart/" + req.getAddress() + "/" + _subOID
                + ".png";
        cu.writeChartAsPNG(chart, chartPath);
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public ConcurrentMap<String, IfEntry> getDataMap() {
        return dataMap;
    }
}
