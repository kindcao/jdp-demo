package crm.model;

/**
 * MonitorNews entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MonitorNews implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer publishDate;

    private String media;

    private Integer interviewDate;

    private String participant;

    private String reporter;

    private String url;

    private String subject;

    private String content;

    private String remark;

    private String picture;

    // Constructors

    /** default constructor */
    public MonitorNews() {
    }

    /** minimal constructor */
    public MonitorNews(Integer publishDate, String media) {
        this.publishDate = publishDate;
        this.media = media;
    }

    /** full constructor */
    public MonitorNews(Integer publishDate, String media, Integer interviewDate, String participant, String reporter,
            String url, String subject, String content, String remark, String picture) {
        this.publishDate = publishDate;
        this.media = media;
        this.interviewDate = interviewDate;
        this.participant = participant;
        this.reporter = reporter;
        this.url = url;
        this.subject = subject;
        this.content = content;
        this.remark = remark;
        this.picture = picture;
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

    public String getMedia() {
        return this.media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public Integer getInterviewDate() {
        return this.interviewDate;
    }

    public void setInterviewDate(Integer interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getParticipant() {
        return this.participant;
    }

    public void setParticipant(String participant) {
        this.participant = participant;
    }

    public String getReporter() {
        return this.reporter;
    }

    public void setReporter(String reporter) {
        this.reporter = reporter;
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

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

}