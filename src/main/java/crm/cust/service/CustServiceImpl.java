package crm.cust.service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.service.BaseServiceImpl;
import crm.dto.CustDto;
import crm.model.Customer;
import crm.model.CustomerSysCompanyRel;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:32:13 PM
 */
public class CustServiceImpl extends BaseServiceImpl implements CustService {

    private final Logger log = LoggerFactory.getLogger(CustServiceImpl.class);

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        if (object instanceof CustDto) {
            CustDto custDto = (CustDto) object;
            Customer custObj = custDto.getCustObj();
            // save customer
            super.saveOrUpdate(custObj);
            // set customer id and save CustomerSysCompanyRel
            Set<CustomerSysCompanyRel> custSysCompRels = custDto.getCustSysCompRels();
            if (null != custSysCompRels) {
                for (Iterator<CustomerSysCompanyRel> iterator = custSysCompRels.iterator(); iterator.hasNext();) {
                    CustomerSysCompanyRel ele = (CustomerSysCompanyRel) iterator.next();
                    ele.getId().setCustomerId(custObj.getId());
                    super.saveOrUpdate(ele);
                }
            } else {
                log.warn("custSysCompRels is null");
            }
            // set customer id and save CustomerSysUserRel
            // Set<CustomerSysUserRel> custSysUserRels =
            // custDto.getCustSysUserRels();
            // if (null != custSysUserRels) {
            // for (Iterator<CustomerSysUserRel> iterator =
            // custSysUserRels.iterator(); iterator.hasNext();) {
            // CustomerSysUserRel ele = (CustomerSysUserRel) iterator.next();
            // ele.getId().setCustomerId(custObj.getId());
            // super.saveOrUpdate(ele);
            // }
            // } else {
            // log.warn("custSysUserRels is null");
            // }
        } else {
            log.error("object not instanceof CustDto,object type :" + object.getClass().getSimpleName());
        }
    }

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by cv.customerId desc ";
        List<?> result = getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
        return result;
    }

    private String getQueryHQL(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" from CustomerView cv where 1=1 ");

        if (null != map.get("custName")) {
            sb.append(" and cv.custName like :custName ");
            map.put("custName", "%" + map.get("custName") + "%");
        }

        if (null != map.get("custCode")) {
            if ("---".equals(map.get("custCode"))) {
                sb.append(" and cv.custCode is not null  ");
                sb.append(" and LENGTH(cv.custCode) > 0 ");
            } else {
                sb.append(" and cv.custCode like :custCode ");
                // sb.append(" and cv.custCode = :custCode ");
                map.put("custCode", "%" + map.get("custCode") + "%");
            }
        }

        if (null != map.get("superiorIndustryId")) {
            sb.append(" and cv.superiorIndustryId = :superiorIndustryId ");
        }

        if (null != map.get("industryId")) {
            sb.append(" and cv.industryId = :industryId ");
        }

        if (null != map.get("address")) {
            sb.append(" and cv.address like :address ");
            map.put("address", "%" + map.get("address") + "%");
        }

        if (null != map.get("custSysCompIds")) {
            sb.append(" and cv.sysCompanyId like :custSysCompIds ");
            map.put("custSysCompIds", "%" + map.get("custSysCompIds") + "%");
        }
        return sb.toString();
    }

    @Override
    public void deleteAll(Object object, Collection ids) throws Exception {
        if (object instanceof Customer) {
            if (null != ids && ids.size() > 0) {
                List<?> list = getBaseDaoImpl().findByIds(object.getClass(), ids);
                if (null != list) {
                    Customer custObj = (Customer) object;
                    for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                        Customer ele = (Customer) iterator.next();
                        ele.setDeletedBy(custObj.getDeletedBy());
                        ele.setDeletedTime(custObj.getDeletedTime());
                        ele.setDeleteFlag(custObj.getDeleteFlag());
                    }
                    getBaseDaoImpl().saveOrUpdateAll(list);
                }
            } else {
                log.warn("delete ids collection is null");
            }
        } else {
            log.error("object not instanceof Customer,object type :" + object.getClass().getSimpleName());
        }
    }
}
