package crm.model;

/**
 * CustomerSysUserRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CustomerSysUserRel implements java.io.Serializable {

    // Fields

    private CustomerSysUserRelId id;

    private String isPrimary;

    // Constructors

    /** default constructor */
    public CustomerSysUserRel() {
    }

    /** minimal constructor */
    public CustomerSysUserRel(CustomerSysUserRelId id) {
        this.id = id;
    }

    /** full constructor */
    public CustomerSysUserRel(CustomerSysUserRelId id, String isPrimary) {
        this.id = id;
        this.isPrimary = isPrimary;
    }

    // Property accessors

    public CustomerSysUserRelId getId() {
        return this.id;
    }

    public void setId(CustomerSysUserRelId id) {
        this.id = id;
    }

    public String getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

}