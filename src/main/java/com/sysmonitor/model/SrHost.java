package com.sysmonitor.model;

import java.util.HashSet;
import java.util.Set;

/**
 * SrHost entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SrHost implements java.io.Serializable {

    // Fields

    private Integer id;

    private String hostAddress;

    private String hostName;

    private String protocol;

    private Integer port;

    private String community;

    private String osType;

    private String status;

    private String remarks;

    private Set srResources = new HashSet(0);

    // Constructors

    /** default constructor */
    public SrHost() {
    }

    /** full constructor */
    public SrHost(String hostAddress, String hostName, String protocol, Integer port, String community, String osType,
            String status, String remarks, Set srResources) {
        this.hostAddress = hostAddress;
        this.hostName = hostName;
        this.protocol = protocol;
        this.port = port;
        this.community = community;
        this.osType = osType;
        this.status = status;
        this.remarks = remarks;
        this.srResources = srResources;
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

    public String getOsType() {
        return this.osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
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

    public Set getSrResources() {
        return this.srResources;
    }

    public void setSrResources(Set srResources) {
        this.srResources = srResources;
    }

}