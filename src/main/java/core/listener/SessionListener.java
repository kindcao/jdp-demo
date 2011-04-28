package core.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.common.Constants;
import crm.model.SysUser;

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
        HttpSession session = event.getSession();
        SysUser sysUser = (SysUser) session
                .getAttribute(Constants.CURR_SYS_USER_SESSION_KEY);
        if (sysUser != null) {
            Constants.SYS_USER_MAP.remove(sysUser.getUsername());
            session.removeAttribute(Constants.CURR_SYS_USER_SESSION_KEY);
            log.info("user[" + sysUser.getUsername() + "] session destroyed");
        }
    }
}
