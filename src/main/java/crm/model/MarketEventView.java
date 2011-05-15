package crm.model;

import org.apache.commons.lang.StringUtils;

/**
 * MarketEventViewId entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEventView implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer occurDate;

    private String occurDateStr;

    private Integer beginTime;

    private String beginTimeStr;

    private Integer endTime;

    private String endTimeStr;

    private String subject;

    private Integer mktevtSuperiorId;

    private Integer mktevtId;

    private String mktevtName;

    private String custId;

    private String custName;

    private String contId;

    private String contName;

    private String sysCompUserId;

    private String sysCompUserName;

    // Constructors

    /** default constructor */
    public MarketEventView() {
    }

    /** minimal constructor */
    public MarketEventView(Integer id, Integer occurDate, Integer beginTime, Integer endTime, Integer mktevtId,
            String mktevtName) {
        this.id = id;
        this.occurDate = occurDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.mktevtId = mktevtId;
        this.mktevtName = mktevtName;
    }

    /** full constructor */
    public MarketEventView(Integer id, Integer occurDate, String occurDateStr, Integer beginTime, String beginTimeStr,
            Integer endTime, String endTimeStr, String subject, Integer mktevtSuperiorId, Integer mktevtId,
            String mktevtName, String custId, String custName, String contId, String contName, String sysCompUserId,
            String sysCompUserName) {
        this.id = id;
        this.occurDate = occurDate;
        this.occurDateStr = occurDateStr;
        this.beginTime = beginTime;
        this.beginTimeStr = beginTimeStr;
        this.endTime = endTime;
        this.endTimeStr = endTimeStr;
        this.subject = subject;
        this.mktevtSuperiorId = mktevtSuperiorId;
        this.mktevtId = mktevtId;
        this.mktevtName = mktevtName;
        this.custId = custId;
        this.custName = custName;
        this.contId = contId;
        this.contName = contName;
        this.sysCompUserId = sysCompUserId;
        this.sysCompUserName = sysCompUserName;
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

    public Integer getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public String getBeginTimeStr() {
        return this.beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
    }

    public Integer getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public String getEndTimeStr() {
        return this.endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
    }

    public String getSubject() {
        if (StringUtils.isNotBlank(subject)) {
            return StringUtils.abbreviate(subject, 20);
        }
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMktevtSuperiorId() {
        return this.mktevtSuperiorId;
    }

    public void setMktevtSuperiorId(Integer mktevtSuperiorId) {
        this.mktevtSuperiorId = mktevtSuperiorId;
    }

    public Integer getMktevtId() {
        return this.mktevtId;
    }

    public void setMktevtId(Integer mktevtId) {
        this.mktevtId = mktevtId;
    }

    public String getMktevtName() {
        return this.mktevtName;
    }

    public void setMktevtName(String mktevtName) {
        this.mktevtName = mktevtName;
    }

    public String getCustId() {
        return this.custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return this.custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getContId() {
        return this.contId;
    }

    public void setContId(String contId) {
        this.contId = contId;
    }

    public String getContName() {
        return this.contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getSysCompUserId() {
        return this.sysCompUserId;
    }

    public void setSysCompUserId(String sysCompUserId) {
        this.sysCompUserId = sysCompUserId;
    }

    public String getSysCompUserName() {
        return this.sysCompUserName;
    }

    public void setSysCompUserName(String sysCompUserName) {
        this.sysCompUserName = sysCompUserName;
    }

    public boolean equals(Object other) {
        if ((this == other))
            return true;
        if ((other == null))
            return false;
        if (!(other instanceof MarketEventView))
            return false;
        MarketEventView castOther = (MarketEventView) other;

        return ((this.getId() == castOther.getId()) || (this.getId() != null && castOther.getId() != null && this
                .getId().equals(castOther.getId())))
                && ((this.getOccurDate() == castOther.getOccurDate()) || (this.getOccurDate() != null
                        && castOther.getOccurDate() != null && this.getOccurDate().equals(castOther.getOccurDate())))
                && ((this.getOccurDateStr() == castOther.getOccurDateStr()) || (this.getOccurDateStr() != null
                        && castOther.getOccurDateStr() != null && this.getOccurDateStr().equals(
                        castOther.getOccurDateStr())))
                && ((this.getBeginTime() == castOther.getBeginTime()) || (this.getBeginTime() != null
                        && castOther.getBeginTime() != null && this.getBeginTime().equals(castOther.getBeginTime())))
                && ((this.getBeginTimeStr() == castOther.getBeginTimeStr()) || (this.getBeginTimeStr() != null
                        && castOther.getBeginTimeStr() != null && this.getBeginTimeStr().equals(
                        castOther.getBeginTimeStr())))
                && ((this.getEndTime() == castOther.getEndTime()) || (this.getEndTime() != null
                        && castOther.getEndTime() != null && this.getEndTime().equals(castOther.getEndTime())))
                && ((this.getEndTimeStr() == castOther.getEndTimeStr()) || (this.getEndTimeStr() != null
                        && castOther.getEndTimeStr() != null && this.getEndTimeStr().equals(castOther.getEndTimeStr())))
                && ((this.getSubject() == castOther.getSubject()) || (this.getSubject() != null
                        && castOther.getSubject() != null && this.getSubject().equals(castOther.getSubject())))
                && ((this.getMktevtSuperiorId() == castOther.getMktevtSuperiorId()) || (this.getMktevtSuperiorId() != null
                        && castOther.getMktevtSuperiorId() != null && this.getMktevtSuperiorId().equals(
                        castOther.getMktevtSuperiorId())))
                && ((this.getMktevtId() == castOther.getMktevtId()) || (this.getMktevtId() != null
                        && castOther.getMktevtId() != null && this.getMktevtId().equals(castOther.getMktevtId())))
                && ((this.getMktevtName() == castOther.getMktevtName()) || (this.getMktevtName() != null
                        && castOther.getMktevtName() != null && this.getMktevtName().equals(castOther.getMktevtName())))
                && ((this.getCustId() == castOther.getCustId()) || (this.getCustId() != null
                        && castOther.getCustId() != null && this.getCustId().equals(castOther.getCustId())))
                && ((this.getCustName() == castOther.getCustName()) || (this.getCustName() != null
                        && castOther.getCustName() != null && this.getCustName().equals(castOther.getCustName())))
                && ((this.getContId() == castOther.getContId()) || (this.getContId() != null
                        && castOther.getContId() != null && this.getContId().equals(castOther.getContId())))
                && ((this.getContName() == castOther.getContName()) || (this.getContName() != null
                        && castOther.getContName() != null && this.getContName().equals(castOther.getContName())))
                && ((this.getSysCompUserId() == castOther.getSysCompUserId()) || (this.getSysCompUserId() != null
                        && castOther.getSysCompUserId() != null && this.getSysCompUserId().equals(
                        castOther.getSysCompUserId())))
                && ((this.getSysCompUserName() == castOther.getSysCompUserName()) || (this.getSysCompUserName() != null
                        && castOther.getSysCompUserName() != null && this.getSysCompUserName().equals(
                        castOther.getSysCompUserName())));
    }

    public int hashCode() {
        int result = 17;

        result = 37 * result + (getId() == null ? 0 : this.getId().hashCode());
        result = 37 * result + (getOccurDate() == null ? 0 : this.getOccurDate().hashCode());
        result = 37 * result + (getOccurDateStr() == null ? 0 : this.getOccurDateStr().hashCode());
        result = 37 * result + (getBeginTime() == null ? 0 : this.getBeginTime().hashCode());
        result = 37 * result + (getBeginTimeStr() == null ? 0 : this.getBeginTimeStr().hashCode());
        result = 37 * result + (getEndTime() == null ? 0 : this.getEndTime().hashCode());
        result = 37 * result + (getEndTimeStr() == null ? 0 : this.getEndTimeStr().hashCode());
        result = 37 * result + (getSubject() == null ? 0 : this.getSubject().hashCode());
        result = 37 * result + (getMktevtSuperiorId() == null ? 0 : this.getMktevtSuperiorId().hashCode());
        result = 37 * result + (getMktevtId() == null ? 0 : this.getMktevtId().hashCode());
        result = 37 * result + (getMktevtName() == null ? 0 : this.getMktevtName().hashCode());
        result = 37 * result + (getCustId() == null ? 0 : this.getCustId().hashCode());
        result = 37 * result + (getCustName() == null ? 0 : this.getCustName().hashCode());
        result = 37 * result + (getContId() == null ? 0 : this.getContId().hashCode());
        result = 37 * result + (getContName() == null ? 0 : this.getContName().hashCode());
        result = 37 * result + (getSysCompUserId() == null ? 0 : this.getSysCompUserId().hashCode());
        result = 37 * result + (getSysCompUserName() == null ? 0 : this.getSysCompUserName().hashCode());
        return result;
    }

}