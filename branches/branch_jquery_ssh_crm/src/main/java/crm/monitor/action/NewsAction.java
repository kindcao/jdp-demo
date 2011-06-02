package crm.monitor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.MonitorNews;
import crm.model.MonitorNewsView;
import crm.monitor.dto.NewsExtDto;
import crm.monitor.service.NewsService;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:30:56 PM
 */
@Scope("session")
@SuppressWarnings("serial")
public class NewsAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(NewsAction.class);

    private NewsService newsService;

    private NewsExtDto newsExtDto;

    public String showNewsList() throws Exception {
        return "news.list";
    }

    public String showNewsInfo() throws Exception {
        if (null != newsExtDto && newsExtDto.getId() > 0) {
            session.put(Constants.MONITOR_NEWS_VIEW_SESSION_KEY, newsService.getObject(MonitorNewsView.class,
                    newsExtDto.getId()));
        } else {
            log.warn("newsExtDto is null or newsExtDto.getId() is 0");
        }
        return "news.info";
    }

    public String getNewsList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
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
        JsonListResult jlr = new JsonListResult();
        int totalCount = newsService.getTotalCount(map);
        List<?> mktEvtList = newsService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(mktEvtList);
        responseJsonData(jlr);
        //      
        newsExtDto = new NewsExtDto();
        return NONE;
    }

    public String saveNewsInfo() throws Exception {
        MonitorNews obj = new MonitorNews();
        PropertyUtils.copyProperties(obj, newsExtDto);
        newsService.saveOrUpdate(obj);
        //
        JsonValidateResult jvr = new JsonValidateResult();
        jvr.setSuccess(true);
        responseJsonData(jvr);
        //       
        newsExtDto = new NewsExtDto();
        return NONE;
    }

    public String deleteNews() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isNotBlank(getIds())) {
            newsService.deleteAll(new MonitorNews(), Utils.getIds(getIds()));
            jvr.setSuccess(true);
        } else {
            log.info("delete ids is null");
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    @Resource
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }

    public NewsExtDto getNewsExtDto() {
        return newsExtDto;
    }

    public void setNewsExtDto(NewsExtDto newsExtDto) {
        this.newsExtDto = newsExtDto;
    }

}
