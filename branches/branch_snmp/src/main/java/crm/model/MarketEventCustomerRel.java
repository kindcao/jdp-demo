package crm.model;

/**
 * MarketEventCustomerRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventCustomerRel implements java.io.Serializable {

    // Fields

    private MarketEventCustomerRelId id;

    // Constructors

    /** default constructor */
    public MarketEventCustomerRel() {
    }

    /** full constructor */
    public MarketEventCustomerRel(MarketEventCustomerRelId id) {
        this.id = id;
    }

    // Property accessors

    public MarketEventCustomerRelId getId() {
        return this.id;
    }

    public void setId(MarketEventCustomerRelId id) {
        this.id = id;
    }

}