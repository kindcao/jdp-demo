package crm.monitor.action;

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
import crm.model.IndustryNewsType;
import crm.model.MonitorIndustry;
import crm.model.MonitorIndustryView;
import crm.monitor.dto.IndustryExtDto;
import crm.monitor.service.IndustryService;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:30:56 PM
 */
@Scope("session")
@SuppressWarnings("serial")
public class IndustryAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(IndustryAction.class);

    private IndustryService industryService;

    private IndustryExtDto industryExtDto;

    private MonitorIndustryView industryView;

    public String showIndustryList() throws Exception {
        return "industry.list";
    }

    public String showIndustryInfo() throws Exception {
        if (null != industryExtDto && industryExtDto.getId() > 0) {
            industryView = (MonitorIndustryView) industryService.getObject(MonitorIndustryView.class, industryExtDto
                    .getId());
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
        PropertyUtils.copyProperties(obj, industryExtDto);
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

    public String getIndustryNewsType() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(IndustryNewsType.class.getName());
        if (null == map) {
            throw new RuntimeException("getIndustryNewsType map from servlet context is null.");
        }

        List<IndustryNewsType> list = new ArrayList<IndustryNewsType>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            IndustryNewsType value = (IndustryNewsType) map.get(key);
            list.add(value);
        }
        responseJsonData(list);
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

    public MonitorIndustryView getIndustryView() {
        return industryView;
    }

    public void setIndustryView(MonitorIndustryView industryView) {
        this.industryView = industryView;
    }

}
