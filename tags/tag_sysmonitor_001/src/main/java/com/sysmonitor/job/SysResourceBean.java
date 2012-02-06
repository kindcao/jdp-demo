package com.sysmonitor.job;

/**
 * @author Kind Cao
 * @version $Rev: $, Feb 6, 2012 1:14:44 PM
 */
public class SysResourceBean {

    private long totalCapacity = -1;

    private long freeCapacity = -1;

    private double useCpuRate = -1;

    // for json result
    private String hostName;

    private String hostAddress;

    private String remarks;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getUseCpuRate() {
        return useCpuRate;
    }

    public void setUseCpuRate(double useCpuRate) {
        this.useCpuRate = useCpuRate;
    }

    public long getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(long totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public long getFreeCapacity() {
        return freeCapacity;
    }

    public void setFreeCapacity(long freeCapacity) {
        this.freeCapacity = freeCapacity;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostAddress() {
        return hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }
}
