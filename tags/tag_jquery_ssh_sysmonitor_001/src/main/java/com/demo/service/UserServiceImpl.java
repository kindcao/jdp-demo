package com.demo.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.base.service.BaseServiceImpl;
import com.demo.dao.UserDao;
import com.demo.model.User;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private UserDao userDaoImpl;

    @Resource
    public void setUserDao(UserDao userDaoImpl) {
        this.userDaoImpl = userDaoImpl;
    }

    @Override
    public List<User> findPageByQuery(int pageNo, int pageSize, Map map) {
        String hql = "from User where username like :username";
        return userDaoImpl.findPageByQuery(pageNo, pageSize, hql, map);
    }

    @Override
    public int getTotalCount(Map map) {
        // TODO Auto-generated method stub
        String hql = "from User where username like :username";
        return userDaoImpl.getTotalCount(hql, map);
    }

    @Override
    public User findUserByName(String username) {
        return userDaoImpl.findUserByName(username);
    }

    @Override
    public User findUserByNameAndPass(String username, String password) {
        return userDaoImpl.findUserByNameAndPass(username, password);
    }

}
