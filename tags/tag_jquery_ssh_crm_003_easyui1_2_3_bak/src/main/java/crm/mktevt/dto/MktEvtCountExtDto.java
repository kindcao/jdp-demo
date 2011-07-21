package crm.mktevt.dto;

import org.apache.commons.lang.StringUtils;

import crm.model.MarketEventViewCount;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 9:48:01 AM
 */
@SuppressWarnings("serial")
public class MktEvtCountExtDto extends MarketEventViewCount {

    private String occurDateStart;

    private String occurDateEnd;

    private String sysCompIds;

    public String getOccurDateStart() {
        return occurDateStart;
    }

    public void setOccurDateStart(String occurDateStart) {
        this.occurDateStart = occurDateStart;
        if (StringUtils.isNotBlank(this.occurDateStart)) {
            this.occurDateStart = occurDateStart.replaceAll("-", "");
        }
    }

    public String getOccurDateEnd() {
        return occurDateEnd;
    }

    public void setOccurDateEnd(String occurDateEnd) {
        this.occurDateEnd = occurDateEnd;
        if (StringUtils.isNotBlank(this.occurDateEnd)) {
            this.occurDateEnd = occurDateEnd.replaceAll("-", "");
        }
    }

    public String getSysCompIds() {
        return sysCompIds;
    }

    public void setSysCompIds(String sysCompIds) {
        this.sysCompIds = sysCompIds;
        if (StringUtils.isNotBlank(sysCompIds)) {
            this.sysCompIds = Utils.fmtAndSortIds(sysCompIds);
        }
    }

}
