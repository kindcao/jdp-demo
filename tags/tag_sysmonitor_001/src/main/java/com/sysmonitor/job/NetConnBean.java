package com.sysmonitor.job;

/**
 * @author Kind Cao
 * @version $Rev: $, Feb 6, 2012 1:16:52 PM
 */
public class NetConnBean {

    private String hostAddress;

    private String hostName;

    private Integer times;

    public Integer getTimes() {
        return times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
}