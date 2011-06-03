package crm.syssetup.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import crm.base.action.BaseAction;
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
@Scope("session")
@SuppressWarnings("serial")
public class SysCompAction extends BaseAction {

    protected Logger log = LoggerFactory.getLogger(SysCompAction.class);

    private SysCompService sysCompService;

    private SysCompany sysCompany;

    public String showSysCompList() throws Exception {
        sysCompany = new SysCompany();
        return "syscomp.list";
    }

    public String showSysCompInfo() throws Exception {
        if (null != sysCompany && sysCompany.getId() > 0) {
            sysCompany = (SysCompany) sysCompService.getObject(SysCompany.class, sysCompany.getId());
        } else {
            log.warn("SysCompany is null or sysComp.getId() is 0");
        }
        return "syscomp.info";
    }

    public String getSysCompList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(sysCompany.getCompanyName())) {
            map.put("companyName", sysCompany.getCompanyName());
        }
        if (StringUtils.isNotBlank(sysCompany.getStatus())) {
            map.put("status", sysCompany.getStatus());
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

    public String getSysComp() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(SysCompany.class.getName());
        if (null == map) {
            throw new RuntimeException("getSysComp map from servlet context is null.");
        }
        //
        SysCompany _currSysComp = getCurrSysComp();
        List<SysCompany> list = new ArrayList<SysCompany>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            SysCompany value = (SysCompany) map.get(key);
            //           
            if (currSysCompTypeIsR()) {
                list.add(value);
            } else if (_currSysComp.getId().intValue() == value.getId().intValue()) {
                list.add(value);
            }
        }
        responseJsonData(list);
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
