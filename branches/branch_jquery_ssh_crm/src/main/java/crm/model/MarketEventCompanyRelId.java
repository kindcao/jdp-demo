package crm.model;

/**
 * MarketEventCompanyRelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventCompanyRelId implements java.io.Serializable {

    // Fields

    private Integer marketEventId;

    private Integer sysCompanyId;

    // Constructors

    /** default constructor */
    public MarketEventCompanyRelId() {
    }

    /** full constructor */
    public MarketEventCompanyRelId(Integer marketEventId, Integer sysCompanyId) {
        this.marketEventId = marketEventId;
        this.sysCompanyId = sysCompanyId;
    }

    // Property accessors

    public Integer getMarketEventId() {
        return this.marketEventId;
    }

    public void setMarketEventId(Integer marketEventId) {
        this.marketEventId = marketEventId;
    }

    public Integer getSysCompanyId() {
        return this.sysCompanyId;
    }

    public void setSysCompanyId(Integer sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MarketEventCompanyRelId))
            return false;
        MarketEventCompanyRelId castOther = (MarketEventCompanyRelId) other;

        return ((this.getMarketEventId() == castOther.getMarketEventId()) || (this.getMarketEventId() != null
                && castOther.getMarketEventId() != null && this.getMarketEventId().equals(castOther.getMarketEventId())))
                && ((this.getSysCompanyId() == castOther.getSysCompanyId()) || (this.getSysCompanyId() != null
                        && castOther.getSysCompanyId() != null && this.getSysCompanyId().equals(
                        castOther.getSysCompanyId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getMarketEventId() == null ? 0 : this.getMarketEventId().hashCode());
        result = 37 * result + (getSysCompanyId() == null ? 0 : this.getSysCompanyId().hashCode());
        return result;
    }

}