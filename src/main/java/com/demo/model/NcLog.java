package com.demo.model;

import java.util.Date;

/**
 * NcLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NcLog implements java.io.Serializable {

    // Fields

    private Integer id;

    private NcHost ncHost;

    private Date occurrenceTime;

    // Constructors

    /** default constructor */
    public NcLog() {
    }

    /** full constructor */
    public NcLog(Integer id, NcHost ncHost, Date occurrenceTime) {
        this.id = id;
        this.ncHost = ncHost;
        this.occurrenceTime = occurrenceTime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NcHost getNcHost() {
        return this.ncHost;
    }

    public void setNcHost(NcHost ncHost) {
        this.ncHost = ncHost;
    }

    public Date getOccurrenceTime() {
        return this.occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

}