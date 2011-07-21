package crm.model;

/**
 * MarketEventCompanyRel entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventCompanyRel implements java.io.Serializable {

    // Fields

    private MarketEventCompanyRelId id;

    // Constructors

    /** default constructor */
    public MarketEventCompanyRel() {
    }

    /** full constructor */
    public MarketEventCompanyRel(MarketEventCompanyRelId id) {
        this.id = id;
    }

    // Property accessors

    public MarketEventCompanyRelId getId() {
        return this.id;
    }

    public void setId(MarketEventCompanyRelId id) {
        this.id = id;
    }

}