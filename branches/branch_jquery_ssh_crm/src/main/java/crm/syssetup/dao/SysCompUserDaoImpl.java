package crm.syssetup.dao;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import crm.base.dao.BaseDaoImpl;
import crm.model.SysCompanyUser;

public class SysCompUserDaoImpl extends BaseDaoImpl implements SysCompUserDao {

    public SysCompanyUser findUserByName(String username) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find("from SysUser au where au.username = ?", username);
        if (ul != null && ul.size() >= 1) {
            return (SysCompanyUser) ul.get(0);
        }
        return null;
    }

    public SysCompanyUser findUserByNameAndPass(String username, String password) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find("from SysUser au where au.username = ? and au.password = ?",
                new String[] { username, password });
        if (ul != null && ul.size() >= 1) {
            return (SysCompanyUser) ul.get(0);
        }
        return null;
    }

    public void deleteAllById(Collection entities) {
        for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
            SysCompanyUser object = (SysCompanyUser) iterator.next();
            removeObject(SysCompanyUser.class, object.getId());
        }
    }

}
