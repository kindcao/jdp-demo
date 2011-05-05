package crm.model;

/**
 * CustomerView entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CustomerView implements java.io.Serializable {

    private Integer id;

    // Fields
    private Integer customerId;

    private String custName;

    private String custCode;

    private String address;

    private Integer industryId;

    private String industryName;

    private String sysCompanyId;

    private String companyName;

    private String contactId;

    private String contactName;

    private String isPrimaryContact;
    
    private String phone;

    // Constructors

    /** default constructor */
    public CustomerView() {
    }

    /** minimal constructor */
    public CustomerView(Integer customerId, String custName, Integer industryId, String industryName) {
        this.customerId = customerId;
        this.custName = custName;
        this.industryId = industryId;
        this.industryName = industryName;
    }

    /** full constructor */
    public CustomerView(Integer customerId, String custName, String custCode, String address, Integer industryId,
            String industryName, String sysCompanyId, String companyName, String contactId, String contactName,
            String isPrimaryContact) {
        this.customerId = customerId;
        this.custName = custName;
        this.custCode = custCode;
        this.address = address;
        this.industryId = industryId;
        this.industryName = industryName;
        this.sysCompanyId = sysCompanyId;
        this.companyName = companyName;
        this.contactId = contactId;
        this.contactName = contactName;
        this.isPrimaryContact = isPrimaryContact;
    }

    // Property accessors

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIndustryId() {
        return this.industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getIndustryName() {
        return this.industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getSysCompanyId() {
        return this.sysCompanyId;
    }

    public void setSysCompanyId(String sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactId() {
        return this.contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return this.contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getIsPrimaryContact() {
        return this.isPrimaryContact;
    }

    public void setIsPrimaryContact(String isPrimaryContact) {
        this.isPrimaryContact = isPrimaryContact;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CustomerView))
            return false;
        CustomerView castOther = (CustomerView) other;

        return ((this.getCustomerId() == castOther.getCustomerId()) || (this.getCustomerId() != null
                && castOther.getCustomerId() != null && this.getCustomerId().equals(castOther.getCustomerId())))
                && ((this.getCustName() == castOther.getCustName()) || (this.getCustName() != null
                        && castOther.getCustName() != null && this.getCustName().equals(castOther.getCustName())))
                && ((this.getCustCode() == castOther.getCustCode()) || (this.getCustCode() != null
                        && castOther.getCustCode() != null && this.getCustCode().equals(castOther.getCustCode())))
                && ((this.getAddress() == castOther.getAddress()) || (this.getAddress() != null
                        && castOther.getAddress() != null && this.getAddress().equals(castOther.getAddress())))
                && ((this.getIndustryId() == castOther.getIndustryId()) || (this.getIndustryId() != null
                        && castOther.getIndustryId() != null && this.getIndustryId().equals(castOther.getIndustryId())))
                && ((this.getIndustryName() == castOther.getIndustryName()) || (this.getIndustryName() != null
                        && castOther.getIndustryName() != null && this.getIndustryName().equals(
                        castOther.getIndustryName())))
                && ((this.getSysCompanyId() == castOther.getSysCompanyId()) || (this.getSysCompanyId() != null
                        && castOther.getSysCompanyId() != null && this.getSysCompanyId().equals(
                        castOther.getSysCompanyId())))
                && ((this.getCompanyName() == castOther.getCompanyName()) || (this.getCompanyName() != null
                        && castOther.getCompanyName() != null && this.getCompanyName().equals(
                        castOther.getCompanyName())))
                && ((this.getContactId() == castOther.getContactId()) || (this.getContactId() != null
                        && castOther.getContactId() != null && this.getContactId().equals(castOther.getContactId())))
                && ((this.getContactName() == castOther.getContactName()) || (this.getContactName() != null
                        && castOther.getContactName() != null && this.getContactName().equals(
                        castOther.getContactName())))
                && ((this.getIsPrimaryContact() == castOther.getIsPrimaryContact()) || (this.getIsPrimaryContact() != null
                        && castOther.getIsPrimaryContact() != null && this.getIsPrimaryContact().equals(
                        castOther.getIsPrimaryContact())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getCustomerId() == null ? 0 : this.getCustomerId().hashCode());
        result = 37 * result + (getCustName() == null ? 0 : this.getCustName().hashCode());
        result = 37 * result + (getCustCode() == null ? 0 : this.getCustCode().hashCode());
        result = 37 * result + (getAddress() == null ? 0 : this.getAddress().hashCode());
        result = 37 * result + (getIndustryId() == null ? 0 : this.getIndustryId().hashCode());
        result = 37 * result + (getIndustryName() == null ? 0 : this.getIndustryName().hashCode());
        result = 37 * result + (getSysCompanyId() == null ? 0 : this.getSysCompanyId().hashCode());
        result = 37 * result + (getCompanyName() == null ? 0 : this.getCompanyName().hashCode());
        result = 37 * result + (getContactId() == null ? 0 : this.getContactId().hashCode());
        result = 37 * result + (getContactName() == null ? 0 : this.getContactName().hashCode());
        result = 37 * result + (getIsPrimaryContact() == null ? 0 : this.getIsPrimaryContact().hashCode());
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getPhone() {
        return phone;
    }

    
    public void setPhone(String phone) {
        this.phone = phone;
    }
}