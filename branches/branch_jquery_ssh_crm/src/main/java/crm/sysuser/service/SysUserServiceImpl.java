package crm.sysuser.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import core.service.BaseServiceImpl;
import crm.model.SysUser;
import crm.sysuser.dao.SysUserDao;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class SysUserServiceImpl extends BaseServiceImpl implements
        SysUserService {

    private SysUserDao sysUserDao;

    @Resource
    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map map) {
        String hql = "from SysUser where username like :username";
        return sysUserDao.findPageByQuery(pageNo, pageSize, hql, map);
    }

    @Override
    public int getTotalCount(Map map) {
        // TODO Auto-generated method stub
        String hql = "from SysUser where username like :username";
        return sysUserDao.getTotalCount(hql, map);
    }

    @Override
    public SysUser findUserByName(String username) {
        return sysUserDao.findUserByName(username);
    }

    @Override
    public SysUser findUserByNameAndPass(String username, String password) {
        return sysUserDao.findUserByNameAndPass(username, password);
    }

}
