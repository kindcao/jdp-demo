package crm.model;

/**
 * SysCompanyUserRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyUserRole implements java.io.Serializable {

    // Fields

    private SysCompanyUserRoleId id;

    // Constructors

    /** default constructor */
    public SysCompanyUserRole() {
    }

    /** full constructor */
    public SysCompanyUserRole(SysCompanyUserRoleId id) {
        this.id = id;
    }

    // Property accessors

    public SysCompanyUserRoleId getId() {
        return this.id;
    }

    public void setId(SysCompanyUserRoleId id) {
        this.id = id;
    }

}