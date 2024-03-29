package com.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.dao.BaseDao;

public class BaseDaoImpl implements BaseDao {

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

    public void save(Object object) {
        // TODO Auto-generated method stub
        getHibernateTemplate().save(object);
    }

    public void removeObject(Class clazz, Serializable id) {
        // TODO Auto-generated method stub
        getHibernateTemplate().delete(getHibernateTemplate().get(clazz, id));
    }

    public List findPageByQuery(final int pageNo, final int pageSize, final String hql, final Map map) {
        // TODO Auto-generated method stub
        List result = null;
        try {
            result = getHibernateTemplate().executeFind(new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException {
                    Query query = session.createQuery(hql);
                    Iterator it = map.keySet().iterator();
                    while (it.hasNext()) {
                        Object key = it.next();
                        query.setParameter(key.toString(), map.get(key));
                    }
                    System.out.println("pageNo=" + pageNo);
                    query.setFirstResult(pageNo); // (pageNo - 1) * pageSize
                    query.setMaxResults(pageSize);
                    return query.list();
                }
            });
        } catch (RuntimeException re) {
            throw re;
        }
        return result;
    }

    public int getTotalCount(final String hql, final Map map) {
        // TODO Auto-generated method stub
        List result = null;
        try {
            result = getHibernateTemplate().executeFind(new HibernateCallback() {

                public Object doInHibernate(Session session) throws HibernateException, SQLException {
                    // TODO Auto-generated method stub
                    Query query = session.createQuery(hql);
                    Iterator it = map.keySet().iterator();
                    while (it.hasNext()) {
                        Object key = it.next();
                        query.setParameter(key.toString(), map.get(key));
                    }
                    return query.list();
                }
            });
        } catch (RuntimeException re) {
            throw re;
        }
        return result.size();
    }

}
