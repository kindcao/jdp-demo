package crm.model;

/**
 * MarketEventContactRelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventContactRelId implements java.io.Serializable {

    // Fields

    private Integer marketEventId;

    private Integer customerContactId;

    // Constructors

    /** default constructor */
    public MarketEventContactRelId() {
    }

    /** full constructor */
    public MarketEventContactRelId(Integer marketEventId, Integer customerContactId) {
        this.marketEventId = marketEventId;
        this.customerContactId = customerContactId;
    }

    // Property accessors

    public Integer getMarketEventId() {
        return this.marketEventId;
    }

    public void setMarketEventId(Integer marketEventId) {
        this.marketEventId = marketEventId;
    }

    public Integer getCustomerContactId() {
        return this.customerContactId;
    }

    public void setCustomerContactId(Integer customerContactId) {
        this.customerContactId = customerContactId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MarketEventContactRelId))
            return false;
        MarketEventContactRelId castOther = (MarketEventContactRelId) other;

        return ((this.getMarketEventId() == castOther.getMarketEventId()) || (this.getMarketEventId() != null
                && castOther.getMarketEventId() != null && this.getMarketEventId().equals(castOther.getMarketEventId())))
                && ((this.getCustomerContactId() == castOther.getCustomerContactId()) || (this.getCustomerContactId() != null
                        && castOther.getCustomerContactId() != null && this.getCustomerContactId().equals(
                        castOther.getCustomerContactId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getMarketEventId() == null ? 0 : this.getMarketEventId().hashCode());
        result = 37 * result + (getCustomerContactId() == null ? 0 : this.getCustomerContactId().hashCode());
        return result;
    }

}