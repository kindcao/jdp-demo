package crm.cust.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.cust.service.MarketEventService;
import crm.model.MarketEvent;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:44:42 PM
 */
@SuppressWarnings("serial")
public class MarketEventAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MarketEventAction.class);

    private Integer eventTypeId;

    private MarketEventService marketEventService;

    private MarketEvent marketEvent;

    public String showMarketEventList() throws Exception {
        return "marketevent.list";
    }

    public String showMarketEventInfo() throws Exception {
        return "marketevent.info";
    }

    public String saveMarketEventInfo() throws Exception {
        return NONE;
    }

    public String getMarketEventList() throws Exception {
        return NONE;
    }

    public String deleteMarketEvent() throws Exception {
        return NONE;
    }

    public MarketEventService getMarketEventService() {
        return marketEventService;
    }

    @Resource
    public void setMarketEventService(MarketEventService marketEventService) {
        this.marketEventService = marketEventService;
    }

    public MarketEvent getMarketEvent() {
        return marketEvent;
    }

    public void setMarketEvent(MarketEvent marketEvent) {
        this.marketEvent = marketEvent;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

}
