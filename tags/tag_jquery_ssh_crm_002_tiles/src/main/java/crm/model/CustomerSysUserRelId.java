package crm.model;

/**
 * CustomerSysUserRelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CustomerSysUserRelId implements java.io.Serializable {

    // Fields

    private Integer customerId;

    private Integer sysCompanyUserId;

    // Constructors

    /** default constructor */
    public CustomerSysUserRelId() {
    }

    /** full constructor */
    public CustomerSysUserRelId(Integer customerId, Integer sysCompanyUserId) {
        this.customerId = customerId;
        this.sysCompanyUserId = sysCompanyUserId;
    }

    // Property accessors

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getSysCompanyUserId() {
        return this.sysCompanyUserId;
    }

    public void setSysCompanyUserId(Integer sysCompanyUserId) {
        this.sysCompanyUserId = sysCompanyUserId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof CustomerSysUserRelId))
            return false;
        CustomerSysUserRelId castOther = (CustomerSysUserRelId) other;

        return ((this.getCustomerId() == castOther.getCustomerId()) || (this.getCustomerId() != null
                && castOther.getCustomerId() != null && this.getCustomerId().equals(castOther.getCustomerId())))
                && ((this.getSysCompanyUserId() == castOther.getSysCompanyUserId()) || (this.getSysCompanyUserId() != null
                        && castOther.getSysCompanyUserId() != null && this.getSysCompanyUserId().equals(
                        castOther.getSysCompanyUserId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getCustomerId() == null ? 0 : this.getCustomerId().hashCode());
        result = 37 * result + (getSysCompanyUserId() == null ? 0 : this.getSysCompanyUserId().hashCode());
        return result;
    }

}