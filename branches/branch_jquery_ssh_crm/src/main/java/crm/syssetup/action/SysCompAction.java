package crm.syssetup.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;

import crm.base.action.BaseAction;
import crm.json.JsonListResult;
import crm.model.SysCompany;
import crm.syssetup.service.SysCompService;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 3, 2011 9:45:30 PM
 */
public class SysCompAction extends BaseAction {

    private static final long serialVersionUID = 6451848871854370991L;

    protected Logger log = LoggerFactory.getLogger(SysCompAction.class);

    private SysCompService sysCompService;

    private SysCompany sysComp;

    private String companyName;

    private String status;

    public String showCompList() throws Exception {
        companyName = "";
        status = "";
        return "syscomp.list";
    }

    public String getCompList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(companyName)) {
            map.put("companyName", companyName);
        }
        if (StringUtils.isNotBlank(status)) {
            map.put("status", status);
        }

        int totalCount = sysCompService.getTotalCount(map);
        List<?> results = sysCompService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(results);
        responseJsonData(jlr);
        return Action.NONE;
    }

    @Resource
    public void setSysCompService(SysCompService sysCompService) {
        this.sysCompService = sysCompService;
    }

    public SysCompany getSysComp() {
        return sysComp;
    }

    public void setSysComp(SysCompany sysComp) {
        this.sysComp = sysComp;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
