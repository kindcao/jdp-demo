package crm.model;

/**
 * SysCompanyRole entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyRole implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer sysCompanyId;

    private String roleName;

    private String descript;

    private Integer dispOrder;

    // Constructors

    /** default constructor */
    public SysCompanyRole() {
    }

    /** minimal constructor */
    public SysCompanyRole(Integer sysCompanyId, String roleName, Integer dispOrder) {
        this.sysCompanyId = sysCompanyId;
        this.roleName = roleName;
        this.dispOrder = dispOrder;
    }

    /** full constructor */
    public SysCompanyRole(Integer sysCompanyId, String roleName, String descript, Integer dispOrder) {
        this.sysCompanyId = sysCompanyId;
        this.roleName = roleName;
        this.descript = descript;
        this.dispOrder = dispOrder;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysCompanyId() {
        return this.sysCompanyId;
    }

    public void setSysCompanyId(Integer sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public String getRoleName() {
        return this.roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public Integer getDispOrder() {
        return this.dispOrder;
    }

    public void setDispOrder(Integer dispOrder) {
        this.dispOrder = dispOrder;
    }

}