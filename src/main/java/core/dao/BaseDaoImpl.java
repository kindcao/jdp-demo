package core.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BaseDaoImpl implements BaseDao {

    protected Log log = LogFactory.getLog(BaseDaoImpl.class);

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

    public List findPageByQuery(final int pageNo, final int pageSize,
            final String hql, final Map map) {
        // TODO Auto-generated method stub
        List result = null;
        try {
            result = getHibernateTemplate().executeFind(
                    new HibernateCallback() {

                        public Object doInHibernate(Session session)
                                throws HibernateException {
                            Query query = session.createQuery(hql);
                            Iterator it = map.keySet().iterator();
                            while (it.hasNext()) {
                                Object key = it.next();
                                query.setString(key.toString(), map.get(key)
                                        .toString());
                            }
                            System.out.println("pageNo=" + pageNo);
                            query.setFirstResult(pageNo); // (pageNo - 1) *
                            // pageSize
                            query.setMaxResults(pageSize);
                            return query.list();
                        }
                    });
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return result;
    }

    public int getTotalCount(final String hql, final Map map) {
        // TODO Auto-generated method stub
        List result = null;

        try {
            result = getHibernateTemplate().executeFind(
                    new HibernateCallback() {

                        public Object doInHibernate(Session session)
                                throws HibernateException, SQLException {
                            // TODO Auto-generated method stub
                            Query query = session.createQuery(hql);
                            Iterator<String> it = map.keySet().iterator();
                            while (it.hasNext()) {
                                Object key = it.next();
                                query.setString(key.toString(), map.get(key)
                                        .toString());
                            }
                            return query.list();
                        }
                    });
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
        return result.size();
    }

    @Override
    public void saveOrUpdate(Object object) {
        try {
            getHibernateTemplate().saveOrUpdate(object);
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }

    }

    @Override
    public void deleteAll(Collection entities) {
        try {
            getHibernateTemplate().deleteAll(entities);
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
    }

    @Override
    public List<?> loadAll(Class entityClass) {
        try {
            return getHibernateTemplate().loadAll(entityClass);
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
    }

    @Override
    public List<?> findByExample(Object exampleEntity) {
        try {
            return getHibernateTemplate().findByExample(exampleEntity);
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
    }

    @Override
    public void deleteAll(Class clazz, Collection ids) {
        try {
            getHibernateTemplate().deleteAll(findByIds(clazz, ids));
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
    }

    public List<?> findByIds(Class clazz, Collection ids) {
        try {
            DetachedCriteria dc = DetachedCriteria.forClass(clazz);
            dc.add(Restrictions.in("id", ids));
            return getHibernateTemplate().findByCriteria(dc);
        } catch (RuntimeException e) {
            log.error(e);
            throw e;
        }
    }
}
