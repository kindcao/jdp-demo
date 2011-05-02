package crm.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseService {

    void removeObject(Class clazz, Serializable serializable);

    void saveOrUpdate(Object o);

    void deleteAll(Collection entities);

    void deleteAll(Class clazz, Collection ids);

    int getTotalCount(Map<String, Object> map);

    Object getObject(Class clazz, Serializable serializable);

    List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map);

    List<?> findByExample(Object exampleEntity);

    List<?> loadAll(Class entityClass);

    List<?> findByIds(Class clazz, Collection ids);

}
