package crm.monitor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.json.JsonListResult;
import crm.model.MonitorPublishView;
import crm.monitor.service.MonitorService;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:30:56 PM
 */
@SuppressWarnings("serial")
public class MonitorAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MonitorAction.class);

    private MonitorService monitorService;

    private MonitorPublishView publishView;

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
        return "news.info";
    }

    public String showIndustryInfo() throws Exception {
        return "industry.info";
    }

    public String getPublishList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != publishView && StringUtils.isNotBlank(publishView.getPublishDateStr())) {
            map.put("publishDateStr", publishView.getPublishDateStr());
        }
        int totalCount = monitorService.getTotalCount(map);
        List<?> mktEvtList = monitorService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(mktEvtList);
        responseJsonData(jlr);
        //
        publishView = new MonitorPublishView();
        return NONE;
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
}
