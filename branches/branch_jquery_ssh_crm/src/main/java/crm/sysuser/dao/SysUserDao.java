package crm.sysuser.dao;

import core.dao.BaseDao;
import crm.model.SysUser;

public interface SysUserDao extends BaseDao {

    public SysUser findUserByNameAndPass(String username, String password);

    public SysUser findUserByName(String username);

}
