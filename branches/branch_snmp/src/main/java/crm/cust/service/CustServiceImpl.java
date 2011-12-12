package crm.cust.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;

import crm.base.service.BaseServiceImpl;
import crm.cust.dto.CustExtDto;
import crm.model.Customer;
import crm.model.CustomerSysCompanyRel;
import crm.model.CustomerSysCompanyRelId;
import crm.model.CustomerView;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:32:13 PM
 */
public class CustServiceImpl extends BaseServiceImpl implements CustService {

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        List<Object> delList = new ArrayList<Object>();
        List<Object> saveList = new ArrayList<Object>();
        CustExtDto custDto = (CustExtDto) object;
        // save customer
        Customer custObj = new Customer();
        PropertyUtils.copyProperties(custObj, custDto);
        super.saveOrUpdate(custObj);
        // set customer id and save CustomerSysCompanyRel
        Set<CustomerSysCompanyRel> custSysCompRels = custDto.getCustSysCompRels();
        List<?> compRels = findCustSysCompRel(new CustomerSysCompanyRelId(custObj.getId(), null));
        if (null != custSysCompRels) {
            if (null != compRels && compRels.size() > 0) {
                for (Iterator<?> iterator = compRels.iterator(); iterator.hasNext();) {
                    CustomerSysCompanyRel ele = (CustomerSysCompanyRel) iterator.next();
                    boolean isFind = false;
                    for (Iterator<CustomerSysCompanyRel> iterator2 = custSysCompRels.iterator(); iterator2.hasNext();) {
                        CustomerSysCompanyRel ele2 = (CustomerSysCompanyRel) iterator2.next();
                        if (ele.getId().getSysCompanyId().intValue() == ele2.getId().getSysCompanyId()) {
                            iterator2.remove();
                            isFind = true;
                            break;
                        }
                    }
                    // for delete
                    if (!isFind) {
                        delList.add(ele);
                    }
                }
            }
            // add
            if (custSysCompRels.size() > 0) {
                for (Iterator<CustomerSysCompanyRel> iterator = custSysCompRels.iterator(); iterator.hasNext();) {
                    CustomerSysCompanyRel ele = (CustomerSysCompanyRel) iterator.next();
                    ele.getId().setCustomerId(custObj.getId());
                }
                saveList.addAll(custSysCompRels);
            }
        } else {
            delList.addAll(compRels);
        }
        //
        if (delList.size() > 0) {
            super.deleteAll(delList);
        }
        if (saveList.size() > 0) {
            super.saveOrUpdateAll(saveList);
        }
    }

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by cv.id desc ";
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
                super.saveOrUpdateAll(list);
            }
        }
    }

    @Override
    public List<?> findCustSysCompRel(CustomerSysCompanyRelId id) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerSysCompanyRel.class);
        criteria.add(Expression.eq("id.customerId", id.getCustomerId()));
        return super.findByCriteria(criteria);
    }

    @Override
    public List<?> getCustNameList(CustomerView object) throws Exception {
        DetachedCriteria criteria = DetachedCriteria.forClass(CustomerView.class);
        if (StringUtils.isNotBlank(object.getSysCompanyId())) {
            criteria.add(Expression.ilike("sysCompanyId", object.getSysCompanyId(), MatchMode.ANYWHERE));
        }
        return super.findByCriteria(criteria);
    }
}
