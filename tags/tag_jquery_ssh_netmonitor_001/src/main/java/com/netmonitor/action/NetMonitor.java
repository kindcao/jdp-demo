package com.netmonitor.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.base.action.BaseAction;
import com.base.json.JsonListResult;
import com.base.json.JsonValidateResult;
import com.netmonitor.common.Constants;
import com.netmonitor.snmp.IfEntry;
import com.netmonitor.snmp.SnmpRequest;
import com.netmonitor.task.ChartTimer;
import com.netmonitor.task.ChartTimerTask;

/**
 * @author Kind Cao
 * @version $Rev$, Dec 29, 2011 3:39:14 PM
 */

@SuppressWarnings("serial")
@Scope("session")
public class NetMonitor extends BaseAction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SnmpRequest sr;

    private ChartTimer ct;

    private long period = 5;

    private ConcurrentMap<String, IfEntry> dataMap;

    public String showNetMonitor() throws Exception {
        return SUCCESS;
    }

    public String inputSnmpPara() throws Exception {
        if (null != sr) {
            sr = new SnmpRequest();
            logger.info("create SnmpRequest instance");
        }
        return NONE;
    }

    public String start() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (null == sr) {
            logger.warn("SnmpRequest object is null");
            jvr.setErrors("No input snmp paras");
        }
        //
        if (StringUtils.isBlank(jvr.getErrors())) {
            jvr.setSuccess(true);
        }
        //
        if (jvr.isSuccess()) {
            ct = new ChartTimer(new ChartTimerTask(sr, period));
            ct.start();
            logger.warn("start ChartTimerTask[" + sr.getAddress() + "] ...");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String stop() throws Exception {
        if (null != ct) {
            ct.stop();
        }
        return NONE;
    }

    public String getDataList() throws Exception {
        if (null != ct) {
            JsonListResult jlr = new JsonListResult();
            dataMap = ct.getCtt().getDataMap();
            //
            List<IfEntry> list = new ArrayList<IfEntry>();
            for (Iterator<String> iterator = dataMap.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (key.startsWith(Constants.IFINOCTETS)) {
                    list.add(dataMap.get(key));
                }
            }
            jlr.setRows(list);
            jlr.setTotal(list.size());
            //
            Collections.sort(list, new Comparator<IfEntry>() {

                @Override
                public int compare(IfEntry o1, IfEntry o2) {
                    return Integer.valueOf(o1.getIfIndex()) - Integer.valueOf(o2.getIfIndex());
                }

            });
            responseJsonData(list);
        }
        return NONE;
    }

    public SnmpRequest getSr() {
        return sr;
    }

    public void setSr(SnmpRequest sr) {
        this.sr = sr;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        if (StringUtils.isNotBlank(period)) {
            this.period = Long.valueOf(period);
        }
        this.period *= 1000;
    }
}
