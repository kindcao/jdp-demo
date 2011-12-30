package com.base.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.CookiesAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netmonitor.common.Constants;
import com.netmonitor.util.Utils;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware,
        ApplicationAware, CookiesAware {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected Map<String, Object> application;

    protected Map<String, Object> session;

    protected Map<String, String> cookies;

    protected HttpServletResponse response;

    protected HttpServletRequest request;

    // for search list paging
    private Integer rows = Constants.DEFAULT_ROWS;

    private Integer page = Constants.DEFAULT_PAGE;

    // for action flag
    private String actionFlag;

    // for select all ids
    private String ids;

    public static final Map<Object, Object> DATA_MAP = new HashMap<Object, Object>();

    protected void responseJsonData(String data) throws IOException {
        response.setContentType("text/json;charset=utf-8");
        response.setHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.getWriter().write(data);
        response.getWriter().flush();
        response.getWriter().close();
    }

    protected void responseJsonData(Object obj) throws IOException {
        responseJsonData(obj, new JsonConfig());
    }

    protected void responseJsonData(Object obj, JsonConfig cfg) throws IOException {
        StringBuilder sb = new StringBuilder();
        if (JSONUtils.isNull(obj)) {
            logger.warn("JSONUtils.isNull(obj)");
            return;
        }
        if (JSONUtils.isArray(obj)) {
            sb.append(JSONArray.fromObject(obj, cfg));
        } else if (JSONUtils.isObject(obj)) {
            sb.append(JSONObject.fromObject(obj, cfg));
        }
        if (sb.toString().length() > 0) {
            response.setContentType("text/json;charset=utf-8");
            response.setHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.getWriter().write(sb.toString());
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
        ServletActionContext.getContext().setSession(session);
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application = application;
        ServletActionContext.getContext().setApplication(application);
    }

    @Override
    public void setCookiesMap(Map<String, String> cookies) {
        this.cookies = cookies;
    }

    protected ServletContext getCtx() {
        return ServletActionContext.getServletContext();
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = Utils.fmtAndSortIds(ids);
    }

}
