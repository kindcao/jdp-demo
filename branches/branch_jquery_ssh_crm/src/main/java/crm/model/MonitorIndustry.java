package crm.model;

/**
 * MonitorIndustry entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MonitorIndustry implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer publishDate;

    private Integer industryNewsTypeId;

    private Integer customerId;

    private String url;

    private String subject;

    private String content;

    // Constructors

    /** default constructor */
    public MonitorIndustry() {
    }

    /** minimal constructor */
    public MonitorIndustry(Integer publishDate, Integer industryNewsTypeId, Integer customerId, String subject) {
        this.publishDate = publishDate;
        this.industryNewsTypeId = industryNewsTypeId;
        this.customerId = customerId;
        this.subject = subject;
    }

    /** full constructor */
    public MonitorIndustry(Integer publishDate, Integer industryNewsTypeId, Integer customerId, String url,
            String subject, String content) {
        this.publishDate = publishDate;
        this.industryNewsTypeId = industryNewsTypeId;
        this.customerId = customerId;
        this.url = url;
        this.subject = subject;
        this.content = content;
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

}