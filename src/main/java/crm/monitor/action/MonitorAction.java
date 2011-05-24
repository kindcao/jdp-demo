package crm.monitor.action;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.monitor.service.MonitorService;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:30:56 PM
 */
@SuppressWarnings("serial")
public class MonitorAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(MonitorAction.class);

    private MonitorService monitorService;

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

    @Resource
    public void setMonitorService(MonitorService monitorService) {
        this.monitorService = monitorService;
    }
}
