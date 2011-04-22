package com.service.impl;

import java.io.Serializable;

import com.dao.BaseDao;
import com.service.BaseManager;

public class BaseManagerImpl implements BaseManager {

    private BaseDao baseDao;

    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    public Object getObject(Class class1, Serializable serializable) {
        // TODO Auto-generated method stub
        return this.baseDao.getObject(class1, serializable);
    }

}
