package com.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport {

    public static final Map<Object, Object> DATA_MAP = new HashMap<Object, Object>();

    protected void responseJsonData(String json) throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setContentType("text/json;charset=UTF-8");
        response.setHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "must-revalidate");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.getWriter().write(json);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
