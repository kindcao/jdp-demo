package crm.syssetup.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import crm.base.service.BaseServiceImpl;
import crm.syssetup.dao.SysCompDao;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class SysCompServiceImpl extends BaseServiceImpl implements SysCompService {

    public SysCompDao sysCompDao;

    @Resource
    public void setSysCompDao(SysCompDao sysCompDao) {
        this.sysCompDao = sysCompDao;
    }

    @Override
    public int getTotalCount(Map<String, Object> map) {
        return sysCompDao.getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) {
        String hql = getQueryHQL(map) + " order by id ";
        return sysCompDao.findPageByQuery(pageNo, pageSize, hql, map);
    }

    private String getQueryHQL(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from SysCompany where 1=1 ");
        if (null != map.get("companyName")) {
            sb.append(" and companyName like :companyName ");
            map.put("companyName", "%" + map.get("companyName") + "%");
        }
        if (null != map.get("status")) {
            sb.append(" and status = :status ");
        }
        return sb.toString();
    }
}
