package snmp.task;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import snmp.chart.ChartInfo;
import snmp.chart.ChartUtil;
import snmp.common.Constants;
import snmp.net.IfEntry;
import snmp.net.SnmpRequest;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:30:03 PM
 */
public class ChartTimerTask extends AbstractChartTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String[] yName = { "In (k/s)", "Out (k/s)" };

    private ConcurrentMap<String, TimeSeriesCollection[]> tscMap = new ConcurrentHashMap<String, TimeSeriesCollection[]>();

    public ChartTimerTask(SnmpRequest req, long period) {
        this(req, null, period);
    }

    public ChartTimerTask(SnmpRequest req, String subOID) {
        this(req, subOID, 1);
    }

    public ChartTimerTask(SnmpRequest sr, String subOID, long period) {
        super(sr, subOID, period);
    }

    public void createChart(String _subOID) {
        ChartInfo ci = new ChartInfo();
        String dsKey = sr.getAddress() + Constants.UNDERLINE + _subOID;
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
        ci.setTitle(entryDesc.getIfDescr());
        ci.setYName(yName);
        // In
        IfEntry entryIn = (dataMap.get(Constants.IFINOCTETS + _subOID));
        if (null != entryIn) {
            double inRate = (entryIn.getIfInOctets() - entryIn.getLastIfInOctets() * 1.0) / (getPeriod() / 1000) / 1024;
            datasets[0].getSeries(0).addOrUpdate(new Second(), inRate);
            logger.debug("ifInOctets=" + entryIn.getIfInOctets() + "\tlastIfInOctets=" + entryIn.getLastIfInOctets()
                    + "\tinRate=" + inRate);
            entryIn.setLastIfInOctets(entryIn.getIfInOctets());
        }

        // out
        IfEntry entryOut = (dataMap.get(Constants.IFOUTOCTETS + _subOID));
        if (null != entryOut) {
            double outRate = (entryOut.getIfOutOctets() - entryOut.getLastIfOutOctets() * 1.0) / (getPeriod() / 1000)
                    / 1024;
            datasets[1].getSeries(0).addOrUpdate(new Second(), outRate);
            logger.debug("ifOutOctets=" + entryOut.getIfOutOctets() + "\tlastIfOutOctets="
                    + entryOut.getLastIfOutOctets() + "\toutRate=" + outRate);
            entryOut.setLastIfOutOctets(entryOut.getIfOutOctets());
        }

        //
        ci.setSaveFilepath(getSaveChartImgPath(_subOID));
        ChartUtil.writeChartAsPNG(ci, ChartUtil.createChart(ci, datasets));;
    }

}
