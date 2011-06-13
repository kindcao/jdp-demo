package crm.model;

/**
 * SysCompanyUserRoleId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyUserRoleId implements java.io.Serializable {

    // Fields

    private Integer sysCompanyUserId;

    private Integer sysCompanyRoleId;

    // Constructors

    /** default constructor */
    public SysCompanyUserRoleId() {
    }

    /** full constructor */
    public SysCompanyUserRoleId(Integer sysCompanyUserId, Integer sysCompanyRoleId) {
        this.sysCompanyUserId = sysCompanyUserId;
        this.sysCompanyRoleId = sysCompanyRoleId;
    }

    // Property accessors

    public Integer getSysCompanyUserId() {
        return this.sysCompanyUserId;
    }

    public void setSysCompanyUserId(Integer sysCompanyUserId) {
        this.sysCompanyUserId = sysCompanyUserId;
    }

    public Integer getSysCompanyRoleId() {
        return this.sysCompanyRoleId;
    }

    public void setSysCompanyRoleId(Integer sysCompanyRoleId) {
        this.sysCompanyRoleId = sysCompanyRoleId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SysCompanyUserRoleId))
            return false;
        SysCompanyUserRoleId castOther = (SysCompanyUserRoleId) other;

        return ((this.getSysCompanyUserId() == castOther.getSysCompanyUserId()) || (this.getSysCompanyUserId() != null
                && castOther.getSysCompanyUserId() != null && this.getSysCompanyUserId().equals(
                castOther.getSysCompanyUserId())))
                && ((this.getSysCompanyRoleId() == castOther.getSysCompanyRoleId()) || (this.getSysCompanyRoleId() != null
                        && castOther.getSysCompanyRoleId() != null && this.getSysCompanyRoleId().equals(
                        castOther.getSysCompanyRoleId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getSysCompanyUserId() == null ? 0 : this.getSysCompanyUserId().hashCode());
        result = 37 * result + (getSysCompanyRoleId() == null ? 0 : this.getSysCompanyRoleId().hashCode());
        return result;
    }

}