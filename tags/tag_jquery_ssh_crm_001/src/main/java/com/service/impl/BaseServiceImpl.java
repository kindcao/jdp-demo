package com.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.dao.BaseDao;
import com.service.BaseService;

public class BaseServiceImpl implements BaseService {

    private BaseDao baseDao;

    @Resource
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public Object getObject(Class class1, Serializable serializable) {
        // TODO Auto-generated method stub
        return this.baseDao.getObject(class1, serializable);
    }

    @Override
    public void saveOrUpdate(Object object) {
        baseDao.saveOrUpdate(object);
    }

    @Override
    public List findPageByQuery(int pageNo, int pageSize, String hql, Map map) {
        return baseDao.findPageByQuery(pageNo, pageSize, hql, map);
    }

    @Override
    public int getTotalCount(String hql, Map map) {
        return baseDao.getTotalCount(hql, map);
    }

    @Override
    public void removeObject(Class clazz, Serializable serializable) {
        baseDao.removeObject(clazz, serializable);
    }

    @Override
    public void deleteAll(Class clazz, Collection ids) {
        baseDao.deleteAll(clazz, ids);
    }

    @Override
    public void deleteAll(Collection entities) {
        baseDao.deleteAll(entities);
    }

}
