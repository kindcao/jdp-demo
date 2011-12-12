package crm.model;

/**
 * MonitorPublishView entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MonitorPublishView implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer publishDate;

    private String publishDateStr;

    private Integer publishTime;

    private String publishTimeStr;

    private String subject;

    private String website;

    private String url;

    // Constructors

    /** default constructor */
    public MonitorPublishView() {
    }

    /** minimal constructor */
    public MonitorPublishView(Integer id, Integer publishDate, Integer publishTime, String subject, String website,
            String url) {
        this.id = id;
        this.publishDate = publishDate;
        this.publishTime = publishTime;
        this.subject = subject;
        this.website = website;
        this.url = url;
    }

    /** full constructor */
    public MonitorPublishView(Integer id, Integer publishDate, String publishDateStr, Integer publishTime,
            String publishTimeStr, String subject, String website, String url) {
        this.id = id;
        this.publishDate = publishDate;
        this.publishDateStr = publishDateStr;
        this.publishTime = publishTime;
        this.publishTimeStr = publishTimeStr;
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

    public String getPublishDateStr() {
        return this.publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public Integer getPublishTime() {
        return this.publishTime;
    }

    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishTimeStr() {
        return this.publishTimeStr;
    }

    public void setPublishTimeStr(String publishTimeStr) {
        this.publishTimeStr = publishTimeStr;
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

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MonitorPublishView))
            return false;
        MonitorPublishView castOther = (MonitorPublishView) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this
                .getId().equals(castOther.getId())))
                && ((this.getPublishDate() == castOther.getPublishDate()) || (this.getPublishDate() != null
                        && castOther.getPublishDate() != null && this.getPublishDate().equals(
                        castOther.getPublishDate())))
                && ((this.getPublishDateStr() == castOther.getPublishDateStr()) || (this.getPublishDateStr() != null
                        && castOther.getPublishDateStr() != null && this.getPublishDateStr().equals(
                        castOther.getPublishDateStr())))
                && ((this.getPublishTime() == castOther.getPublishTime()) || (this.getPublishTime() != null
                        && castOther.getPublishTime() != null && this.getPublishTime().equals(
                        castOther.getPublishTime())))
                && ((this.getPublishTimeStr() == castOther.getPublishTimeStr()) || (this.getPublishTimeStr() != null
                        && castOther.getPublishTimeStr() != null && this.getPublishTimeStr().equals(
                        castOther.getPublishTimeStr())))
                && ((this.getSubject() == castOther.getSubject()) || (this.getSubject() != null
                        && castOther.getSubject() != null && this.getSubject().equals(castOther.getSubject())))
                && ((this.getWebsite() == castOther.getWebsite()) || (this.getWebsite() != null
                        && castOther.getWebsite() != null && this.getWebsite().equals(castOther.getWebsite())))
                && ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null && castOther.getUrl() != null && this
                        .getUrl().equals(castOther.getUrl())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getPublishDate() == null ? 0 : this.getPublishDate().hashCode());
        result = 37 * result + (getPublishDateStr() == null ? 0 : this.getPublishDateStr().hashCode());
        result = 37 * result + (getPublishTime() == null ? 0 : this.getPublishTime().hashCode());
        result = 37 * result + (getPublishTimeStr() == null ? 0 : this.getPublishTimeStr().hashCode());
        result = 37 * result + (getSubject() == null ? 0 : this.getSubject().hashCode());
        result = 37 * result + (getWebsite() == null ? 0 : this.getWebsite().hashCode());
        result = 37 * result + (getUrl() == null ? 0 : this.getUrl().hashCode());
        return result;
    }

}