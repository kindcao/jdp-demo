package crm.model;

/**
 * MonitorIndustryView entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MonitorIndustryView implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer publishDate;

    private Integer industryNewsTypeId;

    private Integer customerId;

    private String url;

    private String subject;

    private String content;

    private String publishDateStr;

    private String industryNewsTypeName;

    private String custName;

    // Constructors

    /** default constructor */
    public MonitorIndustryView() {
    }

    /** minimal constructor */
    public MonitorIndustryView(Integer id, Integer publishDate, Integer industryNewsTypeId, Integer customerId,
            String subject, String industryNewsTypeName, String custName) {
        this.id = id;
        this.publishDate = publishDate;
        this.industryNewsTypeId = industryNewsTypeId;
        this.customerId = customerId;
        this.subject = subject;
        this.industryNewsTypeName = industryNewsTypeName;
        this.custName = custName;
    }

    /** full constructor */
    public MonitorIndustryView(Integer id, Integer publishDate, Integer industryNewsTypeId, Integer customerId,
            String url, String subject, String content, String publishDateStr, String industryNewsTypeName,
            String custName) {
        this.id = id;
        this.publishDate = publishDate;
        this.industryNewsTypeId = industryNewsTypeId;
        this.customerId = customerId;
        this.url = url;
        this.subject = subject;
        this.content = content;
        this.publishDateStr = publishDateStr;
        this.industryNewsTypeName = industryNewsTypeName;
        this.custName = custName;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPublishDate() {
        return this.publishDate;
    }

    public void setPublishDate(Integer publishDate) {
        this.publishDate = publishDate;
    }

    public Integer getIndustryNewsTypeId() {
        return this.industryNewsTypeId;
    }

    public void setIndustryNewsTypeId(Integer industryNewsTypeId) {
        this.industryNewsTypeId = industryNewsTypeId;
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublishDateStr() {
        return this.publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public String getIndustryNewsTypeName() {
        return industryNewsTypeName;
    }

    public void setIndustryNewsTypeName(String industryNewsTypeName) {
        this.industryNewsTypeName = industryNewsTypeName;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MonitorIndustryView))
            return false;
        MonitorIndustryView castOther = (MonitorIndustryView) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this
                .getId().equals(castOther.getId())))
                && ((this.getPublishDate() == castOther.getPublishDate()) || (this.getPublishDate() != null
                        && castOther.getPublishDate() != null && this.getPublishDate().equals(
                        castOther.getPublishDate())))
                && ((this.getIndustryNewsTypeId() == castOther.getIndustryNewsTypeId()) || (this
                        .getIndustryNewsTypeId() != null
                        && castOther.getIndustryNewsTypeId() != null && this.getIndustryNewsTypeId().equals(
                        castOther.getIndustryNewsTypeId())))
                && ((this.getCustomerId() == castOther.getCustomerId()) || (this.getCustomerId() != null
                        && castOther.getCustomerId() != null && this.getCustomerId().equals(castOther.getCustomerId())))
                && ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null && castOther.getUrl() != null && this
                        .getUrl().equals(castOther.getUrl())))
                && ((this.getSubject() == castOther.getSubject()) || (this.getSubject() != null
                        && castOther.getSubject() != null && this.getSubject().equals(castOther.getSubject())))
                && ((this.getContent() == castOther.getContent()) || (this.getContent() != null
                        && castOther.getContent() != null && this.getContent().equals(castOther.getContent())))
                && ((this.getPublishDateStr() == castOther.getPublishDateStr()) || (this.getPublishDateStr() != null
                        && castOther.getPublishDateStr() != null && this.getPublishDateStr().equals(
                        castOther.getPublishDateStr())))
                && ((this.getIndustryNewsTypeName() == castOther.getIndustryNewsTypeName()) || (this
                        .getIndustryNewsTypeName() != null
                        && castOther.getIndustryNewsTypeName() != null && this.getIndustryNewsTypeName().equals(
                        castOther.getIndustryNewsTypeName())))
                && ((this.getCustName() == castOther.getCustName()) || (this.getCustName() != null
                        && castOther.getCustName() != null && this.getCustName().equals(castOther.getCustName())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getPublishDate() == null ? 0 : this.getPublishDate().hashCode());
        result = 37 * result + (getIndustryNewsTypeId() == null ? 0 : this.getIndustryNewsTypeId().hashCode());
        result = 37 * result + (getCustomerId() == null ? 0 : this.getCustomerId().hashCode());
        result = 37 * result + (getUrl() == null ? 0 : this.getUrl().hashCode());
        result = 37 * result + (getSubject() == null ? 0 : this.getSubject().hashCode());
        result = 37 * result + (getContent() == null ? 0 : this.getContent().hashCode());
        result = 37 * result + (getPublishDateStr() == null ? 0 : this.getPublishDateStr().hashCode());
        result = 37 * result + (getIndustryNewsTypeName() == null ? 0 : this.getIndustryNewsTypeName().hashCode());
        result = 37 * result + (getCustName() == null ? 0 : this.getCustName().hashCode());
        return result;
    }

}