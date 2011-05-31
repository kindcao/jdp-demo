package crm.syssetup.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.common.MstDataLoader;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
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

    private SysCompany sysCompany;

    public String showSysCompList() throws Exception {
        return "syscomp.list";
    }

    public String showSysCompInfo() throws Exception {
        if (null != sysCompany && sysCompany.getId() > 0) {
            session.put(Constants.SYS_COMP_SESSION_KEY, sysCompService.getObject(SysCompany.class, sysCompany.getId()));
        } else {
            log.warn("SysCompany is null or sysComp.getId() is 0");
        }
        return "syscomp.info";
    }

    public String getSysCompList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != sysCompany) {
            if (StringUtils.isNotBlank(sysCompany.getCompanyName())) {
                map.put("companyName", sysCompany.getCompanyName());
            }
            if (StringUtils.isNotBlank(sysCompany.getStatus())) {
                map.put("status", sysCompany.getStatus());
            }
        }

        int totalCount = sysCompService.getTotalCount(map);
        List<?> results = sysCompService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(results);
        responseJsonData(jlr);
        //
        sysCompany = new SysCompany();
        return NONE;
    }

    public String saveSysCompInfo() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isBlank(getActionFlag())) {
            sysCompany.setId(null);
        }
        sysCompService.saveOrUpdate(sysCompany);
        jvr.setSuccess(true);
        responseJsonData(jvr);
        //
        MstDataLoader.loadSysCompany(getCtx());
        sysCompany = new SysCompany();
        return NONE;
    }

    @Resource
    public void setSysCompService(SysCompService sysCompService) {
        this.sysCompService = sysCompService;
    }

    
    public SysCompany getSysCompany() {
        return sysCompany;
    }

    
    public void setSysCompany(SysCompany sysCompany) {
        this.sysCompany = sysCompany;
    }

   
}
