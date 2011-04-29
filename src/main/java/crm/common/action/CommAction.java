package crm.common.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.action.BaseAction;
import core.common.Constants;
import core.service.BaseService;
import crm.model.SysCompany;
import crm.model.SysCompanyUser;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 4:19:25 PM
 */
public class CommAction extends BaseAction {

    private static final long serialVersionUID = 7374356470643624209L;

    protected Log log = LogFactory.getLog(CommAction.class);

    private BaseService baseServiceImpl;

    public String welcome() throws Exception {
        return "welcome";
    }

    public String getSysComp() throws Exception {
        SysCompany sysComp = new SysCompany();
        sysComp.setStatus(Constants.STATUS_A);
        List<?> list = baseServiceImpl.findByExample(sysComp);
        if (list != null && list.size() > 0) {
            responseJsonData(list);
        } else {
            log.info("cust indu list is null");
        }
        return NONE;
    }

    public String getSysCompUser() throws Exception {
        SysCompanyUser sysCompUser = new SysCompanyUser();
        sysCompUser.setStatus(Constants.STATUS_A);
        sysCompUser.setDeleteFlag(Constants.STATUS_N);
        // sysCompUser.setSuperiorId(superiorId)
        List<?> list = baseServiceImpl.findByExample(sysCompUser);
        if (list != null && list.size() > 0) {
            responseJsonData(list);
        } else {
            log.info("cust indu list is null");
        }
        return NONE;
    }

    public BaseService getBaseServiceImpl() {
        return baseServiceImpl;
    }

    @Resource
    public void setBaseServiceImpl(BaseService baseServiceImpl) {
        this.baseServiceImpl = baseServiceImpl;
    }
}
