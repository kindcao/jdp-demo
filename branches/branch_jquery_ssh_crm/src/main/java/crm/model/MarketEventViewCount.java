package crm.model;

/**
 * MarketEventViewCount entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventViewCount implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer industrySuperiorId;

    private String industrySuperiorName;

    private Integer custId;

    private String custName;

    private Integer sysCompanyId;

    private Integer marketEventId;

    private Integer occurDate;

    private Integer mktevtSuperiorId;

    private String mktevtSuperiorName;

    // Constructors

    /** default constructor */
    public MarketEventViewCount() {
    }

    /** minimal constructor */
    public MarketEventViewCount(Integer industrySuperiorId, String industrySuperiorName, Integer custId,
            String custName, Integer sysCompanyId, Integer marketEventId, Integer occurDate, Integer mktevtSuperiorId,
            String mktevtSuperiorName) {
        this.industrySuperiorId = industrySuperiorId;
        this.industrySuperiorName = industrySuperiorName;
        this.custId = custId;
        this.custName = custName;
        this.sysCompanyId = sysCompanyId;
        this.marketEventId = marketEventId;
        this.occurDate = occurDate;
        this.mktevtSuperiorId = mktevtSuperiorId;
        this.mktevtSuperiorName = mktevtSuperiorName;
    }

    /** full constructor */
    public MarketEventViewCount(Integer id, Integer industrySuperiorId, String industrySuperiorName, Integer custId,
            String custName, Integer sysCompanyId, Integer marketEventId, Integer occurDate, Integer mktevtSuperiorId,
            String mktevtSuperiorName) {
        this.id = id;
        this.industrySuperiorId = industrySuperiorId;
        this.industrySuperiorName = industrySuperiorName;
        this.custId = custId;
        this.custName = custName;
        this.sysCompanyId = sysCompanyId;
        this.marketEventId = marketEventId;
        this.occurDate = occurDate;
        this.mktevtSuperiorId = mktevtSuperiorId;
        this.mktevtSuperiorName = mktevtSuperiorName;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndustrySuperiorId() {
        return this.industrySuperiorId;
    }

    public void setIndustrySuperiorId(Integer industrySuperiorId) {
        this.industrySuperiorId = industrySuperiorId;
    }

    public String getIndustrySuperiorName() {
        return this.industrySuperiorName;
    }

    public void setIndustrySuperiorName(String industrySuperiorName) {
        this.industrySuperiorName = industrySuperiorName;
    }

    public Integer getCustId() {
        return this.custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public Integer getSysCompanyId() {
        return this.sysCompanyId;
    }

    public void setSysCompanyId(Integer sysCompanyId) {
        this.sysCompanyId = sysCompanyId;
    }

    public Integer getMarketEventId() {
        return this.marketEventId;
    }

    public void setMarketEventId(Integer marketEventId) {
        this.marketEventId = marketEventId;
    }

    public Integer getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(Integer occurDate) {
        this.occurDate = occurDate;
    }

    public Integer getMktevtSuperiorId() {
        return this.mktevtSuperiorId;
    }

    public void setMktevtSuperiorId(Integer mktevtSuperiorId) {
        this.mktevtSuperiorId = mktevtSuperiorId;
    }

    public String getMktevtSuperiorName() {
        return this.mktevtSuperiorName;
    }

    public void setMktevtSuperiorName(String mktevtSuperiorName) {
        this.mktevtSuperiorName = mktevtSuperiorName;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MarketEventViewCount))
            return false;
        MarketEventViewCount castOther = (MarketEventViewCount) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this
                .getId().equals(castOther.getId())))
                && ((this.getIndustrySuperiorId() == castOther.getIndustrySuperiorId()) || (this
                        .getIndustrySuperiorId() != null
                        && castOther.getIndustrySuperiorId() != null && this.getIndustrySuperiorId().equals(
                        castOther.getIndustrySuperiorId())))
                && ((this.getIndustrySuperiorName() == castOther.getIndustrySuperiorName()) || (this
                        .getIndustrySuperiorName() != null
                        && castOther.getIndustrySuperiorName() != null && this.getIndustrySuperiorName().equals(
                        castOther.getIndustrySuperiorName())))
                && ((this.getCustId() == castOther.getCustId()) || (this.getCustId() != null
                        && castOther.getCustId() != null && this.getCustId().equals(castOther.getCustId())))
                && ((this.getCustName() == castOther.getCustName()) || (this.getCustName() != null
                        && castOther.getCustName() != null && this.getCustName().equals(castOther.getCustName())))
                && ((this.getSysCompanyId() == castOther.getSysCompanyId()) || (this.getSysCompanyId() != null
                        && castOther.getSysCompanyId() != null && this.getSysCompanyId().equals(
                        castOther.getSysCompanyId())))
                && ((this.getMarketEventId() == castOther.getMarketEventId()) || (this.getMarketEventId() != null
                        && castOther.getMarketEventId() != null && this.getMarketEventId().equals(
                        castOther.getMarketEventId())))
                && ((this.getOccurDate() == castOther.getOccurDate()) || (this.getOccurDate() != null
                        && castOther.getOccurDate() != null && this.getOccurDate().equals(castOther.getOccurDate())))
                && ((this.getMktevtSuperiorId() == castOther.getMktevtSuperiorId()) || (this.getMktevtSuperiorId() != null
                        && castOther.getMktevtSuperiorId() != null && this.getMktevtSuperiorId().equals(
                        castOther.getMktevtSuperiorId())))
                && ((this.getMktevtSuperiorName() == castOther.getMktevtSuperiorName()) || (this
                        .getMktevtSuperiorName() != null
                        && castOther.getMktevtSuperiorName() != null && this.getMktevtSuperiorName().equals(
                        castOther.getMktevtSuperiorName())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getIndustrySuperiorId() == null ? 0 : this.getIndustrySuperiorId().hashCode());
        result = 37 * result + (getIndustrySuperiorName() == null ? 0 : this.getIndustrySuperiorName().hashCode());
        result = 37 * result + (getCustId() == null ? 0 : this.getCustId().hashCode());
        result = 37 * result + (getCustName() == null ? 0 : this.getCustName().hashCode());
        result = 37 * result + (getSysCompanyId() == null ? 0 : this.getSysCompanyId().hashCode());
        result = 37 * result + (getMarketEventId() == null ? 0 : this.getMarketEventId().hashCode());
        result = 37 * result + (getOccurDate() == null ? 0 : this.getOccurDate().hashCode());
        result = 37 * result + (getMktevtSuperiorId() == null ? 0 : this.getMktevtSuperiorId().hashCode());
        result = 37 * result + (getMktevtSuperiorName() == null ? 0 : this.getMktevtSuperiorName().hashCode());
        return result;
    }

}