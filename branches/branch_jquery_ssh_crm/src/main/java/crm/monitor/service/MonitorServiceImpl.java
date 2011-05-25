package crm.monitor.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.service.BaseServiceImpl;
import crm.model.MonitorNewsView;
import crm.model.MonitorPublishView;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:32:09 PM
 */
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService {

    private final Logger log = LoggerFactory.getLogger(MonitorServiceImpl.class);

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        String hql = null;
        if (map.get("_class_name_").toString().equals(MonitorPublishView.class.getName())) {
            hql = getQueryPublishHQL(map);
        }
        if (map.get("_class_name_").toString().equals(MonitorNewsView.class.getName())) {
            hql = getQueryNewsHQL(map);
        }
        if (null != hql) {
            return getBaseDaoImpl().getTotalCount(hql, map);
        } else {
            log.error("getTotalCount hql is null");
        }
        return 0;
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = null;
        if (map.get("_class_name_").toString().equals(MonitorPublishView.class.getName())) {
            hql = getQueryPublishHQL(map) + " order by mpv.id desc ";
        }
        if (map.get("_class_name_").toString().equals(MonitorNewsView.class.getName())) {
            hql = getQueryNewsHQL(map) + " order by mnv.id desc ";
        }
        if (null != hql) {
            return getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
        } else {
            log.error("findPageByQuery hql is null");
        }
        return null;
    }

    private String getQueryNewsHQL(Map<String, Object> map) throws Exception {
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
        //
        if (null != map.get("interviewDateBegin") && null != map.get("interviewDateEnd")) {
            sb.append(" and mnv.interviewDate between :interviewDateBegin and :interviewDateEnd ");
        } else if (null != map.get("interviewDateBegin")) {
            sb.append(" and mnv.interviewDate = :interviewDateBegin ");
        } else if (null != map.get("interviewDateEnd")) {
            sb.append(" and mnv.interviewDate = :interviewDateEnd ");
        }
        //
        if (null != map.get("subject")) {
            sb.append(" and mnv.subject like :subject ");
            map.put("subject", "%" + map.get("subject") + "%");
        }
        return sb.toString();
    }

    private String getQueryPublishHQL(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" from MonitorPublishView mpv where 1=1 ");
        if (null != map.get("publishDateStr")) {
            sb.append(" and mpv.publishDate between :publishDateBegin and  :publishDateEnd ");
            //            
            String publishDateStr = map.get("publishDateStr").toString().replaceAll("-", "");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(publishDateStr));
            cal.set(Calendar.DAY_OF_MONTH, 1);
            Integer publishDateBegin = Integer.valueOf(sdf.format(cal.getTime()));
            cal.add(Calendar.MONTH, 1);
            cal.add(Calendar.DAY_OF_MONTH, -1);
            Integer publishDateEnd = Integer.valueOf(sdf.format(cal.getTime()));
            map.put("publishDateBegin", publishDateBegin);
            map.put("publishDateEnd", publishDateEnd);
        }
        return sb.toString();
    }
}
