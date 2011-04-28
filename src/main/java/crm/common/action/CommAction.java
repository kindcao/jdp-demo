package crm.common.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.action.BaseAction;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 4:19:25 PM
 */
public class CommAction extends BaseAction {

    private static final long serialVersionUID = 7374356470643624209L;

    protected Log log = LogFactory.getLog(CommAction.class);

    public String welcome() throws Exception {
        return "welcome";
    }
}
