package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.demo.dao.UserDao;
import com.demo.model.User;
import com.demo.service.UserService;
import com.service.impl.BaseServiceImpl;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findPageByQuery(int pageNo, int pageSize, Map map) {
        String hql = "from User where username like :username";
        return userDao.findPageByQuery(pageNo, pageSize, hql, map);
    }

    @Override
    public int getTotalCount(Map map) {
        // TODO Auto-generated method stub
        String hql = "from User where username like :username";
        return userDao.getTotalCount(hql, map);
    }

    @Override
    public User findUserByName(String username) {
        return userDao.findUserByName(username);
    }

    @Override
    public User findUserByNameAndPass(String username, String password) {
        return userDao.findUserByNameAndPass(username, password);
    }

}
