package core.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import core.dao.BaseDao;
import core.service.BaseService;

public class BaseServiceImpl implements BaseService {

    private BaseDao baseDaoImpl;

    @Resource
    public void setBaseDaoImpl(BaseDao baseDaoImpl) {
        this.baseDaoImpl = baseDaoImpl;
    }

    public Object getObject(Class class1, Serializable serializable) {
        // TODO Auto-generated method stub
        return this.baseDaoImpl.getObject(class1, serializable);
    }

    @Override
    public void saveOrUpdate(Object object) {
        baseDaoImpl.saveOrUpdate(object);
    }

    @Override
    public List findPageByQuery(int pageNo, int pageSize, String hql, Map map) {
        return baseDaoImpl.findPageByQuery(pageNo, pageSize, hql, map);
    }

    @Override
    public int getTotalCount(String hql, Map map) {
        return baseDaoImpl.getTotalCount(hql, map);
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

}
