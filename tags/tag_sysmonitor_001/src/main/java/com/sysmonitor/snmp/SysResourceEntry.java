package com.sysmonitor.snmp;

/**
 * @author Kind Cao
 * @version $Rev: $, Feb 3, 2012 3:42:39 PM
 */
public class SysResourceEntry {

    private String desci;

    private long totalDisk = -1;

    private long freeDisk = -1;

    private long totalRam = -1;

    private long freeRam = -1;

    private double useCpuRate = -1;

    public String getDesci() {
        return desci;
    }

    public double getUseCpuRate() {
        return useCpuRate;
    }

    public void setUseCpuRate(double useCpuRate) {
        this.useCpuRate = useCpuRate;
    }

    public void setDesci(String desci) {
        this.desci = desci;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + ":");
        sb.append("\tdesci = " + desci);
        sb.append("\ttotalDisk = " + totalDisk);
        sb.append("\tfreeDisk = " + freeDisk);
        sb.append("\ttotalRam = " + totalRam);
        sb.append("\tfreeRam = " + freeRam);
        sb.append("\tuseCpuRate = " + useCpuRate);
        return sb.toString();
    }

    public long getTotalDisk() {
        return totalDisk;
    }

    public void setTotalDisk(long totalDisk) {
        this.totalDisk = totalDisk;
    }

    public long getFreeDisk() {
        return freeDisk;
    }

    public void setFreeDisk(long freeDisk) {
        this.freeDisk = freeDisk;
    }

    public long getTotalRam() {
        return totalRam;
    }

    public void setTotalRam(long totalRam) {
        this.totalRam = totalRam;
    }

    public long getFreeRam() {
        return freeRam;
    }

    public void setFreeRam(long freeRam) {
        this.freeRam = freeRam;
    }

}
