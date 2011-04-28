package core.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import core.json.JsonResult;

public class BaseAction extends ActionSupport implements SessionAware {

    private static final long serialVersionUID = -7367003790059602087L;

    public static final String LIST = "list";

    protected Map<String, Object> session;

    private Integer rows = 3;

    private Integer page = 1;

    private String actionFlag;

    private String forward;

    protected void responseJsonData(JsonResult jr) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "must-revalidate");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.getWriter().write(JSONObject.fromObject(jr).toString());
        response.getWriter().flush();
        response.getWriter().close();
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
