package com.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDao {

    public Object getObject(Class class1, Serializable serializable);

    public void removeObject(Class clazz, Serializable serializable);

    public void merge(Object object);

    public void save(Object object);

    /**
     * @function 分页显示符合所有的记录数
     * @param pageNo
     *            当前页数
     * @param pageSize
     *            每页显示的条数
     * @param instance
     *            将查询条件封装为Map
     * @return 查询结果List
     */
    public List findPageByQuery(int pageNo, int pageSize, String hql, Map map);

    /**
     * @function 根据查询条件查询记录数的个数
     * @param hql
     *            hql查询语句
     * @param map
     *            用map封装查询条件
     * @return 数据库中满足查询条件的数据的条数
     */
    public int getTotalCount(String hql, Map map);

}
