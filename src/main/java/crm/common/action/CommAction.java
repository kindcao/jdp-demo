package crm.common.action;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.base.service.BaseService;
import crm.common.Constants;
import crm.model.CustomerIndustry;
import crm.model.SysCompany;
import crm.model.SysCompanyUser;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 4:19:25 PM
 */
public class CommAction extends BaseAction {

    private static final long serialVersionUID = 7374356470643624209L;

    private final Logger log = LoggerFactory.getLogger(CommAction.class);

    private BaseService baseServiceImpl;

    private int induId;

    private String sysCompIds;

    private String sysCompType;

    private String sysUserIds;

    public String welcome() throws Exception {
        return "welcome";
    }

    public String getCustIndu() throws Exception {
        List<?> list = null;
        if (induId > 0) {
            CustomerIndustry custIndu = new CustomerIndustry();
            custIndu.setSuperiorId(induId);
            list = baseServiceImpl.findByExample(custIndu);
        } else {
            list = baseServiceImpl.loadAll(CustomerIndustry.class);
        }
        responseJsonData(list);
        return NONE;
    }

    public String getSysComp() throws Exception {
        List<?> list = null;
        SysCompany sysComp = new SysCompany();
        sysComp.setStatus(Constants.STATUS_A);
        if (StringUtils.isNotBlank(sysCompType)) {
            sysComp.setType(sysCompType);
        } else {
            sysComp.setType(Constants.STATUS_O);
        }
        list = baseServiceImpl.findByExample(sysComp);
        responseJsonData(list);
        return NONE;
    }

    public String getSysCompUserByUserIds() throws Exception {
        List<?> list = null;
        SysCompanyUser sysCompUser = new SysCompanyUser();
        sysCompUser.setStatus(Constants.STATUS_A);
        sysCompUser.setDeleteFlag(Constants.STATUS_N);
        list = baseServiceImpl.findByExample(sysCompUser);
        if (StringUtils.isNotBlank(sysUserIds)) {
            for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                SysCompanyUser object = (SysCompanyUser) iterator.next();
                if (!sysUserIds.contains(object.getId().toString())) {
                    iterator.remove();
                }
            }
        }
        responseJsonData(list);
        return NONE;
    }

    public String getSysCompUserByCompIds() throws Exception {
        List<?> list = null;
        SysCompanyUser sysCompUser = new SysCompanyUser();
        sysCompUser.setStatus(Constants.STATUS_A);
        sysCompUser.setDeleteFlag(Constants.STATUS_N);
        list = baseServiceImpl.findByExample(sysCompUser);
        //
        if (StringUtils.isNotBlank(sysCompIds)) {
            for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                SysCompanyUser object = (SysCompanyUser) iterator.next();
                if (!sysCompIds.contains(object.getSysCompanyId().toString())) {
                    iterator.remove();
                }
            }
        }
        responseJsonData(list);
        return NONE;
    }

    public BaseService getBaseServiceImpl() {
        return baseServiceImpl;
    }

    @Resource
    public void setBaseServiceImpl(BaseService baseServiceImpl) {
        this.baseServiceImpl = baseServiceImpl;
    }

    public int getInduId() {
        return induId;
    }

    public void setInduId(int induId) {
        this.induId = induId;
    }

    public String getSysCompIds() {
        return sysCompIds;
    }

    public void setSysCompIds(String sysCompIds) {
        this.sysCompIds = sysCompIds;
    }

    public String getSysUserIds() {
        return sysUserIds;
    }

    public void setSysUserIds(String sysUserIds) {
        this.sysUserIds = sysUserIds;
    }

    public String getSysCompType() {
        return sysCompType;
    }

    public void setSysCompType(String sysCompType) {
        this.sysCompType = sysCompType;
    }
}
