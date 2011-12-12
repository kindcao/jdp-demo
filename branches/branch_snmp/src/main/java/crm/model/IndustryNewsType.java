package crm.model;

/**
 * IndustryNewsType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class IndustryNewsType implements java.io.Serializable {

    // Fields

    private Integer id;

    private String name;

    private String descript;

    private Integer dispOrder;

    // Constructors

    /** default constructor */
    public IndustryNewsType() {
    }

    /** minimal constructor */
    public IndustryNewsType(String name, Integer dispOrder) {
        this.name = name;
        this.dispOrder = dispOrder;
    }

    /** full constructor */
    public IndustryNewsType(String name, String descript, Integer dispOrder) {
        this.name = name;
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

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
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