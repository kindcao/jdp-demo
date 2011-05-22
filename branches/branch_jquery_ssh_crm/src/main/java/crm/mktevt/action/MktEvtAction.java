package crm.mktevt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.dto.MktEvtCalExtDto;
import crm.dto.MktEvtExtDto;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.mktevt.service.MktEvtService;
import crm.model.MarketEvent;
import crm.model.MarketEventView;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:44:42 PM
 */
@SuppressWarnings("serial")
public class MktEvtAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MktEvtAction.class);

    private MktEvtService mktEvtService;

    private MktEvtExtDto mktEvt;

    private String customerIds;

    private String sysCompUseIds;

    private String contIds;

    private String occurDateStr;

    private String beginTimeStr;

    private String endTimeStr;

    private String subject;

    private String status;

    private String compCustName;

    private Integer mktevtSuperiorId;

    //
    private MktEvtCalExtDto calExtDto;

    public String showMktEvtList() throws Exception {
        return "mktevt.list";
    }

    public String showMktEvtInfo() throws Exception {
        return _showMktEvtInfo("mktevt.info");
    }

    public String showMktEvtCal() throws Exception {
        return "mktevt.cal";
    }

    public String showMktEvtCalDtl() throws Exception {
        return _showMktEvtInfo("mktevt.cal.dtl");
    }

    public String showMktEvtCount() throws Exception {
        return "mktevt.count";
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
        //
        mktEvt = new MktEvtExtDto();
        return NONE;
    }

    public String getMktEvtList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(sysCompUseIds)) {
            map.put("sysCompUseIds", sysCompUseIds);
        }
        if (StringUtils.isNotBlank(compCustName)) {
            map.put("compCustName", compCustName);
        }
        if (StringUtils.isNotBlank(occurDateStr)) {
            map.put("occurDate", Integer.valueOf(occurDateStr));
        }
        if (StringUtils.isNotBlank(beginTimeStr)) {
            map.put("beginTime", Integer.valueOf(beginTimeStr));
        }
        if (StringUtils.isNotBlank(endTimeStr)) {
            map.put("endTime", Integer.valueOf(endTimeStr));
        }
        if (StringUtils.isNotBlank(subject)) {
            map.put("subject", subject);
        }
        if (null != mktevtSuperiorId && mktevtSuperiorId > 0) {
            map.put("mktevtSuperiorId", mktevtSuperiorId);
        }
        if (StringUtils.isNotBlank(status)) {
            map.put("status", status);
        }
        //
        int totalCount = mktEvtService.getTotalCount(map);
        List<?> mktEvtList = mktEvtService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(mktEvtList);
        responseJsonData(jlr);
        return NONE;
    }

    public String deleteMktEvt() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isNotBlank(getIds())) {
            mktEvtService.deleteAll(new MarketEvent(), Utils.getIds(getIds()));
            jvr.setSuccess(true);
        } else {
            log.info("delete ids is null");
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String getMktEvtCal() throws Exception {
        JsonListResult jlr = new JsonListResult();
        List<?> list = mktEvtService.findMktEvtCal(calExtDto);
        if (null != list && list.size() > 0) {
            jlr.setTotal(list.size());
            jlr.setRows(list);
        }
        responseJsonData(jlr);
        //
        calExtDto = new MktEvtCalExtDto();
        return NONE;
    }

    private String _showMktEvtInfo(String result) throws Exception {
        if (null != mktEvt && null != mktEvt.getId() && mktEvt.getId() > 0) {
            MarketEvent _mktEvt = (MarketEvent) mktEvtService.getObject(MarketEvent.class, mktEvt.getId());
            MarketEventView _mktEvtView = (MarketEventView) mktEvtService.getObject(MarketEventView.class, mktEvt
                    .getId());
            //        
            session.put(Constants.MARKET_EVENT_SESSION_KEY, _mktEvt);
            session.put(Constants.MARKET_EVENT_VIEW_SESSION_KEY, _mktEvtView);
        } else {
            log.error("mktEvt id is null");
            return null;
        }
        return result;
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

    public String getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(String customerIds) {
        this.customerIds = customerIds;
        if (StringUtils.isNotBlank(customerIds)) {
            this.customerIds = Utils.fmtAndSortIds(customerIds);
        }
    }

    public String getSysCompUseIds() {
        return sysCompUseIds;
    }

    public void setSysCompUseIds(String sysCompUseIds) {
        this.sysCompUseIds = sysCompUseIds;
        if (StringUtils.isNotBlank(sysCompUseIds)) {
            this.sysCompUseIds = Utils.fmtAndSortIds(sysCompUseIds);
        }
    }

    public String getContIds() {
        return contIds;
    }

    public void setContIds(String contIds) {
        this.contIds = contIds;
        if (StringUtils.isNotBlank(contIds)) {
            this.contIds = Utils.fmtAndSortIds(contIds);
        }
    }

    public String getOccurDateStr() {
        return occurDateStr;
    }

    public void setOccurDateStr(String occurDateStr) {
        this.occurDateStr = occurDateStr;
        if (StringUtils.isNotBlank(occurDateStr)) {
            this.occurDateStr = occurDateStr.replaceAll("-", "");
        }
    }

    public String getBeginTimeStr() {
        return beginTimeStr;
    }

    public void setBeginTimeStr(String beginTimeStr) {
        this.beginTimeStr = beginTimeStr;
        if (StringUtils.isNotBlank(beginTimeStr)) {
            this.beginTimeStr = beginTimeStr.replaceAll(":", "");
        }
    }

    public String getEndTimeStr() {
        return endTimeStr;
    }

    public void setEndTimeStr(String endTimeStr) {
        this.endTimeStr = endTimeStr;
        if (StringUtils.isNotBlank(endTimeStr)) {
            this.endTimeStr = endTimeStr.replaceAll(":", "");
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Integer getMktevtSuperiorId() {
        return mktevtSuperiorId;
    }

    public void setMktevtSuperiorId(Integer mktevtSuperiorId) {
        this.mktevtSuperiorId = mktevtSuperiorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompCustName() {
        return compCustName;
    }

    public void setCompCustName(String compCustName) {
        this.compCustName = compCustName;
    }

    public MktEvtCalExtDto getCalExtDto() {
        return calExtDto;
    }

    public void setCalExtDto(MktEvtCalExtDto calExtDto) {
        this.calExtDto = calExtDto;
    }

}
