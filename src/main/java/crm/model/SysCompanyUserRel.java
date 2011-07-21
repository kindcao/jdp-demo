package crm.model;

/**
 * SysCompanyUserRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyUserRel implements java.io.Serializable {

    // Fields

    private SysCompanyUserRelId id;

    // Constructors

    /** default constructor */
    public SysCompanyUserRel() {
    }

    /** full constructor */
    public SysCompanyUserRel(SysCompanyUserRelId id) {
        this.id = id;
    }

    // Property accessors

    public SysCompanyUserRelId getId() {
        return this.id;
    }

    public void setId(SysCompanyUserRelId id) {
        this.id = id;
    }

}