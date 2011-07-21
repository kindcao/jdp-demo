package crm.model;

/**
 * SysCompanyRolePermission entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyRolePermission implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer sysCompanyRoleId;

    private String service;

    private String method;

    // Constructors

    /** default constructor */
    public SysCompanyRolePermission() {
    }

    /** full constructor */
    public SysCompanyRolePermission(Integer sysCompanyRoleId, String service, String method) {
        this.sysCompanyRoleId = sysCompanyRoleId;
        this.service = service;
        this.method = method;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSysCompanyRoleId() {
        return this.sysCompanyRoleId;
    }

    public void setSysCompanyRoleId(Integer sysCompanyRoleId) {
        this.sysCompanyRoleId = sysCompanyRoleId;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getMethod() {
        return this.method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

}