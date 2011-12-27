package snmp.demo;

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

    private TimeSeriesCollection[] datasets = new TimeSeriesCollection[2];

    private String[] yName = new String[2];

    private TimeSeries inSeries;

    private TimeSeries outSeries;

    private SnmpRequest req;

    private long period;

    private String subOID;

    private ConcurrentMap<String, IfEntry> dataMap = new ConcurrentHashMap<String, IfEntry>();

    private ChartUtil tsc;

    private IfEntry lastIfEntry = new IfEntry();

    public ChartTimerTask(SnmpRequest req, String subOID) {
        this(req, subOID, 1);
    }

    public ChartTimerTask(SnmpRequest req, String subOID, long period) {
        this.req = req;
        this.subOID = subOID;
        this.period = period;
        this.lastIfEntry.setIfInOctets(-1);
        this.lastIfEntry.setIfOutOctets(-1);
    }

    public void run() {
        try {
            dataMap.clear();
            req.fetchData(dataMap);
            //
            if (null == tsc) {
                yName[0] = "入站 (bytes/second)";
                yName[1] = "出站 (bytes/second)";
                IfEntry entryDesc = dataMap.get(Constants.IFDESCR + "." + subOID);
                tsc = new ChartUtil(entryDesc.getIfDescr(), "", "", yName);
                //
                inSeries = new TimeSeries("入站网络流量", Second.class);
                datasets[0] = new TimeSeriesCollection(inSeries);
                //
                outSeries = new TimeSeries("出站网络流量", Second.class);
                datasets[1] = new TimeSeriesCollection(outSeries);
            }
            // In
            IfEntry entryIn = (dataMap.get(Constants.IFINOCTETS + "." + subOID));
            if (null != entryIn) {
                if (lastIfEntry.getIfInOctets() == -1) {
                    lastIfEntry.setIfInOctets(entryIn.getIfInOctets());
                }
                double inRate = (entryIn.getIfInOctets() - lastIfEntry.getIfInOctets()) * 1000.00 / period;
                inSeries.add(new Second(), inRate);
                System.out.println(entryIn.getIfInOctets() + " in  " + lastIfEntry.getIfInOctets() + " " + inRate);
                lastIfEntry.setIfInOctets(entryIn.getIfInOctets());
            }

            // out
            IfEntry entryOut = (dataMap.get(Constants.IFOUTOCTETS + "." + subOID));
            if (null != entryOut) {
                if (lastIfEntry.getIfOutOctets() == -1) {
                    lastIfEntry.setIfOutOctets(entryOut.getIfOutOctets());
                }
                double outRate = (entryOut.getIfOutOctets() - lastIfEntry.getIfOutOctets()) * 1000.00 / period;
                outSeries.add(new Second(), outRate);
                System.out.println(entryOut.getIfOutOctets() + " out " + lastIfEntry.getIfOutOctets() + " " + outRate);
                lastIfEntry.setIfOutOctets(entryOut.getIfOutOctets());
            }

            //
            JFreeChart chart = tsc.createChart(datasets);
            tsc.saveAsFile(chart, "d:\\s-" + subOID + ".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
