package crm.mktevt.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.service.BaseServiceImpl;
import crm.dto.MktEvtCalExtDto;
import crm.dto.MktEvtCountExtDto;
import crm.dto.MktEvtExtDto;
import crm.model.MarketEvent;
import crm.model.MarketEventCompanyRel;
import crm.model.MarketEventCompanyRelId;
import crm.model.MarketEventCustomerRel;
import crm.model.MarketEventCustomerRelId;
import crm.model.MarketEventSysUserRel;
import crm.model.MarketEventSysUserRelId;
import crm.model.MarketEventViewCal;
import crm.model.MarketEventViewCount;
import crm.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:48:35 PM
 */
public class MktEvtServiceImpl extends BaseServiceImpl implements MktEvtService {

    private final Logger log = LoggerFactory.getLogger(MktEvtServiceImpl.class);

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        if (object instanceof MktEvtExtDto) {
            MktEvtExtDto mktEvtExtDto = (MktEvtExtDto) object;
            MarketEvent mktEvtObj = new MarketEvent();
            BeanUtils.copyProperties(mktEvtObj, mktEvtExtDto);
            if (mktEvtObj.getId() == 0) {
                mktEvtObj.setId(null);
            } else {
                // for update ,delete rels
                deleteAllRel(Utils.getIds(mktEvtObj.getId().toString()));
            }
            super.saveOrUpdate(mktEvtObj);
            //
            if (StringUtils.isNotBlank(mktEvtExtDto.getSysCompIds())) {
                Set<MarketEventCompanyRel> mktEvtCompRels = new HashSet<MarketEventCompanyRel>();
                List<Integer> compIds = Utils.getIds(mktEvtExtDto.getSysCompIds());
                MarketEventCompanyRelId _relId = null;
                for (Iterator<Integer> iterator = compIds.iterator(); iterator.hasNext();) {
                    Integer ele = (Integer) iterator.next();
                    _relId = new MarketEventCompanyRelId();
                    _relId.setMarketEventId(mktEvtObj.getId());
                    _relId.setSysCompanyId(ele);
                    mktEvtCompRels.add(new MarketEventCompanyRel(_relId));
                }
                super.saveOrUpdateAll(mktEvtCompRels);
            } else {
                log.warn("syscomp ids is null");
            }
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
            // if (StringUtils.isNotBlank(mktEvtExtDto.getContIds())) {
            // Set<MarketEventContactRel> mktEvtContRels = new
            // HashSet<MarketEventContactRel>();
            // List<Integer> contIds = Utils.getIds(mktEvtExtDto.getContIds());
            // MarketEventContactRelId _relId = null;
            // for (Iterator<Integer> iterator = contIds.iterator();
            // iterator.hasNext();) {
            // Integer ele = (Integer) iterator.next();
            // _relId = new MarketEventContactRelId();
            // _relId.setMarketEventId(mktEvtObj.getId());
            // _relId.setCustomerContactId(ele);
            // mktEvtContRels.add(new MarketEventContactRel(_relId));
            // }
            // super.saveOrUpdateAll(mktEvtContRels);
            // } else {
            // log.warn("contact ids is null");
            // }
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
            deleteAllRel(ids);
            super.deleteAll(object, ids);
        } else {
            log.error("deleteAll fail, ids is null");
        }
    }

    private void deleteAllRel(Collection ids) throws Exception {
        if (null != ids && ids.size() > 0) {
            for (Iterator<?> iterator = ids.iterator(); iterator.hasNext();) {
                Integer ele = (Integer) iterator.next();
                //
                List<?> compRelList = getBaseDaoImpl().findByCriteria(
                        DetachedCriteria.forClass(MarketEventCompanyRel.class).add(
                                Expression.eq("id.marketEventId", ele)));
                if (null != compRelList && compRelList.size() > 0) {
                    super.deleteAll(compRelList);
                } else {
                    log.warn("deleteAllRel compRelList is null");
                }
                //
                List<?> custRelList = getBaseDaoImpl().findByCriteria(
                        DetachedCriteria.forClass(MarketEventCustomerRel.class).add(
                                Expression.eq("id.marketEventId", ele)));
                if (null != custRelList && custRelList.size() > 0) {
                    super.deleteAll(custRelList);
                } else {
                    log.warn("deleteAllRel custRelList is null");
                }
                //
                // List<?> contRelList = getBaseDaoImpl().findByCriteria(
                // DetachedCriteria.forClass(MarketEventContactRel.class).add(
                // Expression.eq("id.marketEventId", ele)));
                // if (null != contRelList && contRelList.size() > 0) {
                // super.deleteAll(contRelList);
                // } else {
                // log.warn("deleteAllRel contRelList is null");
                // }
                //
                List<?> sysUserList = getBaseDaoImpl().findByCriteria(
                        DetachedCriteria.forClass(MarketEventSysUserRel.class).add(
                                Expression.eq("id.marketEventId", ele)));
                if (null != sysUserList && sysUserList.size() > 0) {
                    super.deleteAll(sysUserList);
                } else {
                    log.warn("deleteAllRel sysUserList is null");
                }
            }
        } else {
            log.error("deleteAllRel fail, ids is null");
        }
    }

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by mev.id desc ";
        List<?> result = getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
        return result;
    }

    private String getQueryHQL(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(" from MarketEventView mev where 1=1 ");

        if (null != map.get("compCustName")) {
            sb.append(" and mev.compCustName like :compCustName ");
            map.put("compCustName", "%" + map.get("compCustName") + "%");

        }

        if (null != map.get("sysCompUseIds")) {
            sb.append(" and mev.sysCompUserId like :sysCompUseIds ");
            map.put("sysCompUseIds", "%" + map.get("sysCompUseIds") + "%");
        }

        if (null != map.get("occurDateStrBegin") && null != map.get("occurDateStrEnd")) {
            sb.append(" and (mev.occurDate between :occurDateStrBegin and :occurDateStrEnd) ");
        } else if (null != map.get("occurDateStrBegin")) {
            sb.append(" and mev.occurDate = :occurDateStrBegin ");
        } else if (null != map.get("occurDateStrEnd")) {
            sb.append(" and mev.occurDate = :occurDateStrEnd ");
        }

        if (null != map.get("occurDate")) {
            sb.append(" and mev.occurDate = :occurDate ");
        }

        if (null != map.get("beginTime") && null != map.get("endTime")) {
            sb.append(" and (mev.beginTime <= mev.endTime ");
            sb.append(" and mev.beginTime >= :beginTime ");
            sb.append(" and mev.endTime <= :endTime) ");
        } else if (null != map.get("beginTime")) {
            sb.append(" and mev.beginTime = :beginTime ");
        } else if (null != map.get("endTime")) {
            sb.append(" and mev.endTime = :endTime ");
        }

        if (null != map.get("subject")) {
            sb.append(" and mev.subject like :subject ");
            map.put("subject", "%" + map.get("subject") + "%");
        }

        if (null != map.get("mktevtSuperiorId")) {
            sb.append(" and mev.mktevtSuperiorId = :mktevtSuperiorId ");
        }

        if (null != map.get("status")) {
            sb.append(" and mev.status = :status ");
        }
        return sb.toString();
    }

    @Override
    public List<?> findMktEvtCal(Object object) throws Exception {
        if (object instanceof MktEvtCalExtDto) {
            MktEvtCalExtDto dto = (MktEvtCalExtDto) object;
            // MarketEventViewCal _mevc = new MarketEventViewCal();
            // BeanUtils.copyProperties(_mevc, dto);
            DetachedCriteria criteria = DetachedCriteria.forClass(MarketEventViewCal.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Calendar cal = Calendar.getInstance();
            if (null != dto.getOccurDate() && dto.getOccurDate() > 0) {
                cal.setTime(sdf.parse(dto.getOccurDate().toString()));
            } else {
                cal.set(Calendar.DAY_OF_MONTH, 1);
                log.warn("occurDate is null,set occurDate to first day of current month");
            }
            //
            if (dto.isYear()) {
                int beginMonth = cal.get(Calendar.YEAR) * 10000 + 101;
                int endMonth = cal.get(Calendar.YEAR) * 10000 + 1231;
                criteria.add(Restrictions.between("occurDate", beginMonth, endMonth));
            } else {
                cal.set(Calendar.DAY_OF_MONTH, 1);
                int beginDay = Integer.valueOf(sdf.format(cal.getTime()));
                cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                int endDay = Integer.valueOf(sdf.format(cal.getTime()));
                criteria.add(Restrictions.between("occurDate", beginDay, endDay));
            }
            if (StringUtils.isNotBlank(dto.getCompId())) {
                criteria.add(Restrictions.like("compId", dto.getCompId(), MatchMode.ANYWHERE));
            }
            if (null != dto.getMktevtSuperiorId() && dto.getMktevtSuperiorId() > 0) {
                criteria.add(Restrictions.eq("mktevtSuperiorId", dto.getMktevtSuperiorId()));
            }
            return getBaseDaoImpl().findByCriteria(criteria);
        } else {
            log.error("object not instanceof MktEvtCalExtDto,object type :" + object.getClass().getSimpleName());
        }
        return null;
    }

    @Override
    public List<?> findMktEvtCountTab(Object object) throws Exception {
        if (object instanceof MktEvtCountExtDto) {
            MktEvtCountExtDto dto = (MktEvtCountExtDto) object;
            DetachedCriteria criteria = DetachedCriteria.forClass(MarketEventViewCount.class);
            //
            if (null != dto.getIndustrySuperiorId() && dto.getIndustrySuperiorId() > 0) {
                criteria.add(Restrictions.eq("industrySuperiorId", dto.getIndustrySuperiorId()));
            }
            if (StringUtils.isNotBlank(dto.getOccurDateStart()) && StringUtils.isNotBlank(dto.getOccurDateEnd())) {
                criteria.add(Restrictions.between("occurDate", Integer.valueOf(dto.getOccurDateStart()), Integer
                        .valueOf(dto.getOccurDateEnd())));
            } else if (StringUtils.isNotBlank(dto.getOccurDateStart())) {
                criteria.add(Restrictions.eq("occurDate", Integer.valueOf(dto.getOccurDateStart())));
            } else if (StringUtils.isNotBlank(dto.getOccurDateEnd())) {
                criteria.add(Restrictions.eq("occurDate", Integer.valueOf(dto.getOccurDateEnd())));
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.DAY_OF_MONTH, 1);
                int beginDay = Integer.valueOf(sdf.format(cal.getTime()));
                cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
                cal.add(Calendar.DAY_OF_MONTH, -1);
                int endDay = Integer.valueOf(sdf.format(cal.getTime()));
                criteria.add(Restrictions.between("occurDate", beginDay, endDay));
            }
            if (StringUtils.isNotBlank(dto.getSysCompIds())) {
                criteria.add(Restrictions.in("sysCompanyId", Utils.getIds(dto.getSysCompIds())));
            }
            return getBaseDaoImpl().findByCriteria(criteria);
        } else {
            log.error("object not instanceof findMktEvtCountTab,object type :" + object.getClass().getSimpleName());
        }
        return null;
    }
}
