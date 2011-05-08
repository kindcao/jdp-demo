package crm.base.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import crm.common.Constants;

public class BaseDaoImpl implements BaseDao {

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() throws Exception {
        return hibernateTemplate;
    }

    @Resource
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) throws Exception {
        this.hibernateTemplate = hibernateTemplate;
    }

    public Object getObject(Class clazz, Serializable id) throws Exception {
        return getHibernateTemplate().get(clazz, id);
    }

    public void merge(Object object) throws Exception {
        getHibernateTemplate().merge(object);
    }

    public void removeObject(Class clazz, Serializable id) throws Exception {
        getHibernateTemplate().delete(getHibernateTemplate().get(clazz, id));
    }

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        getHibernateTemplate().saveOrUpdate(object);
    }

    @Override
    public void deleteAll(Collection entities) throws Exception {
        getHibernateTemplate().deleteAll(entities);
    }

    @Override
    public List<?> loadAll(Class entityClass) throws Exception {
        return getHibernateTemplate().loadAll(entityClass);
    }

    @Override
    public List<?> findByExample(Object exampleEntity) throws Exception {
        return getHibernateTemplate().findByExample(exampleEntity);
    }

    @Override
    public void deleteAll(Object object, Collection ids) throws Exception {
        getHibernateTemplate().deleteAll(findByIds(object.getClass(), ids));
    }

    @Override
    public List<?> findByIds(Class clazz, Collection ids) throws Exception {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.in("id", ids));
        return getHibernateTemplate().findByCriteria(dc);
    }

    @Override
    public List<?> findPageByQuery(final int pageNo, final int pageSize, final String hql, final Map<String, Object> map)
            throws Exception {
        List<?> result = getHibernateTemplate().executeFind(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException {
                Query query = null;
                Object obj = map.get(Constants.RESULT_TRANSFORMER_DTO);
                if (null != obj) {
                    query = session.createQuery(hql).setResultTransformer(Transformers.aliasToBean(obj.getClass()));
                } else {
                    query = session.createQuery(hql);
                }
                query.setProperties(map);
                // (pageNo - 1) * pageSize
                query.setFirstResult(pageNo);
                query.setMaxResults(pageSize);
                return query.list();
            }
        });
        return result;
    }

    @Override
    public int getTotalCount(final String hql, final Map<String, Object> map) throws Exception {
        Object result = getHibernateTemplate().execute(new HibernateCallback() {

            @Override
            public Object doInHibernate(Session session) throws HibernateException, SQLException {
                Query query = session.createQuery("select count(*) " + hql);
                query.setProperties(map);
                return query.iterate().next();
            }
        });

        if (null != result) {
            return ((Long) result).intValue();
        }
        return 0;
    }

    @Override
    public void saveOrUpdateAll(Collection entities) throws Exception {
        getHibernateTemplate().saveOrUpdateAll(entities);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria criteria) throws Exception {
        return getHibernateTemplate().findByCriteria(criteria);
    }
}
