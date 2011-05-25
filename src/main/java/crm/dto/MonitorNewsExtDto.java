package crm.dto;

import org.apache.commons.lang.StringUtils;

import crm.model.MonitorNews;

/**
 * @author Kind Cao
 * @version $Rev$, May 25, 2011 10:51:18 AM
 */
@SuppressWarnings("serial")
public class MonitorNewsExtDto extends MonitorNews {

    private String publishDateStr;

    private String interviewDateStr;

    private String publishDateStrBegin;

    private String publishDateStrEnd;

    private String interviewDateStrBegin;

    private String interviewDateStrEnd;

    private Integer publishDateBegin;

    private Integer publishDateEnd;

    private Integer interviewDateBegin;

    private Integer interviewDateEnd;

    public String getPublishDateStr() {
        return publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
        if (StringUtils.isNotBlank(this.publishDateStr)) {
            this.setPublishDate(Integer.valueOf(publishDateStr.replaceAll("-", "")));
        }
    }

    public String getInterviewDateStr() {
        return interviewDateStr;
    }

    public void setInterviewDateStr(String interviewDateStr) {
        this.interviewDateStr = interviewDateStr;
        if (StringUtils.isNotBlank(this.interviewDateStr)) {
            this.setInterviewDate(Integer.valueOf(interviewDateStr.replaceAll("-", "")));
        }
    }

    public String getPublishDateStrBegin() {
        return publishDateStrBegin;
    }

    public void setPublishDateStrBegin(String publishDateStrBegin) {
        this.publishDateStrBegin = publishDateStrBegin;
        if (StringUtils.isNotBlank(this.publishDateStrBegin)) {
            this.publishDateBegin = Integer.valueOf(publishDateStrBegin.replaceAll("-", ""));
        }
    }

    public String getPublishDateStrEnd() {
        return publishDateStrEnd;
    }

    public void setPublishDateStrEnd(String publishDateStrEnd) {
        this.publishDateStrEnd = publishDateStrEnd;
        if (StringUtils.isNotBlank(this.publishDateStrEnd)) {
            this.publishDateEnd = Integer.valueOf(publishDateStrEnd.replaceAll("-", ""));
        }
    }

    public String getInterviewDateStrBegin() {
        return interviewDateStrBegin;
    }

    public void setInterviewDateStrBegin(String interviewDateStrBegin) {
        this.interviewDateStrBegin = interviewDateStrBegin;
        if (StringUtils.isNotBlank(this.interviewDateStrBegin)) {
            this.interviewDateBegin = Integer.valueOf(interviewDateStrBegin.replaceAll("-", ""));
        }
    }

    public String getInterviewDateStrEnd() {
        return interviewDateStrEnd;
    }

    public void setInterviewDateStrEnd(String interviewDateStrEnd) {
        this.interviewDateStrEnd = interviewDateStrEnd;
        if (StringUtils.isNotBlank(this.interviewDateStrEnd)) {
            this.interviewDateEnd = Integer.valueOf(interviewDateStrEnd.replaceAll("-", ""));
        }
    }

    public Integer getPublishDateBegin() {
        return publishDateBegin;
    }

    public void setPublishDateBegin(Integer publishDateBegin) {
        this.publishDateBegin = publishDateBegin;
    }

    public Integer getPublishDateEnd() {
        return publishDateEnd;
    }

    public void setPublishDateEnd(Integer publishDateEnd) {
        this.publishDateEnd = publishDateEnd;
    }

    public Integer getInterviewDateBegin() {
        return interviewDateBegin;
    }

    public void setInterviewDateBegin(Integer interviewDateBegin) {
        this.interviewDateBegin = interviewDateBegin;
    }

    public Integer getInterviewDateEnd() {
        return interviewDateEnd;
    }

    public void setInterviewDateEnd(Integer interviewDateEnd) {
        this.interviewDateEnd = interviewDateEnd;
    }
}
