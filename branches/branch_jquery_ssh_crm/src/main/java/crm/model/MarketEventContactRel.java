package crm.model;

/**
 * MarketEventContactRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventContactRel implements java.io.Serializable {

    // Fields

    private MarketEventContactRelId id;

    // Constructors

    /** default constructor */
    public MarketEventContactRel() {
    }

    /** full constructor */
    public MarketEventContactRel(MarketEventContactRelId id) {
        this.id = id;
    }

    // Property accessors

    public MarketEventContactRelId getId() {
        return this.id;
    }

    public void setId(MarketEventContactRelId id) {
        this.id = id;
    }

}