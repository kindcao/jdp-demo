package crm.cust.action;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.action.BaseAction;

/**
 * @author Kind Cao
 * @version $Rev$, Apr 28, 2011 2:30:19 PM
 */
public class CustAction extends BaseAction {

    private static final long serialVersionUID = -8111161784038216123L;

    protected Log log = LogFactory.getLog(CustAction.class);

    private int induId;

    public String showCustList() throws Exception {
        switch (induId) {
        case 1: {
            return "cust.broker.list";
        }
        default:
            break;
        }
        return NONE;
    }

    public int getInduId() {
        return induId;
    }

    public void setInduId(int induId) {
        this.induId = induId;
    }
}
