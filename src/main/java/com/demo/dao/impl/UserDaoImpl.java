package com.demo.dao.impl;

import java.util.List;

import com.dao.impl.BaseDaoImpl;
import com.demo.dao.UserDao;
import com.demo.model.User;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    public void delete(int id) {
        // TODO Auto-generated method stub
        getHibernateTemplate().delete(getHibernateTemplate().get(User.class, id));
    }

    public void delete(User user) {
        // TODO Auto-generated method stub
        getHibernateTemplate().delete(user);
    }

    public List<User> findAll() {
        // TODO Auto-generated method stub
        return (List<User>) getHibernateTemplate().find("from User");
    }

    public User findUserByName(String username) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find("from User au where au.username = ?", username);
        if (ul != null && ul.size() >= 1) {
            return (User) ul.get(0);
        }
        return null;
    }

    public User findUserByNameAndPass(String username, String password) {
        // TODO Auto-generated method stub
        List ul = getHibernateTemplate().find("from User au where au.username = ? and au.password = ?",
                new String[] { username, password });
        if (ul != null && ul.size() >= 1) {
            return (User) ul.get(0);
        }
        return null;
    }

    public Object getObject(int id) {
        // TODO Auto-generated method stub
        return (User) getHibernateTemplate().get(User.class, id);
    }

    public void update(User user) {
        // TODO Auto-generated method stub
        getHibernateTemplate().update(user);
    }

}
