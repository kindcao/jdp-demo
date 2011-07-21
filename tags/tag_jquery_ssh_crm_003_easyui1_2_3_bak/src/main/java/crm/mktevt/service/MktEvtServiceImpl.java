package crm.mktevt.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import crm.base.service.BaseServiceImpl;
import crm.mktevt.dto.MktEvtCalExtDto;
import crm.mktevt.dto.MktEvtCountExtDto;
import crm.mktevt.dto.MktEvtExtDto;
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

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        List<Object> delList = new ArrayList<Object>();
        List<Object> saveList = new ArrayList<Object>();
        MktEvtExtDto mktEvtExtDto = (MktEvtExtDto) object;
        MarketEvent mktEvtObj = new MarketEvent();
        PropertyUtils.copyProperties(mktEvtObj, mktEvtExtDto);
        super.saveOrUpdate(mktEvtObj);
        //
        List<Integer> compIds = null;
        List<?> compRels = getCompRels(mktEvtObj.getId());
        if (StringUtils.isNotBlank(mktEvtExtDto.getSysCompIds())) {
            compIds = Utils.getIds(mktEvtExtDto.getSysCompIds());
            if (null != compRels && compRels.size() > 0) {
                for (Iterator<?> iterator = compRels.iterator(); iterator.hasNext();) {
                    MarketEventCompanyRel ele = (MarketEventCompanyRel) iterator.next();
                    boolean isFind = false;
                    for (Iterator<?> iterator2 = compIds.iterator(); iterator2.hasNext();) {
                        Integer ele2 = (Integer) iterator2.next();
                        if (ele.getId().getSysCompanyId().intValue() == ele2.intValue()) {
                            iterator2.remove();
                            isFind = true;
                            break;
                        }
                    }
                    if (!isFind) {
                        delList.add(ele);
                    }
                }
            }
            // add
            if (null != compIds && compIds.size() > 0) {
                for (Iterator<Integer> iterator = compIds.iterator(); iterator.hasNext();) {
                    Integer ele = (Integer) iterator.next();
                    saveList.add(new MarketEventCompanyRel(new MarketEventCompanyRelId(mktEvtObj.getId(), ele)));
                }
            }
        } else {
            delList.addAll(compRels);
        }

        //
        List<Integer> custIds = null;
        List<?> custRels = getCustRels(mktEvtObj.getId());
        if (StringUtils.isNotBlank(mktEvtExtDto.getCustomerIds())) {
            custIds = Utils.getIds(mktEvtExtDto.getCustomerIds());
            if (null != custRels && custRels.size() > 0) {
                for (Iterator<?> iterator = custRels.iterator(); iterator.hasNext();) {
                    MarketEventCustomerRel ele = (MarketEventCustomerRel) iterator.next();
                    boolean isFind = false;
                    for (Iterator<?> iterator2 = custIds.iterator(); iterator2.hasNext();) {
                        Integer ele2 = (Integer) iterator2.next();
                        if (ele.getId().getCustomerId().intValue() == ele2.intValue()) {
                            iterator2.remove();
                            isFind = true;
                            break;
                        }
                    }
                    if (!isFind) {
                        delList.add(ele);
                    }
                }
            }
            // add
            if (null != custIds && custIds.size() > 0) {
                for (Iterator<Integer> iterator = custIds.iterator(); iterator.hasNext();) {
                    Integer ele = (Integer) iterator.next();
                    saveList.add(new MarketEventCustomerRel(new MarketEventCustomerRelId(mktEvtObj.getId(), ele)));
                }
            }
        } else {
            delList.addAll(custRels);
        }

        //
        List<Integer> sysUserIds = null;
        List<?> sysUserRels = getSysUserRels(mktEvtObj.getId());
        if (StringUtils.isNotBlank(mktEvtExtDto.getSysCompUseIds())) {
            sysUserIds = Utils.getIds(mktEvtExtDto.getSysCompUseIds());
            if (null != sysUserRels && sysUserRels.size() > 0) {
                for (Iterator<?> iterator = sysUserRels.iterator(); iterator.hasNext();) {
                    MarketEventSysUserRel ele = (MarketEventSysUserRel) iterator.next();
                    boolean isFind = false;
                    for (Iterator<?> iterator2 = sysUserIds.iterator(); iterator2.hasNext();) {
                        Integer ele2 = (Integer) iterator2.next();
                        if (ele.getId().getSysCompanyUserId().intValue() == ele2.intValue()) {
                            iterator2.remove();
                            isFind = true;
                            break;
                        }
                    }
                    if (!isFind) {
                        delList.add(ele);
                    }
                }
            }
            // add
            if (null != sysUserIds && sysUserIds.size() > 0) {
                for (Iterator<Integer> iterator = sysUserIds.iterator(); iterator.hasNext();) {
                    Integer ele = (Integer) iterator.next();
                    saveList.add(new MarketEventSysUserRel(new MarketEventSysUserRelId(mktEvtObj.getId(), ele)));
                }
            }
        } else {
            delList.addAll(sysUserRels);
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
    public void deleteAll(Object object, Collection ids) throws Exception {
        if (null != ids && ids.size() > 0) {
            List<Object> _list = new ArrayList<Object>();
            for (Iterator<?> iterator = ids.iterator(); iterator.hasNext();) {
                Integer ele = (Integer) iterator.next();
                _list.addAll(getCompRels(ele));
                _list.addAll(getCustRels(ele));
                _list.addAll(getSysUserRels(ele));
            }
            if (_list.size() > 0) {
                super.deleteAll(_list);
            }
            super.deleteAll(object, ids);
        }
    }

    private List<?> getCompRels(int id) throws Exception {
        return super.findByCriteria(DetachedCriteria.forClass(MarketEventCompanyRel.class).add(
                Expression.eq("id.marketEventId", id)));
    }

    private List<?> getCustRels(int id) throws Exception {
        return super.findByCriteria(DetachedCriteria.forClass(MarketEventCustomerRel.class).add(
                Expression.eq("id.marketEventId", id)));
    }

    private List<?> getSysUserRels(int id) throws Exception {
        return super.findByCriteria(DetachedCriteria.forClass(MarketEventSysUserRel.class).add(
                Expression.eq("id.marketEventId", id)));
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

        if (null != map.get("sysCompUseIds")) {
            sb.append(" and mev.sysCompUserId in (:sysCompUseIds) ");
            map.put("sysCompUseIds", map.get("sysCompUseIds"));
        }

        if (null != map.get("compCustName")) {
            sb.append(" and mev.compCustName like :compCustName ");
            map.put("compCustName", "%" + map.get("compCustName") + "%");
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
        MktEvtCalExtDto dto = (MktEvtCalExtDto) object;
        DetachedCriteria criteria = DetachedCriteria.forClass(MarketEventViewCal.class);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        if (null != dto.getOccurDate() && dto.getOccurDate() > 0) {
            cal.setTime(sdf.parse(dto.getOccurDate().toString()));
        } else {
            // occurDate is null,set occurDate to first day of current month
            cal.set(Calendar.DAY_OF_MONTH, 1);
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
        return super.findByCriteria(criteria);
    }

    @Override
    public List<?> findMktEvtCountTab(Object object) throws Exception {
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
        return super.findByCriteria(criteria);
    }
}
