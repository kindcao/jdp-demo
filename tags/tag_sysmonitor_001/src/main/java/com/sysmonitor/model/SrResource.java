package com.sysmonitor.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SrResource entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SrResource implements java.io.Serializable {

    // Fields

    private Integer id;

    private SrHost srHost;

    private String resourceIndex;

    private Long alarmValue;

    private String status;

    private String remarks;

    private Set srLogs = new HashSet(0);

    // Constructors

    /** default constructor */
    public SrResource() {
    }

    /** minimal constructor */
    public SrResource(Integer id) {
        this.id = id;
    }

    /** full constructor */
    public SrResource(Integer id, SrHost srHost, String resourceIndex, Long alarmValue, String status, String remarks,
            Set srLogs) {
        this.id = id;
        this.srHost = srHost;
        this.resourceIndex = resourceIndex;
        this.alarmValue = alarmValue;
        this.status = status;
        this.remarks = remarks;
        this.srLogs = srLogs;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SrHost getSrHost() {
        return this.srHost;
    }

    public void setSrHost(SrHost srHost) {
        this.srHost = srHost;
    }

    public String getResourceIndex() {
        return this.resourceIndex;
    }

    public void setResourceIndex(String resourceIndex) {
        this.resourceIndex = resourceIndex;
    }

    public Long getAlarmValue() {
        return this.alarmValue;
    }

    public void setAlarmValue(Long alarmValue) {
        this.alarmValue = alarmValue;
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

    public Set getSrLogs() {
        return this.srLogs;
    }

    public void setSrLogs(Set srLogs) {
        this.srLogs = srLogs;
    }

}