package crm.sysuser.service;

import java.util.List;
import java.util.Map;

import core.service.BaseService;
import crm.model.SysUser;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:15:00 PM
 */
public interface SysUserService extends BaseService {

    SysUser findUserByNameAndPass(String username, String password);

    SysUser findUserByName(String username);

    List<?> findPageByQuery(int pageNo, int pageSize, Map map);

    int getTotalCount(Map map);
}
