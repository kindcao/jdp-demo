package crm.model;

/**
 * MonitorNewsView entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MonitorNewsView implements java.io.Serializable {

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

    private String publishDateStr;

    private String interviewDateStr;

    // Constructors

    /** default constructor */
    public MonitorNewsView() {
    }

    /** minimal constructor */
    public MonitorNewsView(Integer id, Integer publishDate, String media) {
        this.id = id;
        this.publishDate = publishDate;
        this.media = media;
    }

    /** full constructor */
    public MonitorNewsView(Integer id, Integer publishDate, String media, Integer interviewDate, String participant,
            String reporter, String url, String subject, String content, String remark, String picture,
            String publishDateStr, String interviewDateStr) {
        this.id = id;
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
        this.publishDateStr = publishDateStr;
        this.interviewDateStr = interviewDateStr;
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

    public String getPublishDateStr() {
        return this.publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public String getInterviewDateStr() {
        return this.interviewDateStr;
    }

    public void setInterviewDateStr(String interviewDateStr) {
        this.interviewDateStr = interviewDateStr;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MonitorNewsView))
            return false;
        MonitorNewsView castOther = (MonitorNewsView) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this
                .getId().equals(castOther.getId())))
                && ((this.getPublishDate() == castOther.getPublishDate()) || (this.getPublishDate() != null
                        && castOther.getPublishDate() != null && this.getPublishDate().equals(
                        castOther.getPublishDate())))
                && ((this.getMedia() == castOther.getMedia()) || (this.getMedia() != null
                        && castOther.getMedia() != null && this.getMedia().equals(castOther.getMedia())))
                && ((this.getInterviewDate() == castOther.getInterviewDate()) || (this.getInterviewDate() != null
                        && castOther.getInterviewDate() != null && this.getInterviewDate().equals(
                        castOther.getInterviewDate())))
                && ((this.getParticipant() == castOther.getParticipant()) || (this.getParticipant() != null
                        && castOther.getParticipant() != null && this.getParticipant().equals(
                        castOther.getParticipant())))
                && ((this.getReporter() == castOther.getReporter()) || (this.getReporter() != null
                        && castOther.getReporter() != null && this.getReporter().equals(castOther.getReporter())))
                && ((this.getUrl() == castOther.getUrl()) || (this.getUrl() != null && castOther.getUrl() != null && this
                        .getUrl().equals(castOther.getUrl())))
                && ((this.getSubject() == castOther.getSubject()) || (this.getSubject() != null
                        && castOther.getSubject() != null && this.getSubject().equals(castOther.getSubject())))
                && ((this.getContent() == castOther.getContent()) || (this.getContent() != null
                        && castOther.getContent() != null && this.getContent().equals(castOther.getContent())))
                && ((this.getRemark() == castOther.getRemark()) || (this.getRemark() != null
                        && castOther.getRemark() != null && this.getRemark().equals(castOther.getRemark())))
                && ((this.getPicture() == castOther.getPicture()) || (this.getPicture() != null
                        && castOther.getPicture() != null && this.getPicture().equals(castOther.getPicture())))
                && ((this.getPublishDateStr() == castOther.getPublishDateStr()) || (this.getPublishDateStr() != null
                        && castOther.getPublishDateStr() != null && this.getPublishDateStr().equals(
                        castOther.getPublishDateStr())))
                && ((this.getInterviewDateStr() == castOther.getInterviewDateStr()) || (this.getInterviewDateStr() != null
                        && castOther.getInterviewDateStr() != null && this.getInterviewDateStr().equals(
                        castOther.getInterviewDateStr())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getPublishDate() == null ? 0 : this.getPublishDate().hashCode());
        result = 37 * result + (getMedia() == null ? 0 : this.getMedia().hashCode());
        result = 37 * result + (getInterviewDate() == null ? 0 : this.getInterviewDate().hashCode());
        result = 37 * result + (getParticipant() == null ? 0 : this.getParticipant().hashCode());
        result = 37 * result + (getReporter() == null ? 0 : this.getReporter().hashCode());
        result = 37 * result + (getUrl() == null ? 0 : this.getUrl().hashCode());
        result = 37 * result + (getSubject() == null ? 0 : this.getSubject().hashCode());
        result = 37 * result + (getContent() == null ? 0 : this.getContent().hashCode());
        result = 37 * result + (getRemark() == null ? 0 : this.getRemark().hashCode());
        result = 37 * result + (getPicture() == null ? 0 : this.getPicture().hashCode());
        result = 37 * result + (getPublishDateStr() == null ? 0 : this.getPublishDateStr().hashCode());
        result = 37 * result + (getInterviewDateStr() == null ? 0 : this.getInterviewDateStr().hashCode());
        return result;
    }

}