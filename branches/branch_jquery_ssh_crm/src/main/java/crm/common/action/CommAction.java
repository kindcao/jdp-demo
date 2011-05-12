package crm.common.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.base.service.BaseService;
import crm.common.Constants;
import crm.model.CustomerIndustry;
import crm.model.MarketEventType;
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

    private int eventTypeId;

    private String sysCompIds;

    private String sysCompType;

    private String sysUserIds;

    public String welcome() throws Exception {
        return "welcome";
    }

    public String getMarketEventType() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(MarketEventType.class.getName());
        if (null == map) {
            throw new RuntimeException("getMarketEventType map from servlet context is null.");
        }

        List<MarketEventType> list = new ArrayList<MarketEventType>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            MarketEventType value = (MarketEventType) map.get(key);
            if (eventTypeId == 0 && (null == value.getSuperiorId() || value.getSuperiorId() == 0)) {
                list.add(value);
            }
            if (eventTypeId > 0 && null != value.getSuperiorId() && value.getSuperiorId().intValue() == eventTypeId) {
                list.add(value);
            }
        }
        responseJsonData(list);
        return NONE;
    }

    public String getStatusYN() throws Exception {
        responseJsonData(Constants.JSON_DATA_STATUS_YN);
        return NONE;
    }

    public String getCustIndu() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(CustomerIndustry.class.getName());
        if (null == map) {
            throw new RuntimeException("getCustIndu map from servlet context is null.");
        }
        //
        List<CustomerIndustry> list = new ArrayList<CustomerIndustry>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            CustomerIndustry value = (CustomerIndustry) map.get(key);
            if (induId > 0) {
                if (null != value.getSuperiorId() && value.getSuperiorId().intValue() == induId) {
                    list.add(value);
                }
            } else {
                list.add(value);
            }
        }
        responseJsonData(list);
        return NONE;
    }

    public String getSysComp() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(SysCompany.class.getName());
        if (null == map) {
            throw new RuntimeException("getSysComp map from servlet context is null.");
        }

        //
        List<SysCompany> list = new ArrayList<SysCompany>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            SysCompany value = (SysCompany) map.get(key);
            if (StringUtils.isNotBlank(sysCompType)) {
                if (sysCompType.equals(value.getType())) {
                    list.add(value);
                }
            } else {
                if (Constants.STATUS_O.equals(value.getType())) {
                    list.add(value);
                }
            }
        }
        responseJsonData(list);
        return NONE;
    }

    public String getSysCompUserByUserIds() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(SysCompanyUser.class.getName());
        if (null == map) {
            throw new RuntimeException("getSysCompUserByUserIds map from servlet context is null.");
        }

        //
        List<SysCompanyUser> list = new ArrayList<SysCompanyUser>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            SysCompanyUser value = (SysCompanyUser) map.get(key);
            if (StringUtils.isNotBlank(sysUserIds)) {
                if (sysUserIds.contains(value.getId().toString())) {
                    list.add(value);
                }
            }
        }
        responseJsonData(list);
        return NONE;
    }

    public String getSysCompUserByCompIds() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(SysCompanyUser.class.getName());
        if (null == map) {
            throw new RuntimeException("getSysCompUserByCompIds map from servlet context is null.");
        }

        //
        List<SysCompanyUser> list = new ArrayList<SysCompanyUser>();
        for (Iterator<?> iterator = map.keySet().iterator(); iterator.hasNext();) {
            String key = (String) iterator.next();
            SysCompanyUser value = (SysCompanyUser) map.get(key);
            if (StringUtils.isNotBlank(sysCompIds)) {
                if (sysCompIds.contains(value.getSysCompanyId().toString())) {
                    list.add(value);
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

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId = eventTypeId;
    }
}
