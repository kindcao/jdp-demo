package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.model.User;
import com.service.BaseManager;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:15:00 PM
 */
public interface UserService extends BaseManager {

    public List<User> getUserByPage(int start, int limit, Map map);

    public int getUserTotalCount(Map map);

    public void addSysUser(User sysUser);

    public List<User> findAll();

    public User findUserByNameAndPass(String username, String password);

    public User findUserByName(String username);

}
