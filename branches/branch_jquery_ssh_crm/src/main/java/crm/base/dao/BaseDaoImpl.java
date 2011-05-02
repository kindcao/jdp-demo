package crm.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import crm.common.Constants;

public class BaseDaoImpl implements BaseDao {

    private final Logger log = LoggerFactory.getLogger(BaseDaoImpl.class);

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Object getObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub
        Object o = getHibernateTemplate().get(clazz, id);
        return o;
    }

    public void merge(Object object) {
        // TODO Auto-generated method stub
        getHibernateTemplate().merge(object);
    }

    public void removeObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub
        getHibernateTemplate().delete(getHibernateTemplate().get(clazz, id));
    }

    @Override
    public void saveOrUpdate(Object object) {
        try {
            getHibernateTemplate().saveOrUpdate(object);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }

    }

    @Override
    public void deleteAll(Collection entities) {
        try {
            getHibernateTemplate().deleteAll(entities);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<?> loadAll(Class entityClass) {
        try {
            return getHibernateTemplate().loadAll(entityClass);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<?> findByExample(Object exampleEntity) {
        try {
            return getHibernateTemplate().findByExample(exampleEntity);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public void deleteAll(Class clazz, Collection ids) {
        try {
            getHibernateTemplate().deleteAll(findByIds(clazz, ids));
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<?> findByIds(Class clazz, Collection ids) {
        try {
            DetachedCriteria dc = DetachedCriteria.forClass(clazz);
            dc.add(Restrictions.in("id", ids));
            return getHibernateTemplate().findByCriteria(dc);
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }

    @Override
    public List<?> findPageByQuery(final int pageNo, final int pageSize, final String hql, final Map<String, Object> map) {
        List<?> result = null;
        try {
            result = getHibernateTemplate().executeFind(new HibernateCallback() {

                @Override
                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = null;
                    Object obj = map.get(Constants.RESULT_TRANSFORMER_DTO);
                    if (null != obj) {
                        query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(obj.getClass()));
                    } else {
                        query = session.createQuery(hql);
                    }
                    setQueryParasValue(query, map);
                    // (pageNo - 1) * pageSize
                    query.setFirstResult(pageNo);
                    query.setMaxResults(pageSize);
                    return query.list();
                }
            });
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return result;
    }

    @Override
    public int getTotalCount(final String hql, final Map<String, Object> map) {
        List<?> result = null;
        try {
            result = getHibernateTemplate().executeFind(new HibernateCallback() {

                @Override
                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    Query query = session.createQuery(hql);
                    setQueryParasValue(query, map);
                    return query.list();
                }
            });
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            throw e;
        }
        return result.size();
    }

    private void setQueryParasValue(Query query, final Map<String, Object> map) {
        Iterator<String> it = map.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Object value = map.get(key);
            if (value instanceof String) {
                query.setString(key, value.toString());
            }
            if (value instanceof Integer) {
                query.setInteger(key, (Integer) value);
            }
        }
    }
}
