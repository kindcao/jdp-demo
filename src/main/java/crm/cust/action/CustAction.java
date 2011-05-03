package crm.cust.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.cust.service.CustService;
import crm.dto.CustDto;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.Customer;
import crm.model.CustomerSysCompanyRel;
import crm.model.CustomerSysCompanyRelId;
import crm.model.CustomerSysUserRel;
import crm.model.CustomerSysUserRelId;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:30:19 PM
 */
public class CustAction extends BaseAction {

    private static final long serialVersionUID = -8111161784038216123L;

    private final Logger log = LoggerFactory.getLogger(CustAction.class);

    private int induId;

    private String custSysCompIds = "";

    private String custSysUserIds = "";

    private String custSysUserPrimIds = "";

    private Customer cust;

    private Customer custSearch;

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
        CustDto custDto = new CustDto();
        cust.setDeleteFlag(Constants.STATUS_N);
        cust.setCreatedBy(getCurrSysCompUser().getId());
        cust.setCreatedTime(getCurrDate());
        cust.setLastUpdatedBy(cust.getCreatedBy());
        cust.setLastUpdatedTime(cust.getCreatedTime());
        custDto.setCustObj(cust);
        //
        Integer[] custSysCompRelIds = splitIdsStrByRegex(custSysCompIds);
        if (null != custSysCompRelIds) {
            Set<CustomerSysCompanyRel> custSysCompRels = new HashSet<CustomerSysCompanyRel>();
            for (int i = 0; i < custSysCompRelIds.length; i++) {
                CustomerSysCompanyRel obj = new CustomerSysCompanyRel();
                obj.setId(new CustomerSysCompanyRelId());
                obj.getId().setSysCompanyId(custSysCompRelIds[i]);
                custSysCompRels.add(obj);
            }
            custDto.setCustSysCompRels(custSysCompRels);
        } else {
            log.error("custSysCompRelIds is null");
            jvr.getErrors().concat("custSysCompRelIds is null \n");
        }
        //
        Integer[] custSysUserRelIds = splitIdsStrByRegex(custSysUserIds);
        if (null != custSysUserRelIds) {
            Set<CustomerSysUserRel> custSysUserRels = new HashSet<CustomerSysUserRel>();
            for (int i = 0; i < custSysUserRelIds.length; i++) {
                CustomerSysUserRel obj = new CustomerSysUserRel();
                obj.setId(new CustomerSysUserRelId());
                obj.getId().setSysCompanyUserId(custSysUserRelIds[i]);
                //
                if (custSysUserPrimIds.contains(obj.getId().getSysCompanyUserId().toString())) {
                    obj.setIsPrimary(Constants.STATUS_Y);
                }
                custSysUserRels.add(obj);
            }
            custDto.setCustSysUserRels(custSysUserRels);
        } else {
            log.error("custSysUserRelIds is null");
            jvr.getErrors().concat("custSysUserRelIds is null \n");
        }
        //
        if (jvr.getErrors().length() == 0) {
            custService.saveOrUpdate(custDto);
            jvr.setSuccess(true);
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String getCustList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("custName", custSearch.getCustName());
        map.put("custCode", custSearch.getCustCode());
        map.put("industryId", custSearch.getIndustryId());
        map.put("address", custSearch.getAddress());
        map.put("custSysCompIds", custSysCompIds);
        map.put("custSysUserIds", custSysUserIds);

        int totalCount = custService.getTotalCount(map);
        List<?> custList = custService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(custList);
        responseJsonData(jlr);
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

    public Customer getCustSearch() {
        return custSearch;
    }

    public void setCustSearch(Customer custSearch) {
        this.custSearch = custSearch;
    }
}