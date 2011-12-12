package crm.model;

/**
 * MarketEventCustomerRelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventCustomerRelId implements java.io.Serializable {

    // Fields

    private Integer marketEventId;

    private Integer customerId;

    // Constructors

    /** default constructor */
    public MarketEventCustomerRelId() {
    }

    /** full constructor */
    public MarketEventCustomerRelId(Integer marketEventId, Integer customerId) {
        this.marketEventId = marketEventId;
        this.customerId = customerId;
    }

    // Property accessors

    public Integer getMarketEventId() {
        return this.marketEventId;
    }

    public void setMarketEventId(Integer marketEventId) {
        this.marketEventId = marketEventId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MarketEventCustomerRelId))
            return false;
        MarketEventCustomerRelId castOther = (MarketEventCustomerRelId) other;

        return ((this.getMarketEventId() == castOther.getMarketEventId()) || (this.getMarketEventId() != null
                && castOther.getMarketEventId() != null && this.getMarketEventId().equals(castOther.getMarketEventId())))
                && ((this.getCustomerId() == castOther.getCustomerId()) || (this.getCustomerId() != null
                        && castOther.getCustomerId() != null && this.getCustomerId().equals(castOther.getCustomerId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getMarketEventId() == null ? 0 : this.getMarketEventId().hashCode());
        result = 37 * result + (getCustomerId() == null ? 0 : this.getCustomerId().hashCode());
        return result;
    }

}