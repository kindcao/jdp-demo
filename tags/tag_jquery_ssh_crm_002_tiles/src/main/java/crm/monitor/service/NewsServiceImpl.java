package crm.monitor.service;

import java.util.List;
import java.util.Map;

import crm.base.service.BaseServiceImpl;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:32:09 PM
 */
public class NewsServiceImpl extends BaseServiceImpl implements NewsService {

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by mnv.id desc ";
        return getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
    }

    private String getQueryHQL(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" from MonitorNewsView mnv where 1=1 ");
        if (null != map.get("publishDateBegin") && null != map.get("publishDateEnd")) {
            sb.append(" and mnv.publishDate between :publishDateBegin and :publishDateEnd ");
            map.put("publishDateBegin", Integer.valueOf(map.get("publishDateBegin").toString()));
        } else if (null != map.get("publishDateBegin")) {
            sb.append(" and mnv.publishDate = :publishDateBegin ");
        } else if (null != map.get("publishDateEnd")) {
            sb.append(" and mnv.publishDate = :publishDateEnd ");
        }
        if (null != map.get("interviewDateBegin") && null != map.get("interviewDateEnd")) {
            sb.append(" and mnv.interviewDate between :interviewDateBegin and :interviewDateEnd ");
        } else if (null != map.get("interviewDateBegin")) {
            sb.append(" and mnv.interviewDate = :interviewDateBegin ");
        } else if (null != map.get("interviewDateEnd")) {
            sb.append(" and mnv.interviewDate = :interviewDateEnd ");
        }
        if (null != map.get("subject")) {
            sb.append(" and mnv.subject like :subject ");
            map.put("subject", "%" + map.get("subject") + "%");
        }
        return sb.toString();
    }

}
