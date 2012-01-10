package com.netmonitor.task;

import java.io.IOException;

import com.netmonitor.snmp.SnmpRequest;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 6, 2012 11:07:39 AM
 */
public class TestChartTimerTask2 {

    public static void main(String[] args) throws IOException {
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress("udp:127.0.0.1/161");
        sr.setCommunity("public");
        ChartTimer ct = new ChartTimer(new ChartTimerTask2(sr, 5 * 1000));
        ct.start();
    }
}
