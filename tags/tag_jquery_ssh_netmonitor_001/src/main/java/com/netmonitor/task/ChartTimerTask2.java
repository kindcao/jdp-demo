package com.netmonitor.task;

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
        ci.setHeight(200);
        String dsKey = sr.getAddress() + Constants.UNDERLINE + _subOID;
        TimeSeriesCollection datasets = tscMap.get(dsKey);
        if (null == datasets) {
            datasets = new TimeSeriesCollection();
            tscMap.put(dsKey, datasets);
            //
            TimeSeries inSeries = new TimeSeries("In");
            inSeries.setMaximumItemCount(30);
            datasets.addSeries(inSeries);
            //
            TimeSeries outSeries = new TimeSeries("Out");
            outSeries.setMaximumItemCount(30);
            datasets.addSeries(outSeries);
        }
        //
        IfEntry entryDesc = dataMap.get(Constants.IFDESCR + _subOID);
        // ci.setTitle(entryDesc.getIfDescr());
        ci.setTitle("");
        ci.setYName(new String[] { "In/Out" });
        // In
        IfEntry entryIn = (dataMap.get(Constants.IFINOCTETS + _subOID));
        if (null != entryIn) {
            double inRate = (entryIn.getIfInOctets() - entryIn.getLastIfInOctets() * 1.0) / (getPeriod() / 1000) / 1024;
            datasets.getSeries(0).addOrUpdate(new Second(), inRate);
            logger.debug("ifInOctets=" + entryIn.getIfInOctets() + "\tlastIfInOctets=" + entryIn.getLastIfInOctets()
                    + "\tinRate=" + inRate);
            entryIn.setLastIfInOctets(entryIn.getIfInOctets());
        }

        // out
        IfEntry entryOut = (dataMap.get(Constants.IFOUTOCTETS + _subOID));
        if (null != entryOut) {
            double outRate = (entryOut.getIfOutOctets() - entryOut.getLastIfOutOctets() * 1.0) / (getPeriod() / 1000)
                    / 1024;
            datasets.getSeries(1).addOrUpdate(new Second(), outRate);
            logger.debug("ifOutOctets=" + entryOut.getIfOutOctets() + "\tlastIfOutOctets="
                    + entryOut.getLastIfOutOctets() + "\toutRate=" + outRate);
            entryOut.setLastIfOutOctets(entryOut.getIfOutOctets());
        }

        //
        ci.setSaveFilepath(getSaveChartImgPath(_subOID));
        ChartUtil.writeChartAsPNG(ci, ChartUtil.createChart(ci, datasets));;
    }

}
