package com.demo.interceptor;

import com.demo.action.UserAction;
import com.demo.model.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 16, 2011 12:43:23 AM
 */
@SuppressWarnings("serial")
public class CheckLoginInterceptor extends MethodFilterInterceptor {

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        if (UserAction.class.getName().equals(
                invocation.getAction().getClass().getName())
                && "login".equals(invocation.getProxy().getConfig()
                        .getMethodName())) {
            return invocation.invoke();
        }

        User user = (User) invocation.getInvocationContext().getSession().get(
                "_CURR_USER");
        if (user != null) {
            return invocation.invoke();
        } else {
            return Action.LOGIN;
        }
    }
}
