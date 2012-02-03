package com.sysmonitor.snmp;

/**
 * @author Kind Cao
 * @version $Rev: $, Feb 3, 2012 3:42:39 PM
 */
public class SysResourceEntry {

    private String desci;

    private long totalCapacity = -1;

    private long freeCapacity = -1;

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
        sb.append("\ttotalCapacity = " + totalCapacity);
        sb.append("\tfreeCapacity = " + freeCapacity);
        sb.append("\tuseCpuRate = " + useCpuRate);
        return sb.toString();
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

}
