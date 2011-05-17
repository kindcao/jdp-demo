package crm.dto;

import org.apache.commons.lang.StringUtils;

import crm.model.MarketEvent;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 14, 2011 9:23:34 PM
 */
@SuppressWarnings("serial")
public class MktEvtExtDto extends MarketEvent {

    private String customerIds;

    private String sysCompUseIds;

    private String contIds;

    private String sysCompIds;

    private String occurDateStr;

    private String beginTimeStr;

    private String endTimeStr;

    public String getSysCompUseIds() {
        return sysCompUseIds;
    }

    public void setSysCompUseIds(String sysCompUseIds) {
        this.sysCompUseIds = sysCompUseIds;
    }

    public String getSysCompIds() {
        return sysCompIds;
    }

    public void setSysCompIds(String sysCompIds) {
        this.sysCompIds = sysCompIds;
    }

    public String getContIds() {
        return contIds;
    }

    public void setContIds(String contIds) {
        this.contIds = contIds;
    }

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
    }

    public String getOccurDateStr() {
        return occurDateStr;
    }

    public void setOccurDateStr(String occurDateStr) {
        this.occurDateStr = occurDateStr;
        if (StringUtils.isNotBlank(this.occurDateStr)) {
            this.setOccurDate(Integer.valueOf(occurDateStr.replaceAll("-", "")));
        }
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
        if (StringUtils.isNotBlank(this.beginTimeStr)) {
            this.setBeginTime(Integer.valueOf(beginTimeStr.replaceAll(":", "")));
        }
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
        if (StringUtils.isNotBlank(this.endTimeStr)) {
            this.setEndTime(Integer.valueOf(endTimeStr.replaceAll(":", "")));
        }
    }

}
