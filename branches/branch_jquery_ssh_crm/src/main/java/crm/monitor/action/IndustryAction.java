package crm.monitor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.MonitorIndustry;
import crm.model.MonitorIndustryView;
import crm.monitor.dto.IndustryExtDto;
import crm.monitor.service.IndustryService;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:30:56 PM
 */
@SuppressWarnings("serial")
public class IndustryAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(IndustryAction.class);

    private IndustryService industryService;

    private IndustryExtDto industryExtDto;

    public String showIndustryList() throws Exception {
        return "industry.list";
    }

    public String showIndustryInfo() throws Exception {
        if (null != industryExtDto && industryExtDto.getId() > 0) {
            session.put(Constants.MONITOR_INDUSTRY_VIEW_SESSION_KEY, industryService.getObject(
                    MonitorIndustryView.class, industryExtDto.getId()));
        } else {
            log.warn("industryExtDto is null or industryExtDto.getId() is 0");
        }
        return "industry.info";
    }

    public String getIndustryList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!currSysCompTypeIsR()) {
            map.put("sysCompId", Utils.fmtAndSortIds(getCurrSysComp().getId().toString()));
        }

        if (null != industryExtDto) {
            if (null != industryExtDto.getPublishDateBegin() && industryExtDto.getPublishDateBegin() > 0) {
                map.put("publishDateBegin", industryExtDto.getPublishDateBegin());
            }
            if (null != industryExtDto.getPublishDateEnd() && industryExtDto.getPublishDateEnd() > 0) {
                map.put("publishDateEnd", industryExtDto.getPublishDateEnd());
            }
            if (null != industryExtDto.getIndustryNewsTypeId() && industryExtDto.getIndustryNewsTypeId() > 0) {
                map.put("industryNewsTypeId", industryExtDto.getIndustryNewsTypeId());
            }
            if (null != industryExtDto.getCustomerId() && industryExtDto.getCustomerId() > 0) {
                map.put("customerId", industryExtDto.getCustomerId());
            }
            if (StringUtils.isNotBlank(industryExtDto.getSubject())) {
                map.put("subject", industryExtDto.getSubject());
            }
        }
        //
        JsonListResult jlr = new JsonListResult();
        int totalCount = industryService.getTotalCount(map);
        List<?> mktEvtList = industryService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(mktEvtList);
        responseJsonData(jlr);
        //
        industryExtDto = new IndustryExtDto();
        return NONE;
    }

    public String saveIndustryInfo() throws Exception {
        MonitorIndustry obj = new MonitorIndustry();
        BeanUtils.copyProperties(obj, industryExtDto);
        if (StringUtils.isBlank(getActionFlag())) {
            obj.setId(null);
        }
        JsonValidateResult jvr = new JsonValidateResult();
        industryService.saveOrUpdate(obj);
        jvr.setSuccess(true);
        responseJsonData(jvr);
        //      
        industryExtDto = new IndustryExtDto();
        return NONE;
    }

    public String deleteIndustry() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isNotBlank(getIds())) {
            industryService.deleteAll(new MonitorIndustry(), Utils.getIds(getIds()));
            jvr.setSuccess(true);
        } else {
            log.info("delete ids is null");
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    @Resource
    public void setIndustryService(IndustryService industryService) {
        this.industryService = industryService;
    }

    public IndustryExtDto getIndustryExtDto() {
        return industryExtDto;
    }

    public void setIndustryExtDto(IndustryExtDto industryExtDto) {
        this.industryExtDto = industryExtDto;
    }
}
