package crm.model;

/**
 * MarketEventSysUserRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventSysUserRel implements java.io.Serializable {

    // Fields

    private MarketEventSysUserRelId id;

    private String isPrimary;

    // Constructors

    /** default constructor */
    public MarketEventSysUserRel() {
    }

    /** minimal constructor */
    public MarketEventSysUserRel(MarketEventSysUserRelId id) {
        this.id = id;
    }

    /** full constructor */
    public MarketEventSysUserRel(MarketEventSysUserRelId id, String isPrimary) {
        this.id = id;
        this.isPrimary = isPrimary;
    }

    // Property accessors

    public MarketEventSysUserRelId getId() {
        return this.id;
    }

    public void setId(MarketEventSysUserRelId id) {
        this.id = id;
    }

    public String getIsPrimary() {
        return this.isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

}