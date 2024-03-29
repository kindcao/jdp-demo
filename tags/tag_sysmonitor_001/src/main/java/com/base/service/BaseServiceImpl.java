package com.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.base.dao.BaseDao;

public class BaseServiceImpl implements BaseService {

    private BaseDao baseDaoImpl;

    @Resource
    public void setBaseDaoImpl(BaseDao baseDaoImpl) {
        this.baseDaoImpl = baseDaoImpl;
    }

    public Object getObject(Class class1, Serializable serializable) {
        return baseDaoImpl.getObject(class1, serializable);
    }

    @Override
    public void saveOrUpdate(Object object) {
        baseDaoImpl.saveOrUpdate(object);
    }

    @Override
    public List findPageByQuery(int pageNo, int pageSize, String hql, Map map) {
        return baseDaoImpl.findPageByQuery(pageNo, pageSize, hql, map);
    }

    @Override
    public int getTotalCount(String hql, Map map) {
        return baseDaoImpl.getTotalCount(hql, map);
    }

    @Override
    public void removeObject(Class clazz, Serializable serializable) {
        baseDaoImpl.removeObject(clazz, serializable);
    }

    @Override
    public void deleteAll(Class clazz, Collection ids) {
        baseDaoImpl.deleteAll(clazz, ids);
    }

    @Override
    public void deleteAll(Collection entities) {
        baseDaoImpl.deleteAll(entities);
    }

    @Override
    public List<?> loadAll(Class entityClass) {
        return baseDaoImpl.loadAll(entityClass);
    }

    @Override
    public List<?> findByExample(Object exampleEntity) {
        return baseDaoImpl.findByExample(exampleEntity);
    }

}
