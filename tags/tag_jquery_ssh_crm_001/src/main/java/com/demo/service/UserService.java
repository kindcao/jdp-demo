package com.demo.service;

import java.util.List;
import java.util.Map;

import com.demo.model.User;
import com.service.BaseService;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:15:00 PM
 */
public interface UserService extends BaseService {

    User findUserByNameAndPass(String username, String password);

    User findUserByName(String username);

    List<User> findPageByQuery(int pageNo, int pageSize, Map map);

    int getTotalCount(Map map);
}
