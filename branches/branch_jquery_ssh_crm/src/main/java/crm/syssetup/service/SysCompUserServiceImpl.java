package crm.syssetup.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import crm.base.service.BaseServiceImpl;
import crm.syssetup.dao.SysCompUserDao;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class SysCompUserServiceImpl extends BaseServiceImpl implements SysCompUserService {

    private SysCompUserDao compUserDao;

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return compUserDao.getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by id desc";
        return compUserDao.findPageByQuery(pageNo, pageSize, hql, map);
    }

    private String getQueryHQL(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from SysCompanyUser where 1=1 ");
        if (null != map.get("name")) {
            sb.append(" and name like :name ");
            map.put("name", "%" + map.get("name") + "%");
        }

        if (null != map.get("loginId")) {
            sb.append(" and loginId like :loginId ");
            map.put("loginId", "%" + map.get("loginId") + "%");
        }

        if (null != map.get("status")) {
            sb.append(" and status = :status ");
        }
        return sb.toString();
    }

    @Resource
    public void setCompUserDao(SysCompUserDao compUserDao) {
        this.compUserDao = compUserDao;
    }
}
