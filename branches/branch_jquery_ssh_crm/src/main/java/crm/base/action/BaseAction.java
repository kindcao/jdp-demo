package crm.base.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = -7367003790059602087L;

    protected Log log = LogFactory.getLog(BaseAction.class);

    protected Map<String, Object> session;

    private Integer rows = 3;

    private Integer page = 1;

    private String actionFlag;

    private String forward;

    protected void responseJsonData(Object obj) {
        try {
            StringBuilder sb = new StringBuilder();
            if (JSONUtils.isNull(obj)) {
                log.warn("JSONUtils.isNull(obj)");
                return;
            } 
            
            if (JSONUtils.isArray(obj)) {
                sb.append(JSONArray.fromObject(obj));
            } else if (JSONUtils.isObject(obj)) {
                sb.append(JSONObject.fromObject(obj));
            }
            if (sb.toString().length() > 0) {
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("text/json;charset=UTF-8");
                response.setHeader("Pragma", "no-cache");
                response.addHeader("Cache-Control", "must-revalidate");
                response.addHeader("Cache-Control", "no-cache");
                response.addHeader("Cache-Control", "no-store");
                response.setDateHeader("Expires", 0);
                response.getWriter().write(sb.toString());
                response.getWriter().flush();
                response.getWriter().close();
            }
        } catch (Exception e) {
            log.error(e);
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public String getForward() {
        return forward;
    }

    public void setForward(String forward) {
        this.forward = forward;
    }

}
