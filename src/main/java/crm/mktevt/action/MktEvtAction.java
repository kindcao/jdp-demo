package crm.mktevt.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.mktevt.service.MktEvtService;
import crm.model.MarketEvent;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:44:42 PM
 */
@SuppressWarnings("serial")
public class MktEvtAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MktEvtAction.class);

    private Integer eventTypeId;

    private MktEvtService mktEvtService;

    private MarketEvent mktEvt;

    public String showMktEvtList() throws Exception {
        // switch (eventTypeId) {
        // case 1: {
        // return "mktevt.visit.list";
        // }
        // case 2: {
        // return "mktevt.train.list";
        // }
        // case 3: {
        // return "mktevt.activity.list";
        // }
        // case 4: {
        // return "mktevt.others.list";
        // }
        // default:
        //            
        // }
        return "mktevt.list";
    }

    public String showMktEvtInfo() throws Exception {
        // switch (eventTypeId) {
        // case 1: {
        // return "mktevt.visit.info";
        // }
        // case 2: {
        // return "mktevt.train.info";
        // }
        // case 3: {
        // return "mktevt.activity.info";
        // }
        // case 4: {
        // return "mktevt.others.info";
        // }
        // default:
        //          
        // }
        return "mktevt.list";
    }

    public String saveMktEvtInfo() throws Exception {
        return NONE;
    }

    public String getMktEvtList() throws Exception {
        return NONE;
    }

    public String deleteMktEvt() throws Exception {
        return NONE;
    }

    public MktEvtService getMktEvtService() {
        return mktEvtService;
    }

    @Resource
    public void setMktEvtService(MktEvtService mktEvtService) {
        this.mktEvtService = mktEvtService;
    }

    public Integer getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(Integer eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

    public MarketEvent getMktEvt() {
        return mktEvt;
    }

    public void setMktEvt(MarketEvent mktEvt) {
        this.mktEvt = mktEvt;
    }

}
