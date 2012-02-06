package com.sysmonitor.job;

/**
 * @author Kind Cao
 * @version $Rev: $, Feb 6, 2012 1:18:33 PM
 */
public class NetFlowBean {

    private String hostAddress;

    private String hostName;

    private String remarks;

    private Long inOctets;

    private Long outOctets;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getInOctets() {
        return inOctets;
    }

    public void setInOctets(Long inOctets) {
        this.inOctets = inOctets;
    }

    public Long getOutOctets() {
        return outOctets;
    }

    public void setOutOctets(Long outOctets) {
        this.outOctets = outOctets;
    }

}
