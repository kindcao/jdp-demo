package com.demo.dao.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.dao.impl.BaseDaoImpl;
import com.demo.dao.UserDao;
import com.demo.model.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public User findUserByName(String username) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find(
                "from User au where au.username = ?", username);
        if (ul != null && ul.size() >= 1) {
            return (User) ul.get(0);
        }
        return null;
    }

    public User findUserByNameAndPass(String username, String password) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find(
                "from User au where au.username = ? and au.password = ?",
                new String[] { username, password });
        if (ul != null && ul.size() >= 1) {
            return (User) ul.get(0);
        }
        return null;
    }

    public void deleteAllById(Collection entities) {
        for (Iterator iterator = entities.iterator(); iterator.hasNext();) {
            User object = (User) iterator.next();
            removeObject(User.class, object.getId());
        }
    }

}
