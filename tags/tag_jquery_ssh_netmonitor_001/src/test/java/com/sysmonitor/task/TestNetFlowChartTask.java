package com.sysmonitor.task;

import com.sysmonitor.snmp.SnmpRequest;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 11, 2012 5:55:57 PM
 */
public class TestNetFlowChartTask {

    public static void main(String[] args) {
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress("udp:127.0.0.1/161");

        ChartTimer ct = new ChartTimer(new NetFlowChartTask(sr, 5 * 1000));
        ct.start();
    }
}
