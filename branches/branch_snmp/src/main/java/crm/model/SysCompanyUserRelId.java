package crm.model;

/**
 * SysCompanyUserRelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyUserRelId implements java.io.Serializable {

    // Fields

    private Integer parentUserId;

    private Integer childUserId;

    // Constructors

    /** default constructor */
    public SysCompanyUserRelId() {
    }

    /** full constructor */
    public SysCompanyUserRelId(Integer parentUserId, Integer childUserId) {
        this.parentUserId = parentUserId;
        this.childUserId = childUserId;
    }

    // Property accessors

    public Integer getParentUserId() {
        return this.parentUserId;
    }

    public void setParentUserId(Integer parentUserId) {
        this.parentUserId = parentUserId;
    }

    public Integer getChildUserId() {
        return this.childUserId;
    }

    public void setChildUserId(Integer childUserId) {
        this.childUserId = childUserId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SysCompanyUserRelId))
            return false;
        SysCompanyUserRelId castOther = (SysCompanyUserRelId) other;

        return ((this.getParentUserId() == castOther.getParentUserId()) || (this.getParentUserId() != null
                && castOther.getParentUserId() != null && this.getParentUserId().equals(castOther.getParentUserId())))
                && ((this.getChildUserId() == castOther.getChildUserId()) || (this.getChildUserId() != null
                        && castOther.getChildUserId() != null && this.getChildUserId().equals(
                        castOther.getChildUserId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getParentUserId() == null ? 0 : this.getParentUserId().hashCode());
        result = 37 * result + (getChildUserId() == null ? 0 : this.getChildUserId().hashCode());
        return result;
    }

}