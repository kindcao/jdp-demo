package crm.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.dao.BaseDao;

public class BaseServiceImpl implements BaseService {

    private final Logger log = LoggerFactory.getLogger(BaseServiceImpl.class);

    private BaseDao baseDaoImpl;

    public Object getObject(Class class1, Serializable serializable) {
        return baseDaoImpl.getObject(class1, serializable);
    }

    @Override
    public void saveOrUpdate(Object object) {
        baseDaoImpl.saveOrUpdate(object);
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
    public List<?> findByExample(Object exampleEntity) {
        return baseDaoImpl.findByExample(exampleEntity);
    }

    @Override
    public List<?> loadAll(Class entityClass) {
        return baseDaoImpl.loadAll(entityClass);
    }

    @Override
    public List<?> findByIds(Class clazz, Collection ids) {
        return baseDaoImpl.findByIds(clazz, ids);
    }

    @Override
    public int getTotalCount(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) {
        return null;
    }

    @Resource
    public void setBaseDaoImpl(BaseDao baseDaoImpl) {
        this.baseDaoImpl = baseDaoImpl;
    }

    public BaseDao getBaseDaoImpl() {
        return baseDaoImpl;
    }

}
