package com.sysmonitor.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.base.action.BaseAction;
import com.base.json.JsonListResult;
import com.base.json.JsonValidateResult;
import com.sysmonitor.common.Constants;
import com.sysmonitor.snmp.IfEntry;
import com.sysmonitor.snmp.SnmpRequest;
import com.sysmonitor.task.ChartTimer;
import com.sysmonitor.task.NetFlowChartTask;

/**
 * @author Kind Cao
 * @version $Rev$, Dec 29, 2011 3:39:14 PM
 */

@SuppressWarnings("serial")
@Scope("session")
public class NetFlowAction extends BaseAction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SnmpRequest sr;

    private ChartTimer ct;

    private long period = 5000;

    private Map<String, IfEntry> dataMap;

    public String showNetFlow() throws Exception {
        return SUCCESS;
    }

    public String inputSnmpPara() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        logger.info("create SnmpRequest instance");
        jvr.setSuccess(true);
        responseJsonData(jvr);
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
            if (null != ct) {
                ct.stop();
            }
            ct = new ChartTimer(new NetFlowChartTask(sr, period));
            ct.start();
            logger.info("start NetFlowChartTask[" + sr.getAddress() + "] ...");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String stop() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (null != ct) {
            ct.stop();
            ct.getChartTask().getDataMap().clear();
            FileUtils.deleteDirectory(new File(System.getProperty("webApp.root") + Constants.SUB_CHART_SAVE_PATH
                    + sr.getAddress()));
            jvr.setSuccess(true);
            logger.info("stop NetFlowChartTask[" + sr.getAddress() + "]");
        } else {
            jvr.setErrors("ChartTimer object is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String getDataList() throws Exception {
        List<IfEntry> list = new ArrayList<IfEntry>();
        JsonListResult jlr = new JsonListResult();
        jlr.setRows(list);
        if (null != ct) {
            dataMap = ct.getChartTask().getDataMap();
            //      
            IfEntry entry = null;
            for (Iterator<String> iterator = dataMap.keySet().iterator(); iterator.hasNext();) {
                String key = (String) iterator.next();
                if (key.startsWith(Constants.IFINDEX)) {
                    entry = new IfEntry();
                    entry.setIfIndex(dataMap.get(key).getIfIndex());
                    entry.setIfDescr(dataMap.get(Constants.IFDESCR + entry.getIfIndex()).getIfDescr());
                    entry.setStrTotalIfInOctets(dataMap.get(Constants.IFINOCTETS + entry.getIfIndex())
                            .getStrTotalIfInOctets());
                    entry.setStrTotalIfOutOctets(dataMap.get(Constants.IFOUTOCTETS + entry.getIfIndex())
                            .getStrTotalIfOutOctets());
                    entry.setStrAvgIfInOctets(dataMap.get(Constants.IFINOCTETS + entry.getIfIndex())
                            .getStrAvgIfInOctets());
                    entry.setStrAvgIfOutOctets(dataMap.get(Constants.IFOUTOCTETS + entry.getIfIndex())
                            .getStrAvgIfOutOctets());
                    //
                    String strTotalTimeIn = dataMap.get(Constants.IFINOCTETS + entry.getIfIndex()).getStrTotalTime();
                    String strTotalTimeOut = dataMap.get(Constants.IFOUTOCTETS + entry.getIfIndex()).getStrTotalTime();
                    String strTotalTime = strTotalTimeIn.compareTo(strTotalTimeOut) > 0 ? strTotalTimeIn
                            : strTotalTimeOut;
                    entry.setStrTotalTime(strTotalTime);
                    entry.setChartPath(Constants.SUB_CHART_SAVE_PATH + sr.getAddress() + "/" + entry.getIfIndex()
                            + ".png");
                    list.add(entry);
                }
            }
            //         
            Collections.sort(list, new Comparator<IfEntry>() {

                @Override
                public int compare(IfEntry o1, IfEntry o2) {
                    return Integer.valueOf(o1.getIfIndex()) - Integer.valueOf(o2.getIfIndex());
                }
            });
            // 
            jlr.setTotal(list.size());
            if (list.size() > getRows()) {
                int fromIndex = (getPage() - 1) * getRows();
                int toIndex = getPage() * getRows();
                if (toIndex > list.size()) {
                    toIndex = list.size();
                }
                //
                list = list.subList(fromIndex, toIndex);
                jlr.setRows(list);
            }
        }
        responseJsonData(jlr);
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
            this.period *= 1000;
        }
    }
}
