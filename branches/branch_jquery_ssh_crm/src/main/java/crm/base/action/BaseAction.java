package crm.base.action;

import java.util.Calendar;
import java.util.Date;
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
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionSupport;

import crm.common.Constants;
import crm.model.SysCompanyUser;
import crm.util.Utils;

public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware,
        ApplicationAware {

    private static final long serialVersionUID = -7367003790059602087L;

    private final Logger log = LoggerFactory.getLogger(BaseAction.class);

    protected Map<String, Object> session;

    protected Map<String, Object> application;

    protected HttpServletResponse response;

    protected HttpServletRequest request;

    private Integer rows = 3;

    private Integer page = 1;

    private String actionFlag;

    private String forward;

    private String ids;

    protected void responseJsonData(Object obj) {
        responseJsonData(obj, new JsonConfig());
    }

    protected void responseJsonData(Object obj, JsonConfig cfg) {
        StringBuilder sb = new StringBuilder();
        try {
            if (JSONUtils.isNull(obj)) {
                log.warn("JSONUtils.isNull(obj)");
                return;
            }
            if (JSONUtils.isArray(obj)) {
                sb.append(JSONArray.fromObject(obj, cfg));
            } else if (JSONUtils.isObject(obj)) {
                sb.append(JSONObject.fromObject(obj, cfg));
            }
            if (sb.toString().length() > 0) {
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
            log.error(e.getMessage(), e);
        }
    }

    protected ServletContext getCtx() {
        return ServletActionContext.getServletContext();
    }

    protected SysCompanyUser getCurrSysCompUser() {
        return (SysCompanyUser) session.get(Constants.CURR_SYS_USER_SESSION_KEY);
    }

    protected Date getCurrDate() {
        return Calendar.getInstance().getTime();
    }

    protected Integer[] splitIdsStrByRegex(String str) {
        return splitIdsStrByRegex(str, ",");
    }

    protected Integer[] splitIdsStrByRegex(String str, String regex) {
        Integer[] ids = null;
        if (str.startsWith(regex)) {
            str = str.replaceFirst(regex, "");
        }
        if (str.endsWith(regex)) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.reverse();
            str = sb.reverse().toString().replaceFirst(regex, "");
        }
        //
        String[] strArr = str.split(regex.trim());
        if (strArr != null && strArr.length > 0) {
            ids = new Integer[strArr.length];
            for (int i = 0; i < strArr.length; i++) {
                ids[i] = Integer.valueOf(strArr[i].trim());
            }
        }
        return ids;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = Utils.fmtAndSortIds(ids);
    }

}
