package com.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseService {

    Object getObject(Class clazz, Serializable serializable);

    void removeObject(Class clazz, Serializable serializable);

    void saveOrUpdate(Object o);

    void deleteAll(Collection entities);

    void deleteAll(Class clazz, Collection ids);

    List findPageByQuery(int pageNo, int pageSize, String hql, Map map);

    int getTotalCount(String hql, Map map);

}
