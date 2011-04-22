package com.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao {

    public Object getObject(Class class1, Serializable serializable);

    public void removeObject(Class clazz, Serializable serializable);

    public void merge(Object object);

    public void save(Object object);

    public List findPageByQuery(int pageNo, int pageSize, String hql, Map map);

    public int getTotalCount(String hql, Map map);

}
