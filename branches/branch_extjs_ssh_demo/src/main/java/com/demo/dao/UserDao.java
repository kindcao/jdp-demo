package com.demo.dao;

import java.util.List;

import com.dao.BaseDao;
import com.demo.model.User;

public interface UserDao extends BaseDao {

    public Object getObject(int id);

    public void update(User user);

    public void delete(int id);

    public void delete(User user);

    public List<User> findAll();

    public User findUserByNameAndPass(String username, String password);

    public User findUserByName(String username);

}
