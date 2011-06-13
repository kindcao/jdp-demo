package crm.model;

/**
 * CustomerSysCompanyRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class CustomerSysCompanyRel implements java.io.Serializable {

    // Fields

    private CustomerSysCompanyRelId id;

    // Constructors

    /** default constructor */
    public CustomerSysCompanyRel() {
    }

    /** full constructor */
    public CustomerSysCompanyRel(CustomerSysCompanyRelId id) {
        this.id = id;
    }

    // Property accessors

    public CustomerSysCompanyRelId getId() {
        return this.id;
    }

    public void setId(CustomerSysCompanyRelId id) {
        this.id = id;
    }

}