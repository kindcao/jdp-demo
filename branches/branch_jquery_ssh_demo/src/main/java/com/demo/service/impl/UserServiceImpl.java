package com.demo.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.demo.dao.UserDao;
import com.demo.model.User;
import com.demo.service.UserService;
import com.service.impl.BaseManagerImpl;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:16:09 PM
 */

public class UserServiceImpl extends BaseManagerImpl implements UserService {

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> getUserByPage(int start, int limit, Map map) {
        // TODO Auto-generated method stub
        String hql = "from User where username like :username";
        return userDao.findPageByQuery(start, limit, hql, map);
    }

    public int getUserTotalCount(Map map) {
        // TODO Auto-generated method stub
        String hql = "from User where username like :username";

        return this.userDao.getTotalCount(hql, map);
    }

    public void addSysUser(User sysUser) {
        userDao.save(sysUser);
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findUserByName(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findUserByNameAndPass(String username, String password) {
        return userDao.findUserByNameAndPass(username, password);
    }
}
