package core.interceptor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import core.common.Constants;
import crm.model.SysUser;
import crm.sysuser.action.SysUserAction;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 16, 2011 12:43:23 AM
 */
@SuppressWarnings("serial")
public class CheckLoginInterceptor extends MethodFilterInterceptor {

    protected Log log = LogFactory.getLog(CheckLoginInterceptor.class);

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        if (SysUserAction.class.getName().equals(
                invocation.getAction().getClass().getName())
                && "login".equals(invocation.getProxy().getConfig()
                        .getMethodName())) {
            return invocation.invoke();
        }

        SysUser sysUser = (SysUser) invocation.getInvocationContext()
                .getSession().get(Constants.CURR_SYS_USER_SESSION_KEY);
        if (sysUser != null) {
            return invocation.invoke();
        } else {
            log.info("Not login,please login first");
            return Action.LOGIN;
        }
    }
}
