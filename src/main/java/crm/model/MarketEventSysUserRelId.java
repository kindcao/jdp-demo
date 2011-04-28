package crm.model;

/**
 * MarketEventSysUserRelId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventSysUserRelId implements java.io.Serializable {

    // Fields

    private Integer marketEventId;

    private Integer sysCompanyUserId;

    // Constructors

    /** default constructor */
    public MarketEventSysUserRelId() {
    }

    /** full constructor */
    public MarketEventSysUserRelId(Integer marketEventId, Integer sysCompanyUserId) {
        this.marketEventId = marketEventId;
        this.sysCompanyUserId = sysCompanyUserId;
    }

    // Property accessors

    public Integer getMarketEventId() {
        return this.marketEventId;
    }

    public void setMarketEventId(Integer marketEventId) {
        this.marketEventId = marketEventId;
    }

    public Integer getSysCompanyUserId() {
        return this.sysCompanyUserId;
    }

    public void setSysCompanyUserId(Integer sysCompanyUserId) {
        this.sysCompanyUserId = sysCompanyUserId;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MarketEventSysUserRelId))
            return false;
        MarketEventSysUserRelId castOther = (MarketEventSysUserRelId) other;

        return ((this.getMarketEventId() == castOther.getMarketEventId()) || (this.getMarketEventId() != null
                && castOther.getMarketEventId() != null && this.getMarketEventId().equals(castOther.getMarketEventId())))
                && ((this.getSysCompanyUserId() == castOther.getSysCompanyUserId()) || (this.getSysCompanyUserId() != null
                        && castOther.getSysCompanyUserId() != null && this.getSysCompanyUserId().equals(
                        castOther.getSysCompanyUserId())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getMarketEventId() == null ? 0 : this.getMarketEventId().hashCode());
        result = 37 * result + (getSysCompanyUserId() == null ? 0 : this.getSysCompanyUserId().hashCode());
        return result;
    }

}