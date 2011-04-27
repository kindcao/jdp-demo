package crm.sysuser.dao.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import core.dao.impl.BaseDaoImpl;
import crm.sysuser.dao.SysUserDao;
import crm.sysuser.model.SysUser;

public class SysUserDaoImpl extends BaseDaoImpl implements SysUserDao {

    public SysUser findUserByName(String username) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find(
                "from SysUser au where au.username = ?", username);
        if (ul != null && ul.size() >= 1) {
            return (SysUser) ul.get(0);
        }
        return null;
    }

    public SysUser findUserByNameAndPass(String username, String password) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find(
                "from SysUser au where au.username = ? and au.password = ?",
                new String[] { username, password });
        if (ul != null && ul.size() >= 1) {
            return (SysUser) ul.get(0);
        }
        return null;
    }

    public void deleteAllById(Collection entities) {
        for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
            SysUser object = (SysUser) iterator.next();
            removeObject(SysUser.class, object.getId());
        }
    }

}
