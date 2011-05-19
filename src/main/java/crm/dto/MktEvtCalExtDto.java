package crm.dto;

import crm.model.MarketEventViewCal;

/**
 * @author Kind Cao
 * @version $Rev$, May 19, 2011 10:39:16 AM
 */
public class MktEvtCalExtDto extends MarketEventViewCal {

    private boolean year;

    
    public boolean isYear() {
        return year;
    }

    
    public void setYear(boolean year) {
        this.year = year;
    }


}
