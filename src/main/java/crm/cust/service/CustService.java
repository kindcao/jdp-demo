package crm.cust.service;

import java.util.List;

import crm.base.service.BaseService;
import crm.model.CustomerSysCompanyRelId;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:31:18 PM
 */
public interface CustService extends BaseService {

    List<?> findCustSysCompRel(CustomerSysCompanyRelId id) throws Exception;
}
