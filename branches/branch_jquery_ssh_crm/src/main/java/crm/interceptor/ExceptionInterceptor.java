package crm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * @author Kind Cao
 * @version $Rev$, May 5, 2011 4:54:53 PM
 */
@SuppressWarnings("serial")
public class ExceptionInterceptor extends MethodFilterInterceptor {

    private Logger log = LoggerFactory.getLogger(ExceptionInterceptor.class);

    @Override
    protected String doIntercept(ActionInvocation invocation) throws Exception {
        try {
            return invocation.invoke();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
