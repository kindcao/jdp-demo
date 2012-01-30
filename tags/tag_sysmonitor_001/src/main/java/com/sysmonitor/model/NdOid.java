package com.sysmonitor.model;

import java.util.HashSet;
import java.util.Set;


/**
 * NdOid entity. @author MyEclipse Persistence Tools
 */

public class NdOid  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String oid;
     private String status;
     private String remarks;
     private Set nfHostOidRefs = new HashSet(0);


    // Constructors

    /** default constructor */
    public NdOid() {
    }

    
    /** full constructor */
    public NdOid(String oid, String status, String remarks, Set nfHostOidRefs) {
        this.oid = oid;
        this.status = status;
        this.remarks = remarks;
        this.nfHostOidRefs = nfHostOidRefs;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getOid() {
        return this.oid;
    }
    
    public void setOid(String oid) {
        this.oid = oid;
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

    public Set getNfHostOidRefs() {
        return this.nfHostOidRefs;
    }
    
    public void setNfHostOidRefs(Set nfHostOidRefs) {
        this.nfHostOidRefs = nfHostOidRefs;
    }
   








}