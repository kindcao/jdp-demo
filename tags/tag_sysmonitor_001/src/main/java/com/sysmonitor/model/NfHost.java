package com.sysmonitor.model;

import java.util.HashSet;
import java.util.Set;

/**
 * NfHost entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class NfHost implements java.io.Serializable {

    // Fields

    private Integer id;

    private String hostAddress;

    private String hostName;

    private String protocol;

    private Integer port;

    private String community;

    private Long inOctets;

    private Long outOctets;

    private String status;

    private String remarks;

    private Set nfSwitchs = new HashSet(0);

    // Constructors

    /** default constructor */
    public NfHost() {
    }

    /** full constructor */
    public NfHost(String hostAddress, String hostName, String protocol, Integer port, String community, Long inOctets,
            Long outOctets, String status, String remarks, Set nfSwitchs) {
        this.hostAddress = hostAddress;
        this.hostName = hostName;
        this.protocol = protocol;
        this.port = port;
        this.community = community;
        this.inOctets = inOctets;
        this.outOctets = outOctets;
        this.status = status;
        this.remarks = remarks;
        this.nfSwitchs = nfSwitchs;
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

    public String getProtocol() {
        return this.protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getCommunity() {
        return this.community;
    }

    public void setCommunity(String community) {
        this.community = community;
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

    public Set getNfSwitchs() {
        return this.nfSwitchs;
    }

    public void setNfSwitchs(Set nfSwitchs) {
        this.nfSwitchs = nfSwitchs;
    }

}