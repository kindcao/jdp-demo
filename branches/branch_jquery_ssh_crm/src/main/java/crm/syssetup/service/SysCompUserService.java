package crm.syssetup.service;

import java.util.List;
import java.util.Map;

import crm.base.service.BaseService;
import crm.model.SysCompanyUser;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:15:00 PM
 */
public interface SysCompUserService extends BaseService {

    SysCompanyUser findUserByNameAndPass(String username, String password);

    SysCompanyUser findUserByName(String username);

    List<?> findPageByQuery(int pageNo, int pageSize, Map map);

    int getTotalCount(Map map);
}
