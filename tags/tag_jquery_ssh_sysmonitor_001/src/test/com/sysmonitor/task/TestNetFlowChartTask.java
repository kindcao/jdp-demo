package com.sysmonitor.task;

import java.io.IOException;

import com.sysmonitor.snmp.SnmpRequest;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 6, 2012 11:07:39 AM
 */
public class TestNetFlowChartTask {

    public static void main(String[] args) throws IOException {
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress("udp:127.0.0.1/161");
        sr.setCommunity("public");
        ChartTimer ct = new ChartTimer(new NetFlowChartTask(sr, 5 * 1000));
        ct.start();
    }
}
