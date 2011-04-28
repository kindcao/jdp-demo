package crm.model;

/**
 * MonitorPublish entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MonitorPublish implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer publishDate;

    private Integer publishTime;

    private String subject;

    private String website;

    private String url;

    // Constructors

    /** default constructor */
    public MonitorPublish() {
    }

    /** full constructor */
    public MonitorPublish(Integer publishDate, Integer publishTime, String subject, String website, String url) {
        this.publishDate = publishDate;
        this.publishTime = publishTime;
        this.subject = subject;
        this.website = website;
        this.url = url;
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

    public Integer getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}