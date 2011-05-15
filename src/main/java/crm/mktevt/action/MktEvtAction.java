package crm.mktevt.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.dto.MktEvtExtDto;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.mktevt.service.MktEvtService;
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

    private Integer mktevtSuperiorId;

    public String showMktEvtList() throws Exception {
        return "mktevt.list";
    }

    public String showMktEvtInfo() throws Exception {
        return "mktevt.info";
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
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();

        if (StringUtils.isNotBlank(customerIds)) {
            map.put("customerIds", customerIds);
        }
        if (StringUtils.isNotBlank(sysCompUseIds)) {
            map.put("sysCompUseIds", sysCompUseIds);
        }
        if (StringUtils.isNotBlank(contIds)) {
            map.put("contIds", contIds);
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

        //
        int totalCount = mktEvtService.getTotalCount(map);
        List<?> mktEvtList = mktEvtService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(mktEvtList);
        responseJsonData(jlr);
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

}
