package crm.syssetup.dao;

import crm.base.dao.BaseDao;
import crm.model.SysCompanyUser;

public interface SysCompUserDao extends BaseDao {

    public SysCompanyUser findUserByNameAndPass(String username, String password);

    public SysCompanyUser findUserByName(String username);

}
