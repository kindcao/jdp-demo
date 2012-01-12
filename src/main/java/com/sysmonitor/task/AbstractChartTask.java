package com.sysmonitor.task;

import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import com.sysmonitor.common.Constants;
import com.sysmonitor.snmp.IfEntry;
import com.sysmonitor.snmp.SnmpRequest;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 4, 2012 9:53:35 AM
 */
public abstract class AbstractChartTask extends TimerTask {

    protected SnmpRequest sr;

    private long period;

    protected Map<String, IfEntry> dataMap = new ConcurrentHashMap<String, IfEntry>();

    public AbstractChartTask(SnmpRequest sr) {
        this(sr, 1 * 1000);
    }

    public AbstractChartTask(SnmpRequest sr, long period) {
        this.sr = sr;
        this.period = period;
    }

    public String getSaveChartImgPath(String oid) {
        String chartPath = System.getProperty("webApp.root") + "/" + Constants.SUB_CHART_SAVE_PATH + sr.getAddress()
                + "/" + oid;
        return chartPath;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }

    public final Map<String, IfEntry> getDataMap() {
        return dataMap;
    }
}
