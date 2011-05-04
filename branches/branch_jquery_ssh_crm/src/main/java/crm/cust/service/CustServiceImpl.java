package crm.cust.service;

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
    public void saveOrUpdate(Object object) {
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
    public int getTotalCount(Map<String, Object> map) {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) {
        List<?> result = getBaseDaoImpl().findPageByQuery(pageNo, pageSize, getQueryHQL(map), map);
        return result;
    }

    private String getQueryHQL(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        // StringBuilder sb2 = new StringBuilder();
        sb.append(" from Customer cust");

        // if (null != map.get("custSysCompIds")) {
        // sb.append(" left outer join CustomerSysCompanyRel cscr on cust.id =
        // cscr.id.customerId");
        // sb2.append(" and cscr.id.sysCompanyId in (" +
        // map.get("custSysCompIds") + ") ");
        // }
        //
        // if (null != map.get("custSysUserIds")) {
        // sb.append(" left outer join CustomerSysUserRel csur on cust.id =
        // csur.id.customerId");
        // sb2.append(" and csur.id.sysCompanyUserId in (" +
        // map.get("custSysUserIds") + ") ");
        // }

        sb.append(" where 1=1 ");
        if (null != map.get("custName")) {
            sb.append(" and cust.custName like :custName ");
            map.put("custName", "%" + map.get("custName") + "%");
        }

        if (null != map.get("custCode")) {
            if ("---".equals(map.get("custCode"))) {
                sb.append(" and cust.custCode is not null ");
            } else {
                sb.append(" and cust.custCode = :custCode ");
            }
        }

        if (null != map.get("industryId")) {
            sb.append(" and cust.industryId = :industryId ");
        }

        if (null != map.get("address")) {
            sb.append(" and (cust.country like :address ");
            sb.append(" or cust.province like :address ");
            sb.append(" or cust.city like :address ");
            sb.append(" or cust.address like :address) ");
            map.put("address", "%" + map.get("address") + "%");
        }

        // if (sb2.length() > 0) {
        // sb.append(sb2);
        // }
        sb.append(" order by cust.id desc ");
        return sb.toString();
    }
}
