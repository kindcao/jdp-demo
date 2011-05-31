package crm.cust.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import crm.base.service.BaseServiceImpl;
import crm.model.CustomerContact;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 9, 2011 10:52:14 PM
 */
public class ContServiceImpl extends BaseServiceImpl implements ContService {

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by cc.id desc ";
        List<?> result = getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
        return result;
    }

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    private String getQueryHQL(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" from CustomerContact cc where 1=1 ");

        if (null != map.get("deleteFlag")) {
            sb.append(" and cc.deleteFlag = :deleteFlag ");
        }

        if (null != map.get("customerId")) {
            sb.append(" and cc.customerId = :customerId ");
        }

        if (null != map.get("name")) {
            sb.append(" and cc.name like :name ");
            map.put("name", "%" + map.get("name") + "%");
        }

        if (null != map.get("posit")) {
            sb.append(" and cc.posit like :posit ");
            map.put("posit", "%" + map.get("posit") + "%");
        }

        if (null != map.get("address")) {
            sb.append(" and cc.address like :address ");
            map.put("address", "%" + map.get("address") + "%");
        }

        if (null != map.get("isPrimary")) {
            sb.append(" and cc.isPrimary = :isPrimary ");
        }
        return sb.toString();
    }

    @Override
    public void deleteAll(Object object, Collection ids) throws Exception {
        if (null != ids && ids.size() > 0) {
            List<?> list = getBaseDaoImpl().findByIds(object.getClass(), ids);
            if (null != list) {
                CustomerContact contObj = (CustomerContact) object;
                for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                    CustomerContact ele = (CustomerContact) iterator.next();
                    ele.setDeletedBy(contObj.getDeletedBy());
                    ele.setDeletedTime(contObj.getDeletedTime());
                    ele.setDeleteFlag(contObj.getDeleteFlag());
                }
                getBaseDaoImpl().saveOrUpdateAll(list);
            }
        }
    }
}
