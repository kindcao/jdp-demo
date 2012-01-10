package com.netmonitor.snmp;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 10:53:43 AM
 */
public class IfEntry {

    private String oid;

    private String ifIndex;

    private String ifDescr;

    private String ifType;

    private long ifInOctets;

    private long ifOutOctets;

    // for chart
    private long lastIfInOctets = -1;

    private long lastIfOutOctets = -1;

    private int exponent;

    // for datalist

    private String id;

    private String chartPath;

    private long totalIfInOctets;

    private long totalIfOutOctets;

    private long totalTime = System.currentTimeMillis();

    private boolean status;

    private String strTotalIfInOctets;

    private String strTotalIfOutOctets;

    private String strAvgIfInOctets;

    private String strAvgIfOutOctets;

    private String strTotalTime;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTotalIfInOctets() {
        return totalIfInOctets;
    }

    public void setTotalIfInOctets(long totalIfInOctets) {
        this.totalIfInOctets = totalIfInOctets;
    }

    public long getTotalIfOutOctets() {
        return totalIfOutOctets;
    }

    public void setTotalIfOutOctets(long totalIfOutOctets) {
        this.totalIfOutOctets = totalIfOutOctets;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getIfDescr() {
        return ifDescr;
    }

    public void setIfDescr(String ifDescr) {
        this.ifDescr = ifDescr;
    }

    public String getIfType() {
        return ifType;
    }

    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    public long getIfInOctets() {
        return ifInOctets;
    }

    public void setIfInOctets(long ifInOctets) {
        if (lastIfInOctets == -1) {
            this.lastIfInOctets = ifInOctets;
        }
        this.ifInOctets = ifInOctets;
    }

    public long getIfOutOctets() {
        return ifOutOctets;
    }

    public void setIfOutOctets(long ifOutOctets) {
        if (lastIfOutOctets == -1) {
            this.lastIfOutOctets = ifOutOctets;
        }
        this.ifOutOctets = ifOutOctets;
    }

    public String getIfIndex() {
        return ifIndex;
    }

    public void setIfIndex(String ifIndex) {
        this.ifIndex = ifIndex;
        this.id = this.ifIndex;
    }

    public long getLastIfInOctets() {
        return lastIfInOctets;
    }

    public void setLastIfInOctets(long lastIfInOctets) {
        this.lastIfInOctets = lastIfInOctets;
    }

    public long getLastIfOutOctets() {
        return lastIfOutOctets;
    }

    public void setLastIfOutOctets(long lastIfOutOctets) {
        this.lastIfOutOctets = lastIfOutOctets;
    }

    @Override
    public boolean equals(Object obj) {
        IfEntry entry = (IfEntry) obj;
        return oid.equals(entry.oid);
    }

    @Override
    public int hashCode() {
        if (StringUtils.isNotBlank(ifIndex)) {
            return Integer.valueOf(ifIndex);
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + ":");
        sb.append("\toid = " + oid);
        sb.append("\tifIndex = " + ifIndex);
        sb.append("\tifDescr = " + ifDescr);
        sb.append("\tifType = " + ifType);
        sb.append("\tifInOctets = " + ifInOctets);
        sb.append("\tifOutOctets = " + ifOutOctets);
        sb.append("\tlastIfInOctets = " + lastIfInOctets);
        sb.append("\tlastIfOutOctets = " + lastIfOutOctets);
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChartPath() {
        return chartPath;
    }

    public void setChartPath(String chartPath) {
        this.chartPath = chartPath;
    }

    public int getExponent() {
        return exponent;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public String getStrTotalIfInOctets() {
        return strTotalIfInOctets;
    }

    public void setStrTotalIfInOctets(String strTotalIfInOctets) {
        this.strTotalIfInOctets = strTotalIfInOctets;
    }

    public String getStrTotalIfOutOctets() {
        return strTotalIfOutOctets;
    }

    public void setStrTotalIfOutOctets(String strTotalIfOutOctets) {
        this.strTotalIfOutOctets = strTotalIfOutOctets;
    }

    public String getStrAvgIfInOctets() {
        return strAvgIfInOctets;
    }

    public void setStrAvgIfInOctets(String strAvgIfInOctets) {
        this.strAvgIfInOctets = strAvgIfInOctets;
    }

    public String getStrAvgIfOutOctets() {
        return strAvgIfOutOctets;
    }

    public void setStrAvgIfOutOctets(String strAvgIfOutOctets) {
        this.strAvgIfOutOctets = strAvgIfOutOctets;
    }

    public String getStrTotalTime() {
        return strTotalTime;
    }

    public void setStrTotalTime(String strTotalTime) {
        this.strTotalTime = strTotalTime;
    }

}
