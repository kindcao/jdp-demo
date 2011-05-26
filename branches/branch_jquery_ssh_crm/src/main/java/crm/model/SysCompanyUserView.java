package crm.model;

/**
 * SysCompanyUserViewId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompanyUserView implements java.io.Serializable {

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

    private String companyName;

    private String superiorName;

    // Constructors

    /** default constructor */
    public SysCompanyUserView() {
    }

    /** minimal constructor */
    public SysCompanyUserView(Integer id, Integer sysCompanyId, String name, String loginId, String passwd,
            String status, String deleteFlag, String companyName) {
        this.id = id;
        this.sysCompanyId = sysCompanyId;
        this.name = name;
        this.loginId = loginId;
        this.passwd = passwd;
        this.status = status;
        this.deleteFlag = deleteFlag;
        this.companyName = companyName;
    }

    /** full constructor */
    public SysCompanyUserView(Integer id, Integer sysCompanyId, String name, String loginId, String passwd,
            Integer superiorId, String email, String status, String deleteFlag, String companyName, String superiorName) {
        this.id = id;
        this.sysCompanyId = sysCompanyId;
        this.name = name;
        this.loginId = loginId;
        this.passwd = passwd;
        this.superiorId = superiorId;
        this.email = email;
        this.status = status;
        this.deleteFlag = deleteFlag;
        this.companyName = companyName;
        this.superiorName = superiorName;
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

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getSuperiorName() {
        return this.superiorName;
    }

    public void setSuperiorName(String superiorName) {
        this.superiorName = superiorName;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof SysCompanyUserView))
            return false;
        SysCompanyUserView castOther = (SysCompanyUserView) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this
                .getId().equals(castOther.getId())))
                && ((this.getSysCompanyId() == castOther.getSysCompanyId()) || (this.getSysCompanyId() != null
                        && castOther.getSysCompanyId() != null && this.getSysCompanyId().equals(
                        castOther.getSysCompanyId())))
                && ((this.getName() == castOther.getName()) || (this.getName() != null && castOther.getName() != null && this
                        .getName().equals(castOther.getName())))
                && ((this.getLoginId() == castOther.getLoginId()) || (this.getLoginId() != null
                        && castOther.getLoginId() != null && this.getLoginId().equals(castOther.getLoginId())))
                && ((this.getPasswd() == castOther.getPasswd()) || (this.getPasswd() != null
                        && castOther.getPasswd() != null && this.getPasswd().equals(castOther.getPasswd())))
                && ((this.getSuperiorId() == castOther.getSuperiorId()) || (this.getSuperiorId() != null
                        && castOther.getSuperiorId() != null && this.getSuperiorId().equals(castOther.getSuperiorId())))
                && ((this.getEmail() == castOther.getEmail()) || (this.getEmail() != null
                        && castOther.getEmail() != null && this.getEmail().equals(castOther.getEmail())))
                && ((this.getStatus() == castOther.getStatus()) || (this.getStatus() != null
                        && castOther.getStatus() != null && this.getStatus().equals(castOther.getStatus())))
                && ((this.getDeleteFlag() == castOther.getDeleteFlag()) || (this.getDeleteFlag() != null
                        && castOther.getDeleteFlag() != null && this.getDeleteFlag().equals(castOther.getDeleteFlag())))
                && ((this.getCompanyName() == castOther.getCompanyName()) || (this.getCompanyName() != null
                        && castOther.getCompanyName() != null && this.getCompanyName().equals(
                        castOther.getCompanyName())))
                && ((this.getSuperiorName() == castOther.getSuperiorName()) || (this.getSuperiorName() != null
                        && castOther.getSuperiorName() != null && this.getSuperiorName().equals(
                        castOther.getSuperiorName())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getSysCompanyId() == null ? 0 : this.getSysCompanyId().hashCode());
        result = 37 * result + (getName() == null ? 0 : this.getName().hashCode());
        result = 37 * result + (getLoginId() == null ? 0 : this.getLoginId().hashCode());
        result = 37 * result + (getPasswd() == null ? 0 : this.getPasswd().hashCode());
        result = 37 * result + (getSuperiorId() == null ? 0 : this.getSuperiorId().hashCode());
        result = 37 * result + (getEmail() == null ? 0 : this.getEmail().hashCode());
        result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode());
        result = 37 * result + (getDeleteFlag() == null ? 0 : this.getDeleteFlag().hashCode());
        result = 37 * result + (getCompanyName() == null ? 0 : this.getCompanyName().hashCode());
        result = 37 * result + (getSuperiorName() == null ? 0 : this.getSuperiorName().hashCode());
        return result;
    }

}