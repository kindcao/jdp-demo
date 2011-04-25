package com.demo.dao;

import com.dao.BaseDao;
import com.demo.model.User;

public interface UserDao extends BaseDao {

    public User findUserByNameAndPass(String username, String password);

    public User findUserByName(String username);

}
