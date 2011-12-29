package com.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao {

    Object getObject(Class clazz, Serializable serializable);

    void removeObject(Class clazz, Serializable serializable);

    void merge(Object object);

    void saveOrUpdate(Object object);

    List findPageByQuery(int pageNo, int pageSize, String hql, Map map);

    int getTotalCount(String hql, Map map);

    void deleteAll(Collection entities);

    void deleteAll(Class clazz, Collection ids);

    List<?> loadAll(Class entityClass);

}
