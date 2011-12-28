package snmp.demo;

import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:30:03 PM
 */
public class ChartTimerTask extends TimerTask {

    private String[] yName = new String[2];

    private TimeSeries inSeries;

    private TimeSeries outSeries;

    private SnmpRequest req;

    private long period;

    private String subOID;

    private ChartUtil cu;

    private ConcurrentMap<String, IfEntry> dataMap = new ConcurrentHashMap<String, IfEntry>();

    private TimeSeriesCollection[] datasets = new TimeSeriesCollection[2];

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
        this.yName[0] = "In (k/s)";
        this.yName[1] = "Out (k/s)";
    }

    public void run() {
        try {
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createChart(String _subOID) {
        //
        if (null == cu) {
            cu = new ChartUtil();
            //
            IfEntry entryDesc = dataMap.get(Constants.IFDESCR + _subOID);
            cu.setTitle(entryDesc.getIfDescr());
            cu.setYName(yName);
            //
            inSeries = new TimeSeries("In");
            inSeries.setMaximumItemCount(20);
            datasets[0] = new TimeSeriesCollection(inSeries);
            //
            outSeries = new TimeSeries("Out");
            outSeries.setMaximumItemCount(20);
            datasets[1] = new TimeSeriesCollection(outSeries);
        }
        // In
        IfEntry entryIn = (dataMap.get(Constants.IFINOCTETS + _subOID));
        if (null != entryIn) {
            double inRate = (entryIn.getIfInOctets() - entryIn.getLastIfInOctets() * 1.0) / (period / 1000) / 1024;
            inSeries.addOrUpdate(new Second(), inRate);
            System.out.println("ifInOctets=" + entryIn.getIfInOctets() + "\tlastIfInOctets="
                    + entryIn.getLastIfInOctets() + "\tinRate=" + inRate);
            entryIn.setLastIfInOctets(entryIn.getIfInOctets());
        }

        // out
        IfEntry entryOut = (dataMap.get(Constants.IFOUTOCTETS + _subOID));
        if (null != entryOut) {
            double outRate = (entryOut.getIfOutOctets() - entryOut.getLastIfOutOctets() * 1.0) / (period / 1000) / 1024;
            outSeries.addOrUpdate(new Second(), outRate);
            System.out.println("ifOutOctets=" + entryOut.getIfOutOctets() + "\tlastIfOutOctets="
                    + entryOut.getLastIfOutOctets() + "\toutRate=" + outRate);
            entryOut.setLastIfOutOctets(entryOut.getIfOutOctets());
        }

        //
        JFreeChart chart = cu.createChart(datasets);
        cu.saveAsFile(chart, "d:\\s-" + _subOID + ".png");
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
