package crm.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseService {

    void removeObject(Class clazz, Serializable serializable);

    void merge(Object object);

    void saveOrUpdate(Object object);

    void saveOrUpdateAll(Collection entities);

    void deleteAll(Collection entities);

    void deleteAll(Object object, Collection ids);

    int getTotalCount(Map<String, Object> map);

    Object getObject(Class clazz, Serializable serializable);

    List<?> loadAll(Class clazz);

    List<?> findByExample(Object object);

    List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map);

    List<?> findByIds(Class clazz, Collection ids);

}
