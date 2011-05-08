package crm.base.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;

import crm.base.dao.BaseDao;

public class BaseServiceImpl implements BaseService {

    private BaseDao baseDaoImpl;

    @Resource
    public void setBaseDaoImpl(BaseDao baseDaoImpl) throws Exception {
        this.baseDaoImpl = baseDaoImpl;
    }

    public BaseDao getBaseDaoImpl() {
        return baseDaoImpl;
    }

    public Object getObject(Class class1, Serializable serializable) throws Exception {
        return baseDaoImpl.getObject(class1, serializable);
    }

    @Override
    public void saveOrUpdate(Object object) throws Exception {
        baseDaoImpl.saveOrUpdate(object);
    }

    @Override
    public void removeObject(Class clazz, Serializable serializable) throws Exception {
        baseDaoImpl.removeObject(clazz, serializable);
    }

    @Override
    public void deleteAll(Object object, Collection ids) throws Exception {
        baseDaoImpl.deleteAll(object, ids);
    }

    @Override
    public void deleteAll(Collection entities) throws Exception {
        baseDaoImpl.deleteAll(entities);
    }

    @Override
    public List<?> findByExample(Object exampleEntity) throws Exception {
        return baseDaoImpl.findByExample(exampleEntity);
    }

    @Override
    public List<?> loadAll(Class entityClass) throws Exception {
        return baseDaoImpl.loadAll(entityClass);
    }

    @Override
    public List<?> findByIds(Class clazz, Collection ids) throws Exception {
        return baseDaoImpl.findByIds(clazz, ids);
    }

    @Override
    public void merge(Object object) throws Exception {
        baseDaoImpl.merge(object);
    }

    @Override
    public void saveOrUpdateAll(Collection entities) throws Exception {
        baseDaoImpl.saveOrUpdateAll(entities);
    }

    @Override
    public List<?> findByCriteria(DetachedCriteria criteria) throws Exception {
        return baseDaoImpl.findByCriteria(criteria);
    }

    @Override
    public int getTotalCount(Map<String, Object> map) throws Exception {
        return 0;
    }

    @Override
    public List<?> findPageByQuery(int pageNo, int pageSize, Map<String, Object> map) throws Exception {
        return null;
    }

}
