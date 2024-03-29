package crm.mktevt.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import crm.base.action.BaseAction;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.mktevt.dto.MktEvtCalExtDto;
import crm.mktevt.dto.MktEvtCountDto;
import crm.mktevt.dto.MktEvtCountExtDto;
import crm.mktevt.dto.MktEvtCountItemDto;
import crm.mktevt.dto.MktEvtExtDto;
import crm.mktevt.service.MktEvtService;
import crm.model.CustomerIndustry;
import crm.model.MarketEvent;
import crm.model.MarketEventType;
import crm.model.MarketEventView;
import crm.model.MarketEventViewCount;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:44:42 PM
 */
@Scope("session")
@SuppressWarnings("serial")
public class MktEvtAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MktEvtAction.class);

    private MktEvtService mktEvtService;

    private MktEvtExtDto mktEvt;

    private MarketEventView mktEvtView;

    //
    private MktEvtCalExtDto calExtDto;

    private MktEvtCountExtDto countExtDto;

    //
    private String customerIds;

    private String sysCompUseIds;

    private String contIds;

    private String occurDateStrBegin;

    private String occurDateStrEnd;

    private String beginTimeStr;

    private String endTimeStr;

    private String subject;

    private String status;

    private String compCustName;

    private Integer mktevtSuperiorId;

    //
    private int eventTypeId;

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
        //
        if (!currSysCompTypeIsR()) {
            if (StringUtils.isNotBlank(sysCompUseIds)) {
                sysCompUseIds += ",";
            } else {
                sysCompUseIds = "";
            }
            sysCompUseIds += getCurrSysUserChild();
        }
        if (StringUtils.isNotBlank(sysCompUseIds)) {
            map.put("sysCompUseIds", sysCompUseIds.split(","));
        }
        if (StringUtils.isNotBlank(compCustName)) {
            map.put("compCustName", compCustName);
        }
        if (StringUtils.isNotBlank(occurDateStrBegin)) {
            map.put("occurDateStrBegin", Integer.valueOf(occurDateStrBegin));
        }
        if (StringUtils.isNotBlank(occurDateStrEnd)) {
            map.put("occurDateStrEnd", Integer.valueOf(occurDateStrEnd));
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
        //
        reset();
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
        if (!currSysCompTypeIsR()) {
            String _compIds = StringUtils.isNotBlank(calExtDto.getCompId()) ? calExtDto.getCompId() + "," : "";
            if (!_compIds.contains(getCurrSysComp().getId().toString())) {
                _compIds += getCurrSysComp().getId();
            }
            calExtDto.setCompId(_compIds);
        }

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

    public String getMktEvtCountTab() throws Exception {
        JsonListResult jlr = new JsonListResult();
        //
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(CustomerIndustry.class.getName());
        if (null == map) {
            throw new RuntimeException("getCustIndu map from servlet context is null.");
        }
        //
        List<MktEvtCountDto> list = new ArrayList<MktEvtCountDto>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            CustomerIndustry value = (CustomerIndustry) map.get(key);
            if (null == value.getSuperiorId() || value.getSuperiorId() == 0 || value.getLevel().intValue() == 1) {
                MktEvtCountDto dto = new MktEvtCountDto();
                dto.setInduName(value.getName());
                dto.setInduId(value.getId());
                list.add(dto);
                //             
                if (!currSysCompTypeIsR()) {
                    String _compIds = StringUtils.isNotBlank(countExtDto.getSysCompIds()) ? countExtDto.getSysCompIds()
                            + "," : "";
                    if (!_compIds.contains(getCurrSysComp().getId().toString())) {
                        _compIds += getCurrSysComp().getId();
                    }
                    countExtDto.setSysCompIds(_compIds);
                }
                countExtDto.setIndustrySuperiorId(dto.getInduId());
                List<?> _numList = mktEvtService.findMktEvtCountTab(countExtDto);
                if (null != _numList) {
                    MktEvtCountItemDto itemDto = null;
                    Map<Integer, MktEvtCountItemDto> _map = new HashMap<Integer, MktEvtCountItemDto>();
                    for (Iterator<?> iterator2 = _numList.iterator(); iterator2.hasNext();) {
                        MarketEventViewCount ele = (MarketEventViewCount) iterator2.next();
                        if (!_map.containsKey(ele.getCustId())) {
                            itemDto = new MktEvtCountItemDto();
                            itemDto.setCustName(ele.getCustName());
                            _map.put(ele.getCustId(), itemDto);
                            //
                            dto.getItems().add(itemDto);
                        } else {
                            itemDto = _map.get(ele.getCustId());
                        }
                        //
                        switch (ele.getMktevtSuperiorId()) {
                        case 1:
                            itemDto.setVisitNum(ele.getNum());
                            dto.getSum().setVisitNum(dto.getSum().getVisitNum() + itemDto.getVisitNum());
                            break;
                        case 2:
                            itemDto.setTrainingNum(ele.getNum());
                            dto.getSum().setTrainingNum(dto.getSum().getTrainingNum() + itemDto.getTrainingNum());
                            break;
                        case 3:
                            itemDto.setActivityNum(ele.getNum());
                            dto.getSum().setActivityNum(dto.getSum().getActivityNum() + itemDto.getActivityNum());
                            break;
                        default:
                            itemDto.setOthersNum(ele.getNum());
                            dto.getSum().setOthersNum(dto.getSum().getOthersNum() + itemDto.getOthersNum());
                            break;
                        }
                    }
                    //
                    dto.setItemNum(dto.getItems().size() + 1);
                } else {
                    log.warn("findMktEvtCountTab list is null");
                }
            }
        }
        //            
        jlr.setRows(list);
        jlr.setTotal(list.size());
        responseJsonData(jlr);
        //
        countExtDto = new MktEvtCountExtDto();
        return NONE;
    }

    public String getMktEvtType() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(MarketEventType.class.getName());
        if (null == map) {
            throw new RuntimeException("getMarketEventType map from servlet context is null.");
        }

        List<MarketEventType> list = new ArrayList<MarketEventType>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            MarketEventType value = (MarketEventType) map.get(key);
            if (eventTypeId == 0 && (null == value.getSuperiorId() || value.getSuperiorId() == 0)) {
                list.add(value);
            }
            if (eventTypeId > 0 && null != value.getSuperiorId() && value.getSuperiorId().intValue() == eventTypeId) {
                list.add(value);
            }
        }
        responseJsonData(list);
        return NONE;
    }

    private String _showMktEvtInfo(String result) throws Exception {
        if (null != mktEvt && null != mktEvt.getId() && mktEvt.getId() > 0) {
            MarketEvent _mktEvt = (MarketEvent) mktEvtService.getObject(MarketEvent.class, mktEvt.getId());
            PropertyUtils.copyProperties(mktEvt, _mktEvt);
            mktEvtView = (MarketEventView) mktEvtService.getObject(MarketEventView.class, mktEvt.getId());
            //        
            // session.put(Constants.MARKET_EVENT_SESSION_KEY, _mktEvt);
            // session.put(Constants.MARKET_EVENT_VIEW_SESSION_KEY,
            // _mktEvtView);
        } else {
            log.error("mktEvt id is null");
            return null;
        }
        return result;
    }

    private void reset() {
        customerIds = "";
        sysCompUseIds = "";
        contIds = "";
        occurDateStrBegin = "";
        occurDateStrEnd = "";
        beginTimeStr = "";
        endTimeStr = "";
        subject = "";
        status = "";
        compCustName = "";
        mktevtSuperiorId = null;
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

    public MktEvtCountExtDto getCountExtDto() {
        return countExtDto;
    }

    public void setCountExtDto(MktEvtCountExtDto countExtDto) {
        this.countExtDto = countExtDto;
    }

    public String getOccurDateStrBegin() {
        return occurDateStrBegin;
    }

    public void setOccurDateStrBegin(String occurDateStrBegin) {
        this.occurDateStrBegin = occurDateStrBegin;
        if (StringUtils.isNotBlank(occurDateStrBegin)) {
            this.occurDateStrBegin = occurDateStrBegin.replaceAll("-", "");
        }
    }

    public String getOccurDateStrEnd() {
        return occurDateStrEnd;
    }

    public void setOccurDateStrEnd(String occurDateStrEnd) {
        this.occurDateStrEnd = occurDateStrEnd;
        if (StringUtils.isNotBlank(occurDateStrEnd)) {
            this.occurDateStrEnd = occurDateStrEnd.replaceAll("-", "");
        }
    }

    public MarketEventView getMktEvtView() {
        return mktEvtView;
    }

    public void setMktEvtView(MarketEventView mktEvtView) {
        this.mktEvtView = mktEvtView;
    }

    
    public int getEventTypeId() {
        return eventTypeId;
    }

    
    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }

}
