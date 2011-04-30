package crm.base.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseDao {

    void removeObject(Class clazz, Serializable serializable);

    void merge(Object object);

    void saveOrUpdate(Object object);

    void deleteAll(Collection entities);

    void deleteAll(Class clazz, Collection ids);

    int getTotalCount(String hql, Map map);

    Object getObject(Class clazz, Serializable serializable);

    List<?> loadAll(Class entityClass);

    List<?> findByExample(Object exampleEntity);

    List<?> findPageByQuery(int pageNo, int pageSize, String hql, Map map);

    List<?> findByIds(Class clazz, Collection ids);

}
