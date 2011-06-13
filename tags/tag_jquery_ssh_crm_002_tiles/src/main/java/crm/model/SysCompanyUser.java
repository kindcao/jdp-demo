package crm.model;

/**
 * SysCompanyUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyUser implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer sysCompanyId;

    private String name;

    private String loginId;

    private String passwd;

    private Integer superiorId;

    private String email;

    private String status;

    private String deleteFlag;

    // Constructors

    /** default constructor */
    public SysCompanyUser() {
    }

    /** minimal constructor */
    public SysCompanyUser(Integer sysCompanyId, String name, String loginId, String passwd, String status,
            String deleteFlag) {
        this.sysCompanyId = sysCompanyId;
        this.name = name;
        this.loginId = loginId;
        this.passwd = passwd;
        this.status = status;
        this.deleteFlag = deleteFlag;
    }

    /** full constructor */
    public SysCompanyUser(Integer sysCompanyId, String name, String loginId, String passwd, Integer superiorId,
            String email, String status, String deleteFlag) {
        this.sysCompanyId = sysCompanyId;
        this.name = name;
        this.loginId = loginId;
        this.passwd = passwd;
        this.superiorId = superiorId;
        this.email = email;
        this.status = status;
        this.deleteFlag = deleteFlag;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return this.loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPasswd() {
        return this.passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Integer getSuperiorId() {
        return this.superiorId;
    }

    public void setSuperiorId(Integer superiorId) {
        this.superiorId = superiorId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleteFlag() {
        return this.deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

}