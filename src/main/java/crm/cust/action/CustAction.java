package crm.cust.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.cust.dto.CustExtDto;
import crm.cust.service.CustService;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.Customer;
import crm.model.CustomerIndustry;
import crm.model.CustomerSysCompanyRel;
import crm.model.CustomerSysCompanyRelId;
import crm.model.CustomerView;
import crm.model.SysCompany;
import crm.util.JsonUtils;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:30:19 PM
 */

@Scope("session")
@SuppressWarnings("serial")
public class CustAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(CustAction.class);

    private int induId;

    // for cust search
    private String custSysCompIds;

    private String custName;

    private String custCode;

    private Integer industryId;

    private String address;

    private CustExtDto cust;

    private CustService custService;

    public String showCustList() throws Exception {
        switch (induId) {
        case 1: {
            return "cust.broker.list";
        }
        case 2: {
            return "cust.bank.list";
        }
        case 3: {
            return "cust.invest.list";
        }
        case 4: {
            return "cust.media.list";
        }
        case 5: {
            return "cust.info.list";
        }
        case 6: {
            return "cust.listed.list";
        }
        case 7: {
            return "cust.others.list";
        }
        default:
            break;
        }
        return NONE;
    }

    public String showCustInfo() throws Exception {
        if (null != cust && null != cust.getId() && cust.getId() > 0) {
            PropertyUtils.copyProperties(cust, custService.getObject(Customer.class, cust.getId()));
            //
            Map<?, ?> induMap = (Map<?, ?>) getCtx().getAttribute(CustomerIndustry.class.getName());
            cust.setIndustryName(((CustomerIndustry) induMap.get(cust.getIndustryId().toString())).getName());
            //
            CustomerSysCompanyRelId custSysCompRelId = new CustomerSysCompanyRelId();
            custSysCompRelId.setCustomerId(cust.getId());
            List<?> compList = custService.findCustSysCompRel(custSysCompRelId);
            if (null != compList) {
                Map<?, ?> sysCompMap = (Map<?, ?>) getCtx().getAttribute(SysCompany.class.getName());
                String custSysCompIds = "";
                String custSysCompNames = "";
                for (Iterator<?> iterator = compList.iterator(); iterator.hasNext();) {
                    CustomerSysCompanyRel ele = (CustomerSysCompanyRel) iterator.next();
                    custSysCompIds += ele.getId().getSysCompanyId();
                    custSysCompNames += ((SysCompany) sysCompMap.get(ele.getId().getSysCompanyId().toString()))
                            .getCompanyName();
                    if (iterator.hasNext()) {
                        custSysCompIds += ",";
                        custSysCompNames += ",";
                    }
                }
                cust.setCustSysCompIds(custSysCompIds);
                cust.setCustSysCompNames(custSysCompNames);
            }
            // session.put(Constants.CUSTOMER_SESSION_KEY, custExtDto);
        } else {
            log.error("cust id is null");
            return NONE;
        }
        //       
        switch (induId) {
        case 1: {
            return "cust.broker.info";
        }
        case 2: {
            return "cust.bank.info";
        }
        case 3: {
            return "cust.invest.info";
        }
        case 4: {
            return "cust.media.info";
        }
        case 5: {
            return "cust.info.info";
        }
        case 6: {
            return "cust.listed.info";
        }
        case 7: {
            return "cust.others.info";
        }
        default:
            break;
        }
        return NONE;
    }

    public String showCustInfoDtl() throws Exception {
        return "cust.info.dtl";
    }

    public String saveCustInfo() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isBlank(getActionFlag())) {
            cust.setId(null);
            cust.setDeleteFlag(Constants.STATUS_N);
            cust.setCreatedBy(getCurrSysCompUser().getId());
            cust.setCreatedTime(getCurrDate());
        }
        cust.setLastUpdatedBy(getCurrSysCompUser().getId());
        cust.setLastUpdatedTime(getCurrDate());
        //
        List<Integer> custSysCompRelIds = Utils.getIds(custSysCompIds);
        if (null != custSysCompRelIds) {
            Set<CustomerSysCompanyRel> custSysCompRels = new HashSet<CustomerSysCompanyRel>();
            for (Iterator<Integer> iterator = custSysCompRelIds.iterator(); iterator.hasNext();) {
                Integer ele = (Integer) iterator.next();
                CustomerSysCompanyRel obj = new CustomerSysCompanyRel();
                obj.setId(new CustomerSysCompanyRelId());
                obj.getId().setSysCompanyId(ele);
                custSysCompRels.add(obj);
            }
            cust.setCustSysCompRels(custSysCompRels);
        } else {
            log.error("custSysCompRelIds is null");
            jvr.getErrors().concat("custSysCompRelIds is null \n");
        }

        if (jvr.getErrors().length() == 0) {
            custService.saveOrUpdate(cust);
            jvr.setSuccess(true);
        }
        responseJsonData(jvr);
        //
        cust = new CustExtDto();
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
        // if (null != session.get("induId")) {
        if (induId > 0) {
            map.put("superiorIndustryId", induId);
        }
        if (null != industryId && industryId > 0) {
            map.put("industryId", industryId);
        }
        if (StringUtils.isNotBlank(address)) {
            map.put("address", address);
        }

        //
        if (!currSysCompTypeIsR()) {
            String _compIds = StringUtils.isNotBlank(custSysCompIds) ? custSysCompIds + "," : "";
            if (!_compIds.contains(getCurrSysComp().getId().toString())) {
                _compIds += getCurrSysComp().getId();
            }
            this.setCustSysCompIds(_compIds);
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
        //
        reset();
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
            log.info("delete ids is null");
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String getCustIndu() throws Exception {
        Map<?, ?> map = (Map<?, ?>) getCtx().getAttribute(CustomerIndustry.class.getName());
        if (null == map) {
            throw new RuntimeException("getCustIndu map from servlet context is null.");
        }
        //
        // int induId = Integer.valueOf(session.get("induId").toString());
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

    public String getCustNameList() throws Exception {
        CustomerView _obj = new CustomerView();
        if (!currSysCompTypeIsR()) {
            _obj.setSysCompanyId(getCurrSysComp().getId().toString());
        }
        List<?> custList = custService.getCustNameList(_obj);
        String[] filedName = new String[] { "id", "custName" };
        responseJsonData(custList, JsonUtils.setIncludes(CustomerView.class, filedName));
        return NONE;
    }

    private void reset() {
        this.custSysCompIds = "";
        this.custName = "";
        this.custCode = "";
        this.address = "";
        this.industryId = null;
    }

    public CustExtDto getCust() {
        return cust;
    }

    public void setCust(CustExtDto cust) {
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
        if (StringUtils.isNotBlank(custSysCompIds)) {
            this.custSysCompIds = Utils.fmtAndSortIds(custSysCompIds);
        }
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

    public int getInduId() {
        return induId;
    }

    public void setInduId(int induId) {
        this.induId = induId;
    }

}
