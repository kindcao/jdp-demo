package crm.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.common.Constants;
import crm.model.SysCompanyUser;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 16, 2011 9:20:51 PM
 */
public class SessionListener implements HttpSessionListener {

    protected Logger log = LoggerFactory.getLogger(SessionListener.class);

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        HttpSession session = event.getSession();
        SysCompanyUser sysCompUser = (SysCompanyUser) session.getAttribute(Constants.CURR_SYS_USER_SESSION_KEY);
        if (sysCompUser != null) {
            Constants.SYS_USER_MAP.remove(sysCompUser.getLoginId());
            session.removeAttribute(Constants.CURR_SYS_USER_SESSION_KEY);
            log.info("user[" + sysCompUser.getLoginId() + "] session destroyed");
        }
    }
}
