package snmp.task;

import java.util.Iterator;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import snmp.common.Config;
import snmp.common.Constants;
import snmp.net.IfEntry;
import snmp.net.SnmpRequest;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 4, 2012 9:53:35 AM
 */
public abstract class AbstractChartTask extends TimerTask {

    protected SnmpRequest sr;

    protected String subOID;

    private long period;

    protected ConcurrentMap<String, IfEntry> dataMap = new ConcurrentHashMap<String, IfEntry>();

    public AbstractChartTask(SnmpRequest sr) {
        this(sr, 1 * 1000);
    }

    public AbstractChartTask(SnmpRequest sr, long period) {
        this(sr, null, period);
    }

    public AbstractChartTask(SnmpRequest sr, String subOID, long period) {
        this.sr = sr;
        this.subOID = subOID;
        this.period = period;
    }

    public void run() {
        sr.fetchData(dataMap);
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

    public abstract void createChart(String _subOID);

    public String getSaveChartImgPath(String oid) {
        String chartPath = Config.getInstance().getValue("chart.img.dir").endsWith("/") ? Config.getInstance()
                .getValue("chart.img.dir") : Config.getInstance().getValue("chart.img.dir").concat("/")
                + sr.getAddress() + "/" + oid + ".png";
        return chartPath;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

}
