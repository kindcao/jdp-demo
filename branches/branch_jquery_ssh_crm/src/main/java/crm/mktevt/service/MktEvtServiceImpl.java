package crm.mktevt.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.service.BaseServiceImpl;
import crm.cust.service.CustServiceImpl;
import crm.dto.MktEvtExtDto;
import crm.model.MarketEvent;
import crm.model.MarketEventContactRel;
import crm.model.MarketEventContactRelId;
import crm.model.MarketEventCustomerRel;
import crm.model.MarketEventCustomerRelId;
import crm.model.MarketEventSysUserRel;
import crm.model.MarketEventSysUserRelId;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:48:35 PM
 */
public class MktEvtServiceImpl extends BaseServiceImpl implements MktEvtService {

    private final Logger log = LoggerFactory.getLogger(CustServiceImpl.class);

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        if (object instanceof MktEvtExtDto) {
            MktEvtExtDto mktEvtExtDto = (MktEvtExtDto) object;
            MarketEvent mktEvtObj = new MarketEvent();
            BeanUtils.copyProperties(mktEvtObj, mktEvtExtDto);
            if (mktEvtObj.getId() == 0) {
                mktEvtObj.setId(null);
            }
            super.saveOrUpdate(mktEvtObj);
            //
            if (StringUtils.isNotBlank(mktEvtExtDto.getCustomerIds())) {
                Set<MarketEventCustomerRel> mktEvtCustRels = new HashSet<MarketEventCustomerRel>();
                List<Integer> custIs = Utils.getIds(mktEvtExtDto.getCustomerIds());
                MarketEventCustomerRelId _relId = null;
                for (Iterator<Integer> iterator = custIs.iterator(); iterator.hasNext();) {
                    Integer ele = (Integer) iterator.next();
                    _relId = new MarketEventCustomerRelId();
                    _relId.setMarketEventId(mktEvtObj.getId());
                    _relId.setCustomerId(ele);
                    mktEvtCustRels.add(new MarketEventCustomerRel(_relId));
                }
                super.saveOrUpdateAll(mktEvtCustRels);
            } else {
                log.warn("customer ids is null");
            }
            // 
            if (StringUtils.isNotBlank(mktEvtExtDto.getContIds())) {
                Set<MarketEventContactRel> mktEvtContRels = new HashSet<MarketEventContactRel>();
                List<Integer> contIds = Utils.getIds(mktEvtExtDto.getContIds());
                MarketEventContactRelId _relId = null;
                for (Iterator<Integer> iterator = contIds.iterator(); iterator.hasNext();) {
                    Integer ele = (Integer) iterator.next();
                    _relId = new MarketEventContactRelId();
                    _relId.setMarketEventId(mktEvtObj.getId());
                    _relId.setCustomerContactId(ele);
                    mktEvtContRels.add(new MarketEventContactRel(_relId));
                }
                super.saveOrUpdateAll(mktEvtContRels);
            } else {
                log.warn("contact ids is null");
            }
            //
            if (StringUtils.isNotBlank(mktEvtExtDto.getSysCompUseIds())) {
                Set<MarketEventSysUserRel> mktEvtSysUserRels = new HashSet<MarketEventSysUserRel>();
                List<Integer> sysUserIds = Utils.getIds(mktEvtExtDto.getSysCompUseIds());
                MarketEventSysUserRelId _relId = null;
                for (Iterator<Integer> iterator = sysUserIds.iterator(); iterator.hasNext();) {
                    Integer ele = (Integer) iterator.next();
                    _relId = new MarketEventSysUserRelId();
                    _relId.setMarketEventId(mktEvtObj.getId());
                    _relId.setSysCompanyUserId(ele);
                    mktEvtSysUserRels.add(new MarketEventSysUserRel(_relId));
                }
                super.saveOrUpdateAll(mktEvtSysUserRels);
            } else {
                log.warn("sysuser ids is null");
            }
        } else {
            log.error("object not instanceof MktEvtExtDto,object type :" + object.getClass().getSimpleName());
        }
    }

    @Override
    public void deleteAll(Object object, Collection ids) throws Exception {
        if (null != ids && ids.size() > 0) {
            for (Iterator<?> iterator = ids.iterator(); iterator.hasNext();) {
                Integer ele = (Integer) iterator.next();
                //
                List<?> custRelList = getBaseDaoImpl().findByCriteria(
                        DetachedCriteria.forClass(MarketEventCustomerRel.class).add(
                                Expression.eq("id.marketEventId", ele)));
                if (null != custRelList && custRelList.size() > 0) {
                    super.deleteAll(custRelList);
                } else {
                    log.warn("deleteAll custRelList is null");
                }
                //
                List<?> contRelList = getBaseDaoImpl().findByCriteria(
                        DetachedCriteria.forClass(MarketEventContactRel.class).add(
                                Expression.eq("id.marketEventId", ele)));
                if (null != contRelList && contRelList.size() > 0) {
                    super.deleteAll(contRelList);
                } else {
                    log.warn("deleteAll contRelList is null");
                }
                //
                List<?> sysUserList = getBaseDaoImpl().findByCriteria(
                        DetachedCriteria.forClass(MarketEventSysUserRel.class).add(
                                Expression.eq("id.marketEventId", ele)));
                if (null != sysUserList && sysUserList.size() > 0) {
                    super.deleteAll(sysUserList);
                } else {
                    log.warn("deleteAll sysUserList is null");
                }
            }
            //
            super.deleteAll(object, ids);
        } else {
            log.error("deleteAll fail, ids is null");
        }
    }

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by me.id desc ";
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
}
