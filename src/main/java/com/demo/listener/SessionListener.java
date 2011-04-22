package com.demo.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.action.BaseAction;
import com.demo.model.User;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 16, 2011 9:20:51 PM
 */
public class SessionListener implements HttpSessionListener {

    protected Log log = LogFactory.getLog(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        User user = (User) event.getSession().getAttribute("_CURR_USER");
        if (user != null) {
            BaseAction.DATA_MAP.remove(user.getUsername());
            event.getSession().removeAttribute("_CURR_USER");
            log.info("user[" + user.getUsername() + "] session destroyed");
        }
    }
}
