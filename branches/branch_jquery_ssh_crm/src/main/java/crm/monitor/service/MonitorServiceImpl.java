package crm.monitor.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import crm.base.service.BaseServiceImpl;

/**
 * @author Kind Cao
 * @version $Rev$, May 24, 2011 4:32:09 PM
 */
public class MonitorServiceImpl extends BaseServiceImpl implements MonitorService {

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by mpv.id desc ";
        List<?> result = getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
        return result;
    }

    private String getQueryHQL(Map<String, Object> map) throws Exception {
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
