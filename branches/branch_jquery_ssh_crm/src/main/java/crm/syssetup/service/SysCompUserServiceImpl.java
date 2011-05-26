package crm.syssetup.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Expression;

import crm.base.service.BaseServiceImpl;
import crm.common.Constants;
import crm.model.SysCompanyUser;
import crm.model.SysCompanyUserRel;
import crm.model.SysCompanyUserRelId;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class SysCompUserServiceImpl extends BaseServiceImpl implements SysCompUserService {

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        SysCompanyUser sysCompUser = (SysCompanyUser) object;
        if (null != sysCompUser.getId() && sysCompUser.getId() > 0) {
            // for update
            deleteSysCompanyUserRelByChildId(sysCompUser.getId());
        }
        // for add or update
        super.saveOrUpdate(sysCompUser);
        Set<Integer> set = new HashSet<Integer>();
        getUserSuperiorIds(set, sysCompUser.getId());
        //
        if (set.size() > 0) {
            Set<SysCompanyUserRel> rels = new HashSet<SysCompanyUserRel>();
            for (Iterator<?> iterator = set.iterator(); iterator.hasNext();) {
                Integer ele = (Integer) iterator.next();
                rels.add(new SysCompanyUserRel(new SysCompanyUserRelId(ele, sysCompUser.getId())));
            }
            super.saveOrUpdateAll(rels);
        }
    }

    @Override
    public void deleteAll(Object object, Collection ids) throws Exception {
        List<?> list = super.findByIds(object.getClass(), ids);
        if (null != list) {
            for (Iterator<?> iterator = ids.iterator(); iterator.hasNext();) {
                SysCompanyUser ele = (SysCompanyUser) iterator.next();
                ele.setDeleteFlag(Constants.STATUS_Y);
                //
                deleteSysCompanyUserRelByChildId(ele.getId());
            }
            super.saveOrUpdateAll(list);
        }
    }

    @Override
    public List<?> findSysCompanyUserRel(SysCompanyUserRel object) throws Exception {
        if (null == object) {
            return super.loadAll(object.getClass());
        } else {
            DetachedCriteria criteria = DetachedCriteria.forClass(object.getClass());
            if (object.getId().getParentUserId() != null && object.getId().getParentUserId() > 0) {
                criteria.add(Expression.eq("id.parentUserId", object.getId().getParentUserId()));
            }
            if (object.getId().getChildUserId() != null && object.getId().getChildUserId() > 0) {
                criteria.add(Expression.eq("id.childUserId", object.getId().getChildUserId()));
            }
            return super.findByCriteria(criteria);
        }
    }

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return getBaseDaoImpl().getTotalCount(getQueryHQL(map), map);
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        String hql = getQueryHQL(map) + " order by id desc";
        return getBaseDaoImpl().findPageByQuery(pageNo, pageSize, hql, map);
    }

    private String getQueryHQL(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append(" from SysCompanyUserView where 1=1 ");
        if (null != map.get("name")) {
            sb.append(" and name like :name ");
            map.put("name", "%" + map.get("name") + "%");
        }
        if (null != map.get("loginId")) {
            sb.append(" and loginId like :loginId ");
            map.put("loginId", "%" + map.get("loginId") + "%");
        }
        if (null != map.get("status")) {
            sb.append(" and status = :status ");
        }
        if (null != map.get("sysCompanyId")) {
            sb.append(" and sysCompanyId = :sysCompanyId ");
        }
        if (null != map.get("superiorId")) {
            sb.append(" and superiorId = :superiorId ");
        }
        return sb.toString();
    }

    private void getUserSuperiorIds(Set<Integer> set, Integer id) throws Exception {
        SysCompanyUser _obj = (SysCompanyUser) getObject(SysCompanyUser.class, id);
        if (null != _obj) {
            set.add(_obj.getSuperiorId());
            getUserSuperiorIds(set, _obj.getSuperiorId());
        }
    }

    private void deleteSysCompanyUserRelByChildId(Integer id) throws Exception {
        SysCompanyUserRelId relId = new SysCompanyUserRelId();
        relId.setChildUserId(id);
        List<?> rels = findSysCompanyUserRel(new SysCompanyUserRel(relId));
        if (null != rels && rels.size() > 0) {
            super.deleteAll(rels);
        }
    }
}
