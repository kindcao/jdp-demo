package com.netmonitor.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.action.BaseAction;
import com.netmonitor.snmp.SnmpRequest;
import com.netmonitor.task.ChartTimer;
import com.netmonitor.task.ChartTimerTask;

/**
 * @author Kind Cao
 * @version $Rev$, Dec 29, 2011 3:39:14 PM
 */
public class NetMonitor extends BaseAction {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public String showNetMonitor() throws Exception {
        SnmpRequest req = new SnmpRequest("udp:127.0.0.1/161");
        req.setCommunity("public");
        ChartTimer ct = new ChartTimer(new ChartTimerTask(req, 5 * 1000));
        ct.start();
        return SUCCESS;
    }
}
