package crm.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseService {

    void removeObject(Class clazz, Serializable serializable) throws Exception;

    void merge(Object object) throws Exception;

    void saveOrUpdate(Object object) throws Exception;

    void saveOrUpdateAll(Collection entities) throws Exception;

    void deleteAll(Collection entities) throws Exception;

    void deleteAll(Object object, Collection ids) throws Exception;

    int getTotalCount(Map<String, Object> map) throws Exception;

    Object getObject(Class clazz, Serializable serializable) throws Exception;

    List<?> loadAll(Class clazz) throws Exception;

    List<?> findByExample(Object object) throws Exception;

    List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception;

    List<?> findByIds(Class clazz, Collection ids) throws Exception;

}
