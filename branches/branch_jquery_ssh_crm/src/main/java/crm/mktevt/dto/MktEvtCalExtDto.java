package crm.mktevt.dto;

import org.apache.commons.lang.StringUtils;

import crm.model.MarketEventViewCal;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 19, 2011 10:39:16 AM
 */
@SuppressWarnings("serial")
public class MktEvtCalExtDto extends MarketEventViewCal {

    private boolean year;

    public boolean isYear() {
        return year;
    }

    public void setYear(boolean year) {
        this.year = year;
    }

    @Override
    public String getCompId() {
        if (StringUtils.isNotBlank(super.getCompId())) {
            return Utils.fmtAndSortIds(super.getCompId());
        }
        return super.getCompId();
    }

}
