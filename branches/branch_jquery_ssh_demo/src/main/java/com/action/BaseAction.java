package com.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.action.json.JsonResult;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements SessionAware {

    public static final Map<Object, Object> DATA_MAP = new HashMap<Object, Object>();

    protected Map<String, Object> session;

    private Integer rows = 3;

    private Integer page = 1;

    private String actionFlag;

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
}
