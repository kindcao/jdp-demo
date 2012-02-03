package com.sysmonitor.model;

import java.util.HashSet;
import java.util.Set;

/**
 * NcHost entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NcHost implements java.io.Serializable {

    // Fields

    private Integer id;

    private String hostAddress;

    private String hostName;

    private Integer times;

    private String status;

    private String remarks;

    private Set ncLogs = new HashSet(0);

    // Constructors

    /** default constructor */
    public NcHost() {
    }

    /** full constructor */
    public NcHost(String hostAddress, String hostName, Integer times, String status, String remarks, Set ncLogs) {
        this.hostAddress = hostAddress;
        this.hostName = hostName;
        this.times = times;
        this.status = status;
        this.remarks = remarks;
        this.ncLogs = ncLogs;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHostAddress() {
        return this.hostAddress;
    }

    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getTimes() {
        return this.times;
    }

    public void setTimes(Integer times) {
        this.times = times;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Set getNcLogs() {
        return this.ncLogs;
    }

    public void setNcLogs(Set ncLogs) {
        this.ncLogs = ncLogs;
    }

}