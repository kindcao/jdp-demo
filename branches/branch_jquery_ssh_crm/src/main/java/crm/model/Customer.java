package crm.model;

import java.util.Date;

/**
 * Customer entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class Customer implements java.io.Serializable {

    // Fields

    private Integer id;

    private String custName;

    private String custCode;

    private Integer industryId;

    private String phone;

    private String fax;

    private String website;

    private String country;

    private String province;

    private String city;

    private String address;

    private String postcode;

    private String descript;

    private String remark;

    private String email;

    private Integer createdBy;

    private Date createdTime;

    private Integer lastUpdatedBy;

    private Date lastUpdatedTime;

    private String deleteFlag;

    private Integer deletedBy;

    private Date deletedTime;

    // Constructors

    /** default constructor */
    public Customer() {
    }

    /** minimal constructor */
    public Customer(String custName, Integer industryId, String phone, Integer createdBy, String deleteFlag) {
        this.custName = custName;
        this.industryId = industryId;
        this.phone = phone;
        this.createdBy = createdBy;
        this.deleteFlag = deleteFlag;
    }

    /** full constructor */
    public Customer(String custName, String custCode, Integer industryId, String phone, String fax, String website,
            String country, String province, String city, String address, String postcode, String descript,
            String remark, String email, Integer createdBy, Date createdTime, Integer lastUpdatedBy,
            Date lastUpdatedTime, String deleteFlag, Integer deletedBy, Date deletedTime) {
        this.custName = custName;
        this.custCode = custCode;
        this.industryId = industryId;
        this.phone = phone;
        this.fax = fax;
        this.website = website;
        this.country = country;
        this.province = province;
        this.city = city;
        this.address = address;
        this.postcode = postcode;
        this.descript = descript;
        this.remark = remark;
        this.email = email;
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

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCode() {
        return this.custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getIndustryId() {
        return this.industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
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

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
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