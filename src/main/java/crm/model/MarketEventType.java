package crm.model;

/**
 * MarketEventType entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventType implements java.io.Serializable {

    // Fields

    private Integer id;

    private String name;

    private Integer superiorId;

    private Integer level;

    private String descript;

    private Integer dispOrder;

    // Constructors

    /** default constructor */
    public MarketEventType() {
    }

    /** minimal constructor */
    public MarketEventType(String name, Integer level, Integer dispOrder) {
        this.name = name;
        this.level = level;
        this.dispOrder = dispOrder;
    }

    /** full constructor */
    public MarketEventType(String name, Integer superiorId, Integer level, String descript, Integer dispOrder) {
        this.name = name;
        this.superiorId = superiorId;
        this.level = level;
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

    public Integer getSuperiorId() {
        return this.superiorId;
    }

    public void setSuperiorId(Integer superiorId) {
        this.superiorId = superiorId;
    }

    public Integer getLevel() {
        return this.level;
    }

    public void setLevel(Integer level) {
        this.level = level;
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