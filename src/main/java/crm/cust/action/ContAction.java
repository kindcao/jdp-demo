package crm.cust.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.cust.dto.CustExtDto;
import crm.cust.service.ContService;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.CustomerContact;
import crm.util.JsonUtils;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 6, 2011 1:32:11 PM
 */
@SuppressWarnings("serial")
public class ContAction extends BaseAction {

    private final Logger log = LoggerFactory.getLogger(ContAction.class);

    private CustomerContact cont;

    private ContService contService;

    private String name;

    private String posit;

    private String isPrimary;

    private String address;

    private Integer customerId;

    public String showContList() throws Exception {
        CustExtDto custExtDto = (CustExtDto) session.get(Constants.CUSTOMER_SESSION_KEY);
        if (null == custExtDto) {
            log.info("get customer object from session is null");
            throw new RuntimeException("get customer object from session is null");
        } else {
            customerId = custExtDto.getId();
        }
        return "contact.list";
    }

    public String showContInfo() throws Exception {
        if (null != cont && null != cont.getId() && cont.getId() > 0) {
            cont = (CustomerContact) contService.getObject(CustomerContact.class, cont.getId());
            session.put(Constants.CUSTOMER_CONTACT_SESSION_KEY, cont);
        } else {
            log.error("contact id is null or 0");
            return NONE;
        }
        return "contact.info";
    }

    public String saveContInfo() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isBlank(getActionFlag())) {
            cont.setId(null);
            cont.setDeleteFlag(Constants.STATUS_N);
            cont.setCreatedBy(getCurrSysCompUser().getId());
            cont.setCreatedTime(getCurrDate());
        }
        cont.setLastUpdatedBy(getCurrSysCompUser().getId());
        cont.setLastUpdatedTime(getCurrDate());
        //
        if (StringUtils.isBlank(cont.getIsPrimary())) {
            cont.setIsPrimary(Constants.STATUS_N);
        }
        //        
        if (null == customerId || customerId == 0) {
            jvr.setErrors("get customer object from session is null");
        } else {
            cont.setCustomerId(customerId);
            contService.saveOrUpdate(cont);
            jvr.setSuccess(true);
        }
        responseJsonData(jvr);
        //
        cont = new CustomerContact();
        return NONE;
    }

    public String getContList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("deleteFlag", Constants.STATUS_N);

        if (null != customerId && customerId > 0) {
            map.put("customerId", customerId);
        }
        if (StringUtils.isNotBlank(name)) {
            map.put("name", name);
        }
        if (StringUtils.isNotBlank(posit)) {
            map.put("posit", posit);
        }
        if (StringUtils.isNotBlank(isPrimary)) {
            map.put("isPrimary", isPrimary);
        }
        if (StringUtils.isNotBlank(address)) {
            map.put("address", address);
        }
        //
        int totalCount = contService.getTotalCount(map);
        List<?> custList = contService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(custList);
        //
        String[] filedName = new String[] { "id", "name", "department", "posit", "phone", "mobile", "email", "address",
                "isPrimary" };
        responseJsonData(jlr, JsonUtils.setIncludes(CustomerContact.class, filedName));
        return NONE;
    }

    public String deleteCont() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isNotBlank(getIds())) {
            CustomerContact contObj = new CustomerContact();
            contObj.setDeletedBy(getCurrSysCompUser().getId());
            contObj.setDeletedTime(getCurrDate());
            contObj.setDeleteFlag(Constants.STATUS_Y);
            contService.deleteAll(contObj, Utils.getIds(getIds()));
            jvr.setSuccess(true);
        } else {
            log.info("delete ids is null");
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String getContByCustIds() throws Exception {
        if (null != customerId && customerId > 0) {
            CustomerContact _obj = new CustomerContact();
            _obj.setCustomerId(customerId);
            _obj.setDeleteFlag(Constants.STATUS_N);
            List<?> contList = contService.findByExample(_obj);
            //
            String[] filedName = new String[] { "id", "name" };
            responseJsonData(contList, JsonUtils.setIncludes(CustomerContact.class, filedName));
        } else {
            log.warn("getContByCustIds customerId is null or 0");
        }
        return NONE;
    }

    // private void reset() {
    // this.name = null;
    // this.posit = null;
    // this.isPrimary = null;
    // this.address = null;
    // }

    @Resource
    public void setContService(ContService contService) {
        this.contService = contService;
    }

    public CustomerContact getCont() {
        return cont;
    }

    public void setCont(CustomerContact cont) {
        this.cont = cont;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosit() {
        return posit;
    }

    public void setPosit(String posit) {
        this.posit = posit;
    }

    public String getIsPrimary() {
        return isPrimary;
    }

    public void setIsPrimary(String isPrimary) {
        this.isPrimary = isPrimary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

}
