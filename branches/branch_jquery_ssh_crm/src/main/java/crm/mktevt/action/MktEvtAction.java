package crm.mktevt.action;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.dto.MktEvtExtDto;
import crm.json.JsonValidateResult;
import crm.mktevt.service.MktEvtService;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:44:42 PM
 */
@SuppressWarnings("serial")
public class MktEvtAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MktEvtAction.class);

    private MktEvtService mktEvtService;

    private MktEvtExtDto mktEvt;

    public String showMktEvtList() throws Exception {
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
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isBlank(getActionFlag())) {
            mktEvt.setId(null);
        }
        //        
        mktEvtService.saveOrUpdate(mktEvt);
        jvr.setSuccess(true);
        responseJsonData(jvr);
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

    public MktEvtExtDto getMktEvt() {
        return mktEvt;
    }

    public void setMktEvt(MktEvtExtDto mktEvt) {
        this.mktEvt = mktEvt;
    }

}
