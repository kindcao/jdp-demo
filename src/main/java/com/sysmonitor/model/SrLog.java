package com.sysmonitor.model;

import java.util.Date;

/**
 * SrLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SrLog implements java.io.Serializable {

    // Fields

    private Integer id;

    private SrResource srResource;

    private Long actualValue;

    private Date occurrenceTime;

    // Constructors

    /** default constructor */
    public SrLog() {
    }

    /** minimal constructor */
    public SrLog(Integer id, Date occurrenceTime) {
        this.id = id;
        this.occurrenceTime = occurrenceTime;
    }

    /** full constructor */
    public SrLog(Integer id, SrResource srResource, Long actualValue, Date occurrenceTime) {
        this.id = id;
        this.srResource = srResource;
        this.actualValue = actualValue;
        this.occurrenceTime = occurrenceTime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SrResource getSrResource() {
        return this.srResource;
    }

    public void setSrResource(SrResource srResource) {
        this.srResource = srResource;
    }

    public Long getActualValue() {
        return this.actualValue;
    }

    public void setActualValue(Long actualValue) {
        this.actualValue = actualValue;
    }

    public Date getOccurrenceTime() {
        return this.occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

}