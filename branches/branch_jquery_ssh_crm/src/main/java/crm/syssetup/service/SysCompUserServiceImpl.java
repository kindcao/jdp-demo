package crm.syssetup.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import crm.base.service.BaseServiceImpl;
import crm.model.SysCompanyUser;
import crm.syssetup.dao.SysCompUserDao;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class SysCompUserServiceImpl extends BaseServiceImpl implements SysCompUserService {

    private SysCompUserDao sysCompUserDao;

    @Resource
    public void setSysUserDao(SysCompUserDao sysCompUserDao) {
        this.sysCompUserDao = sysCompUserDao;
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map map) {
        String hql = "from SysUser where username like :username";
        return sysCompUserDao.findPageByQuery(pageNo, pageSize, hql, map);
    }

    @Override
    public int getTotalCount(Map map) {
        // TODO Auto-generated method stub
        String hql = "from SysUser where username like :username";
        return sysCompUserDao.getTotalCount(hql, map);
    }

    @Override
    public SysCompanyUser findUserByName(String username) {
        return sysCompUserDao.findUserByName(username);
    }

    @Override
    public SysCompanyUser findUserByNameAndPass(String username, String password) {
        return sysCompUserDao.findUserByNameAndPass(username, password);
    }

}
