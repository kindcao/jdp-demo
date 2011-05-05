package crm.cust.action;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
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
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:30:19 PM
 */
public class CustAction extends BaseAction {

    private static final long serialVersionUID = -8111161784038216123L;

    private final Logger log = LoggerFactory.getLogger(CustAction.class);

    private int induId;

    private String custSysCompIds = "";

    // private String custSysUserIds = "";
    //
    // private String custSysUserPrimIds = "";

    private String custName;

    private String custCode;

    private Integer industryId;

    private String address;

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
        CustDto custDto = new CustDto();
        if (StringUtils.isBlank(getActionFlag())) {
            cust.setId(null);
            cust.setDeleteFlag(Constants.STATUS_N);
            cust.setCreatedBy(getCurrSysCompUser().getId());
            cust.setCreatedTime(getCurrDate());
        }
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
        // Integer[] custSysUserRelIds = splitIdsStrByRegex(custSysUserIds);
        // if (null != custSysUserRelIds) {
        // Set<CustomerSysUserRel> custSysUserRels = new
        // HashSet<CustomerSysUserRel>();
        // for (int i = 0; i < custSysUserRelIds.length; i++) {
        // CustomerSysUserRel obj = new CustomerSysUserRel();
        // obj.setId(new CustomerSysUserRelId());
        // obj.getId().setSysCompanyUserId(custSysUserRelIds[i]);
        // //
        // if
        // (custSysUserPrimIds.contains(obj.getId().getSysCompanyUserId().toString()))
        // {
        // obj.setIsPrimary(Constants.STATUS_Y);
        // }
        // custSysUserRels.add(obj);
        // }
        // custDto.setCustSysUserRels(custSysUserRels);
        // } else {
        // log.error("custSysUserRelIds is null");
        // jvr.getErrors().concat("custSysUserRelIds is null \n");
        // }
        //
        if (jvr.getErrors().length() == 0) {
            custService.saveOrUpdate(custDto);
            jvr.setSuccess(true);
        }
        responseJsonData(jvr);
        reset();
        return NONE;
    }

    public String getCustList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(custName)) {
            map.put("custName", custName);
        }
        if (StringUtils.isNotBlank(custCode)) {
            map.put("custCode", custCode);
        }
        if (null != industryId && industryId > 0) {
            map.put("industryId", industryId);
        }
        if (StringUtils.isNotBlank(address)) {
            map.put("address", address);
        }
        if (StringUtils.isNotBlank(custSysCompIds)) {
            map.put("custSysCompIds", custSysCompIds);
        }

        //
        int totalCount = custService.getTotalCount(map);
        List<?> custList = custService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(custList);
        responseJsonData(jlr);
        return NONE;
    }

    public String deleteCust() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isNotBlank(getIds())) {
            Customer _custObj = new Customer();
            _custObj.setDeletedBy(getCurrSysCompUser().getId());
            _custObj.setDeletedTime(getCurrDate());
            _custObj.setDeleteFlag(Constants.STATUS_Y);
            custService.deleteAll(_custObj, Utils.getIds(getIds()));
            jvr.setSuccess(true);
        } else {
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    private void reset() {
        this.custSysCompIds = "";
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
        this.custSysCompIds = Utils.fmtAndSortIds(custSysCompIds);
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public Integer getIndustryId() {
        return industryId;
    }

    public void setIndustryId(Integer industryId) {
        this.industryId = industryId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
