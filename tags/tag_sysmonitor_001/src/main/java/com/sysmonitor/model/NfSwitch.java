package com.sysmonitor.model;

import java.util.HashSet;
import java.util.Set;

/**
 * NfSwitch entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NfSwitch implements java.io.Serializable {

    // Fields

    private Integer id;

    private NfHost nfHost;

    private String ifIndex;

    private String status;

    private String remarks;

    private Set nfLogs = new HashSet(0);

    // Constructors

    /** default constructor */
    public NfSwitch() {
    }

    /** full constructor */
    public NfSwitch(NfHost nfHost, String ifIndex, String status, String remarks, Set nfLogs) {
        this.nfHost = nfHost;
        this.ifIndex = ifIndex;
        this.status = status;
        this.remarks = remarks;
        this.nfLogs = nfLogs;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NfHost getNfHost() {
        return this.nfHost;
    }

    public void setNfHost(NfHost nfHost) {
        this.nfHost = nfHost;
    }

    public String getIfIndex() {
        return this.ifIndex;
    }

    public void setIfIndex(String ifIndex) {
        this.ifIndex = ifIndex;
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

    public Set getNfLogs() {
        return this.nfLogs;
    }

    public void setNfLogs(Set nfLogs) {
        this.nfLogs = nfLogs;
    }

}