package crm.model;

/**
 * CustomerSysCompanyRelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CustomerSysCompanyRelId implements java.io.Serializable {

    // Fields

    private Integer customerId;

    private Integer sysCompanyId;

    // Constructors

    /** default constructor */
    public CustomerSysCompanyRelId() {
    }

    /** full constructor */
    public CustomerSysCompanyRelId(Integer customerId, Integer sysCompanyId) {
        this.customerId = customerId;
        this.sysCompanyId = sysCompanyId;
    }

    // Property accessors

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSysCompanyId() {
        return this.sysCompanyId;
    }

    public void setSysCompanyId(Integer sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CustomerSysCompanyRelId))
            return false;
        CustomerSysCompanyRelId castOther = (CustomerSysCompanyRelId) other;

        return ((this.getCustomerId() == castOther.getCustomerId()) || (this.getCustomerId() != null
                && castOther.getCustomerId() != null && this.getCustomerId().equals(castOther.getCustomerId())))
                && ((this.getSysCompanyId() == castOther.getSysCompanyId()) || (this.getSysCompanyId() != null
                        && castOther.getSysCompanyId() != null && this.getSysCompanyId().equals(
                        castOther.getSysCompanyId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getCustomerId() == null ? 0 : this.getCustomerId().hashCode());
        result = 37 * result + (getSysCompanyId() == null ? 0 : this.getSysCompanyId().hashCode());
        return result;
    }

}