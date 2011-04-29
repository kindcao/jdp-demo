package crm.cust.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.action.BaseAction;
import core.json.JsonValidateResult;
import crm.cust.service.CustService;
import crm.model.Customer;
import crm.model.CustomerIndustry;
import crm.model.CustomerSysCompanyRel;
import crm.model.CustomerSysUserRel;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:30:19 PM
 */
public class CustAction extends BaseAction {

    private static final long serialVersionUID = -8111161784038216123L;

    protected Log log = LogFactory.getLog(CustAction.class);

    private int induId;

    private String custSysCompIds;

    private String custSysUserIds;

    private String custSysUserPrimIds;

    private Customer cust;

    private CustService custService;

    public String showCustList() throws Exception {
        switch (induId) {
        case 1: {
            return "cust.broker.list";
        }
        default:
            break;
        }
        return NONE;
    }

    public String showCustInfo() throws Exception {
        switch (induId) {
        case 1: {
            return "cust.broker.info";
        }
        default:
            break;
        }
        return NONE;
    }

    public String saveCustInfo() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        jvr.setSuccess(true);
        responseJsonData(jvr);
        return NONE;
    }

    public String getCustInduList() throws Exception {
        if (induId > 0) {
            CustomerIndustry custIndu = new CustomerIndustry();
            custIndu.setSuperiorId(induId);
            List<?> list = custService.findByExample(custIndu);
            if (list != null && list.size() > 0) {
                responseJsonData(list);
            } else {
                log.info("cust indu list is null");
            }
        } else {
            log.warn("induId is zero.");
        }
        return NONE;
    }

    public int getInduId() {
        return induId;
    }

    public void setInduId(int induId) {
        this.induId = induId;
    }

    public Customer getCust() {
        return cust;
    }

    public void setCust(Customer cust) {
        this.cust = cust;
    }

    public CustService getCustService() {
        return custService;
    }

    @Resource
    public void setCustService(CustService custService) {
        this.custService = custService;
    }

    public String getCustSysCompIds() {
        return custSysCompIds;
    }

    public void setCustSysCompIds(String custSysCompIds) {
        this.custSysCompIds = custSysCompIds;
    }

    public String getCustSysUserIds() {
        return custSysUserIds;
    }

    public void setCustSysUserIds(String custSysUserIds) {
        this.custSysUserIds = custSysUserIds;
    }

    public String getCustSysUserPrimIds() {
        return custSysUserPrimIds;
    }

    public void setCustSysUserPrimIds(String custSysUserPrimIds) {
        this.custSysUserPrimIds = custSysUserPrimIds;
    }
}
