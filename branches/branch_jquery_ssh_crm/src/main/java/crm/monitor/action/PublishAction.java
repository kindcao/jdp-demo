package crm.monitor.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;

import crm.base.action.BaseAction;
import crm.json.JsonListResult;
import crm.model.MonitorPublishView;
import crm.monitor.service.PublishService;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:30:56 PM
 */
@Scope("prototype")
@SuppressWarnings("serial")
public class PublishAction extends BaseAction {

    private PublishService publishService;

    private MonitorPublishView publishView;

    public String showPublishList() throws Exception {
        return "publish.list";
    }

    public String getPublishList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != publishView && StringUtils.isNotBlank(publishView.getPublishDateStr())) {
            map.put("publishDateStr", publishView.getPublishDateStr());
        }
        int totalCount = publishService.getTotalCount(map);
        List<?> mktEvtList = publishService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        JsonListResult jlr = new JsonListResult();
        jlr.setTotal(totalCount);
        jlr.setRows(mktEvtList);
        responseJsonData(jlr);
        // 
        publishView = new MonitorPublishView();
        return NONE;
    }

    @Resource
    public void setPublishService(PublishService publishService) {
        this.publishService = publishService;
    }

    public MonitorPublishView getPublishView() {
        return publishView;
    }

    public void setPublishView(MonitorPublishView publishView) {
        this.publishView = publishView;
    }
}
