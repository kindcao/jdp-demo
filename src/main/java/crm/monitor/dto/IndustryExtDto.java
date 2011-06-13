package crm.monitor.dto;

import org.apache.commons.lang.StringUtils;

import crm.model.MonitorIndustry;

/**
 * @author Kind Cao
 * @version $Rev$, May 26, 2011 10:40:07 AM
 */
@SuppressWarnings("serial")
public class IndustryExtDto extends MonitorIndustry {

    private String publishDateStr;

    private String publishDateStrBegin;

    private String publishDateStrEnd;

    private Integer publishDateBegin;

    private Integer publishDateEnd;

    public String getPublishDateStr() {
        return publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
        if (StringUtils.isNotBlank(this.publishDateStr)) {
            this.setPublishDate(Integer.valueOf(publishDateStr.replaceAll("-", "")));
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

}
