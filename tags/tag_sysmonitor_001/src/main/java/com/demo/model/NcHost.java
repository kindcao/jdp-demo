package com.demo.model;

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

    private String hostIp;

    private String hostName;

    private String status;

    private String remarks;

    private Set ncLogs = new HashSet(0);

    // Constructors

    /** default constructor */
    public NcHost() {
    }

    /** minimal constructor */
    public NcHost(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public NcHost(Integer id, String hostIp, String hostName, String status, String remarks, Set ncLogs) {
        this.id = id;
        this.hostIp = hostIp;
        this.hostName = hostName;
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

    public String getHostIp() {
        return this.hostIp;
    }

    public void setHostIp(String hostIp) {
        this.hostIp = hostIp;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
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