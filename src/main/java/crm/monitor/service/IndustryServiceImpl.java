package crm.monitor.service;

import java.util.List;
import java.util.Map;

import crm.base.service.BaseServiceImpl;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:32:09 PM
 */
public class IndustryServiceImpl extends BaseServiceImpl implements IndustryService {

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by miv.id desc ";
        return getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
    }

    private String getQueryHQL(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" from MonitorIndustryView miv where 1=1 ");
        if (null != map.get("publishDateBegin") && null != map.get("publishDateEnd")) {
            sb.append(" and miv.publishDate between :publishDateBegin and :publishDateEnd ");
            map.put("publishDateBegin", Integer.valueOf(map.get("publishDateBegin").toString()));
        } else if (null != map.get("publishDateBegin")) {
            sb.append(" and miv.publishDate = :publishDateBegin ");
        } else if (null != map.get("publishDateEnd")) {
            sb.append(" and miv.publishDate = :publishDateEnd ");
        }
        if (null != map.get("industryNewsTypeId")) {
            sb.append(" and miv.industryNewsTypeId = :industryNewsTypeId ");
        }
        if (null != map.get("customerId")) {
            sb.append(" and miv.customerId = :customerId ");
        }
        if (null != map.get("subject")) {
            sb.append(" and miv.subject like :subject ");
            map.put("subject", "%" + map.get("subject") + "%");
        }
        return sb.toString();
    }
}
