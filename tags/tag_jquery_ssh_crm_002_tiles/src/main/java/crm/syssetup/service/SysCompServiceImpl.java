package crm.syssetup.service;

import java.util.List;
import java.util.Map;

import crm.base.service.BaseServiceImpl;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class SysCompServiceImpl extends BaseServiceImpl implements SysCompService {

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by id desc";
        return getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
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
