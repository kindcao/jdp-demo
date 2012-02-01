package com.sysmonitor.model;

import java.util.Date;

/**
 * NfLog entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NfLog implements java.io.Serializable {

    // Fields

    private Integer id;

    private NfSwitch nfSwitch;

    private Long inOctets;

    private Long outOctets;

    private Date occurrenceTime;

    // Constructors

    /** default constructor */
    public NfLog() {
    }

    /** minimal constructor */
    public NfLog(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

    /** full constructor */
    public NfLog(NfSwitch nfSwitch, Long inOctets, Long outOctets, Date occurrenceTime) {
        this.nfSwitch = nfSwitch;
        this.inOctets = inOctets;
        this.outOctets = outOctets;
        this.occurrenceTime = occurrenceTime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NfSwitch getNfSwitch() {
        return this.nfSwitch;
    }

    public void setNfSwitch(NfSwitch nfSwitch) {
        this.nfSwitch = nfSwitch;
    }

    public Long getInOctets() {
        return this.inOctets;
    }

    public void setInOctets(Long inOctets) {
        this.inOctets = inOctets;
    }

    public Long getOutOctets() {
        return this.outOctets;
    }

    public void setOutOctets(Long outOctets) {
        this.outOctets = outOctets;
    }

    public Date getOccurrenceTime() {
        return this.occurrenceTime;
    }

    public void setOccurrenceTime(Date occurrenceTime) {
        this.occurrenceTime = occurrenceTime;
    }

}