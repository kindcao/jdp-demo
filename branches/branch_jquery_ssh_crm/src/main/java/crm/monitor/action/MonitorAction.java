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
import crm.dto.MonitorNewsExtDto;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.MonitorNews;
import crm.model.MonitorNewsView;
import crm.model.MonitorPublishView;
import crm.monitor.service.MonitorService;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:30:56 PM
 */
@SuppressWarnings("serial")
public class MonitorAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MonitorAction.class);

    private MonitorService monitorService;

    private MonitorPublishView publishView;

    private MonitorNewsExtDto newsExtDto;

    public String showPublishList() throws Exception {
        return "publish.list";
    }

    public String showNewsList() throws Exception {
        return "news.list";
    }

    public String showIndustryList() throws Exception {
        return "industry.list";
    }

    public String showNewsInfo() throws Exception {
        if (null != newsExtDto && newsExtDto.getId() > 0) {
            session.put(Constants.MONITOR_NEWS_VIEW_SESSION_KEY, monitorService.getObject(MonitorNewsView.class,
                    newsExtDto.getId()));
        } else {
            log.warn("newsExtDto is null or newsExtDto.getId() is 0");
        }
        return "news.info";
    }

    public String showIndustryInfo() throws Exception {
        return "industry.info";
    }

    public String getPublishList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_class_name_", MonitorPublishView.class.getName());
        if (null != publishView && StringUtils.isNotBlank(publishView.getPublishDateStr())) {
            map.put("publishDateStr", publishView.getPublishDateStr());
        }
        //
        getList(map);
        publishView = new MonitorPublishView();
        return NONE;
    }

    public String getNewsList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("_class_name_", MonitorNewsView.class.getName());
        if (null != newsExtDto) {
            if (null != newsExtDto.getPublishDateBegin() && newsExtDto.getPublishDateBegin() > 0) {
                map.put("publishDateBegin", newsExtDto.getPublishDateBegin());
            }
            if (null != newsExtDto.getPublishDateEnd() && newsExtDto.getPublishDateEnd() > 0) {
                map.put("publishDateEnd", newsExtDto.getPublishDateEnd());
            }
            if (null != newsExtDto.getInterviewDateBegin() && newsExtDto.getInterviewDateBegin() > 0) {
                map.put("interviewDateBegin", newsExtDto.getInterviewDateBegin());
            }
            if (null != newsExtDto.getInterviewDateEnd() && newsExtDto.getInterviewDateEnd() > 0) {
                map.put("interviewDateEnd", newsExtDto.getInterviewDateEnd());
            }
            if (StringUtils.isNotBlank(newsExtDto.getSubject())) {
                map.put("subject", newsExtDto.getSubject());
            }
        }
        //
        getList(map);
        newsExtDto = new MonitorNewsExtDto();
        return NONE;
    }

    public String saveNewsInfo() throws Exception {
        MonitorNews obj = new MonitorNews();
        BeanUtils.copyProperties(obj, newsExtDto);
        if (StringUtils.isBlank(getActionFlag())) {
            obj.setId(null);
        }
        //
        saveInfo(obj);
        newsExtDto = new MonitorNewsExtDto();
        return NONE;
    }

    public String deleteNews() throws Exception {
        delete(new MonitorNews());
        return NONE;
    }

    private void saveInfo(Object obj) throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        monitorService.saveOrUpdate(obj);
        jvr.setSuccess(true);
        responseJsonData(jvr);
    }

    private void delete(Object object) throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isNotBlank(getIds())) {
            monitorService.deleteAll(object, Utils.getIds(getIds()));
            jvr.setSuccess(true);
        } else {
            log.info("delete ids is null");
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
    }

    private void getList(Map<String, Object> map) throws Exception {
        JsonListResult jlr = new JsonListResult();
        int totalCount = monitorService.getTotalCount(map);
        List<?> mktEvtList = monitorService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(mktEvtList);
        responseJsonData(jlr);
    }

    @Resource
    public void setMonitorService(MonitorService monitorService) {
        this.monitorService = monitorService;
    }

    public MonitorPublishView getPublishView() {
        return publishView;
    }

    public void setPublishView(MonitorPublishView publishView) {
        this.publishView = publishView;
    }

    public MonitorNewsExtDto getNewsExtDto() {
        return newsExtDto;
    }

    public void setNewsExtDto(MonitorNewsExtDto newsExtDto) {
        this.newsExtDto = newsExtDto;
    }
}
