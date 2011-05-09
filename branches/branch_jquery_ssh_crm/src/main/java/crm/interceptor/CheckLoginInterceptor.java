package crm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

import crm.common.Constants;
import crm.model.SysCompanyUser;
import crm.syssetup.action.SysCompUserAction;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 16, 2011 12:43:23 AM
 */
@SuppressWarnings("serial")
public class CheckLoginInterceptor extends MethodFilterInterceptor {

    protected Logger log = LoggerFactory.getLogger(CheckLoginInterceptor.class);

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        if ("login".equals(invocation.getProxy().getConfig().getMethodName())
                && SysCompUserAction.class.getName().equals(invocation.getAction().getClass().getName())) {
            return invocation.invoke();
        }
        //
        SysCompanyUser sysCompUser = (SysCompanyUser) invocation.getInvocationContext().getSession().get(
                Constants.CURR_SYS_USER_SESSION_KEY);
        if (sysCompUser != null) {
            return invocation.invoke();
        } else {
            log.info("Not login or session timeout,please login first");
            return Action.LOGIN;
        }
    }
}
