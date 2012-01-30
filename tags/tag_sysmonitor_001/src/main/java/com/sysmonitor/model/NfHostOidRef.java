package com.sysmonitor.model;

import java.util.HashSet;
import java.util.Set;


/**
 * NfHostOidRef entity. @author MyEclipse Persistence Tools
 */

public class NfHostOidRef  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private NfHost nfHost;
     private NdOid ndOid;
     private Set nfLogs = new HashSet(0);


    // Constructors

    /** default constructor */
    public NfHostOidRef() {
    }

    
    /** full constructor */
    public NfHostOidRef(NfHost nfHost, NdOid ndOid, Set nfLogs) {
        this.nfHost = nfHost;
        this.ndOid = ndOid;
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

    public NdOid getNdOid() {
        return this.ndOid;
    }
    
    public void setNdOid(NdOid ndOid) {
        this.ndOid = ndOid;
    }

    public Set getNfLogs() {
        return this.nfLogs;
    }
    
    public void setNfLogs(Set nfLogs) {
        this.nfLogs = nfLogs;
    }
   








}