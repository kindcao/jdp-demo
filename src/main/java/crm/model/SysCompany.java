package crm.model;

/**
 * SysCompany entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class SysCompany implements java.io.Serializable {

    // Fields

    private Integer id;

    private String companyName;

    private String descript;

    private String status;

    private String logo;

    private String type;

    // Constructors

    /** default constructor */
    public SysCompany() {
    }

    /** minimal constructor */
    public SysCompany(String companyName, String status, String logo, String type) {
        this.companyName = companyName;
        this.status = status;
        this.logo = logo;
        this.type = type;
    }

    /** full constructor */
    public SysCompany(String companyName, String descript, String status, String logo, String type) {
        this.companyName = companyName;
        this.descript = descript;
        this.status = status;
        this.logo = logo;
        this.type = type;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}