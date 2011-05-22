package crm.model;

/**
 * MarketEventViewCalId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventViewCal implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer occurDate;

    private String occurDateStr;

    private String status;

    private Integer mktevtSuperiorId;

    private String mktevtSuperiorName;

    private String compId;

    private String custId;

    private String compCustName;

    // Constructors

    /** default constructor */
    public MarketEventViewCal() {
    }

    /** minimal constructor */
    public MarketEventViewCal(Integer id, Integer occurDate, String status, String mktevtSuperiorName) {
        this.id = id;
        this.occurDate = occurDate;
        this.status = status;
        this.mktevtSuperiorName = mktevtSuperiorName;
    }

    /** full constructor */
    public MarketEventViewCal(Integer id, Integer occurDate, String occurDateStr, String status,
            Integer mktevtSuperiorId, String mktevtSuperiorName, String compId, String custId, String compCustName) {
        this.id = id;
        this.occurDate = occurDate;
        this.occurDateStr = occurDateStr;
        this.status = status;
        this.mktevtSuperiorId = mktevtSuperiorId;
        this.mktevtSuperiorName = mktevtSuperiorName;
        this.compId = compId;
        this.custId = custId;
        this.compCustName = compCustName;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(Integer occurDate) {
        this.occurDate = occurDate;
    }

    public String getOccurDateStr() {
        return this.occurDateStr;
    }

    public void setOccurDateStr(String occurDateStr) {
        this.occurDateStr = occurDateStr;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getCompId() {
        return this.compId;
    }

    public void setCompId(String compId) {
        this.compId = compId;
    }

    public String getCustId() {
        return this.custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCompCustName() {
        return this.compCustName;
    }

    public void setCompCustName(String compCustName) {
        this.compCustName = compCustName;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MarketEventViewCal))
            return false;
        MarketEventViewCal castOther = (MarketEventViewCal) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this
                .getId().equals(castOther.getId())))
                && ((this.getOccurDate() == castOther.getOccurDate()) || (this.getOccurDate() != null
                        && castOther.getOccurDate() != null && this.getOccurDate().equals(castOther.getOccurDate())))
                && ((this.getOccurDateStr() == castOther.getOccurDateStr()) || (this.getOccurDateStr() != null
                        && castOther.getOccurDateStr() != null && this.getOccurDateStr().equals(
                        castOther.getOccurDateStr())))
                && ((this.getStatus() == castOther.getStatus()) || (this.getStatus() != null
                        && castOther.getStatus() != null && this.getStatus().equals(castOther.getStatus())))
                && ((this.getMktevtSuperiorId() == castOther.getMktevtSuperiorId()) || (this.getMktevtSuperiorId() != null
                        && castOther.getMktevtSuperiorId() != null && this.getMktevtSuperiorId().equals(
                        castOther.getMktevtSuperiorId())))
                && ((this.getMktevtSuperiorName() == castOther.getMktevtSuperiorName()) || (this
                        .getMktevtSuperiorName() != null
                        && castOther.getMktevtSuperiorName() != null && this.getMktevtSuperiorName().equals(
                        castOther.getMktevtSuperiorName())))
                && ((this.getCompId() == castOther.getCompId()) || (this.getCompId() != null
                        && castOther.getCompId() != null && this.getCompId().equals(castOther.getCompId())))
                && ((this.getCustId() == castOther.getCustId()) || (this.getCustId() != null
                        && castOther.getCustId() != null && this.getCustId().equals(castOther.getCustId())))
                && ((this.getCompCustName() == castOther.getCompCustName()) || (this.getCompCustName() != null
                        && castOther.getCompCustName() != null && this.getCompCustName().equals(
                        castOther.getCompCustName())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getOccurDate() == null ? 0 : this.getOccurDate().hashCode());
        result = 37 * result + (getOccurDateStr() == null ? 0 : this.getOccurDateStr().hashCode());
        result = 37 * result + (getStatus() == null ? 0 : this.getStatus().hashCode());
        result = 37 * result + (getMktevtSuperiorId() == null ? 0 : this.getMktevtSuperiorId().hashCode());
        result = 37 * result + (getMktevtSuperiorName() == null ? 0 : this.getMktevtSuperiorName().hashCode());
        result = 37 * result + (getCompId() == null ? 0 : this.getCompId().hashCode());
        result = 37 * result + (getCustId() == null ? 0 : this.getCustId().hashCode());
        result = 37 * result + (getCompCustName() == null ? 0 : this.getCompCustName().hashCode());
        return result;
    }

}