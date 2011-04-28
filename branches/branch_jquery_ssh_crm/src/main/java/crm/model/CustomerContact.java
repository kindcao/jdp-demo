package crm.model;

import java.util.Date;

/**
 * CustomerContact entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CustomerContact implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer customerId;

    private String name;

    private String department;

    private String posit;

    private String phone;

    private String fax;

    private String mobile;

    private String email;

    private String address;

    private String isPrimary;

    private String remark;

    private String im;

    private String picture;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastUpdatedBy;

    private Date lastUpdatedTime;

    private String deleteFlag;

    private Integer deletedBy;

    private Date deletedTime;

    // Constructors

    /** default constructor */
    public CustomerContact() {
    }

    /** minimal constructor */
    public CustomerContact(Integer customerId, String name, Integer createdBy, String deleteFlag) {
        this.customerId = customerId;
        this.name = name;
        this.createdBy = createdBy;
        this.deleteFlag = deleteFlag;
    }

    /** full constructor */
    public CustomerContact(Integer customerId, String name, String department, String posit, String phone, String fax,
            String mobile, String email, String address, String isPrimary, String remark, String im, String picture,
            Integer createdBy, Date createdTime, Integer lastUpdatedBy, Date lastUpdatedTime, String deleteFlag,
            Integer deletedBy, Date deletedTime) {
        this.customerId = customerId;
        this.name = name;
        this.department = department;
        this.posit = posit;
        this.phone = phone;
        this.fax = fax;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.isPrimary = isPrimary;
        this.remark = remark;
        this.im = im;
        this.picture = picture;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedTime = lastUpdatedTime;
        this.deleteFlag = deleteFlag;
        this.deletedBy = deletedBy;
        this.deletedTime = deletedTime;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosit() {
        return this.posit;
    }

    public void setPosit(String posit) {
        this.posit = posit;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return this.fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIm() {
        return this.im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime() {
        return this.createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getLastUpdatedBy() {
        return this.lastUpdatedBy;
    }

    public void setLastUpdatedBy(Integer lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedTime() {
        return this.lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public String getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Integer getDeletedBy() {
        return this.deletedBy;
    }

    public void setDeletedBy(Integer deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Date getDeletedTime() {
        return this.deletedTime;
    }

    public void setDeletedTime(Date deletedTime) {
        this.deletedTime = deletedTime;
    }

}