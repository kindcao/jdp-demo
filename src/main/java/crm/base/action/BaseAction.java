package crm.base.action;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
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
import org.springframework.context.annotation.Scope;

import com.opensymphony.xwork2.ActionSupport;

import crm.common.Constants;
import crm.json.JsonSysStatusResult;
import crm.model.SysCompany;
import crm.model.SysCompanyUser;
import crm.model.SysCompanyUserRel;
import crm.model.SysCompanyUserRelId;
import crm.syssetup.service.SysCompUserService;
import crm.util.Utils;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Jun 1, 2011 11:37:21 PM
 */
@Scope("session")
@SuppressWarnings("serial")
public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware,
        ApplicationAware, CookiesAware {

    private final Logger log = LoggerFactory.getLogger(BaseAction.class);

    protected Map<String, Object> application;

    protected Map<String, Object> session;

    protected Map<String, String> cookies;

    protected HttpServletResponse response;

    protected HttpServletRequest request;

    private SysCompUserService sysCompUserService;

    // for search list paging
    private Integer rows = Constants.DEFAULT_ROWS;

    private Integer page = Constants.DEFAULT_PAGE;

    // for action flag
    private String actionFlag;

    // for select all ids
    private String ids;

    public String welcome() throws Exception {
        return "welcome";
    }

    public String getSysStatus() throws Exception {
        JsonSysStatusResult jssr = new JsonSysStatusResult();
        if (null == getCurrSysCompUser()) {
            jssr.setStatusCode(1);
        } else {
            jssr.setOnlineUserNum(Constants.SYS_USER_MAP.size());
        }
        responseJsonData(jssr);
        return NONE;
    }

    public String getStatusYN() throws Exception {
        responseJsonData(Constants.JSON_DATA_STATUS_YN);
        return NONE;
    }

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
            log.warn("JSONUtils.isNull(obj)");
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

    protected SysCompanyUser getCurrSysCompUser() {
        return (SysCompanyUser) session.get(Constants.CURR_SYS_USER_SESSION_KEY);
    }

    protected SysCompany getCurrSysComp() {
        return (SysCompany) session.get(Constants.CURR_SYS_USER_COMP_SESSION_KEY);
    }

    protected boolean currSysCompTypeIsR() {
        SysCompany _currSysComp = getCurrSysComp();
        return Constants.STATUS_R.equals(_currSysComp.getType());
    }

    protected String getCurrSysUserChild() throws Exception {
        StringBuilder sb = new StringBuilder();
        SysCompanyUser _sysUser = getCurrSysCompUser();
        List<?> rels = sysCompUserService.findSysCompanyUserRel(new SysCompanyUserRel(new SysCompanyUserRelId(_sysUser
                .getId(), null)));
        sb.append(_sysUser.getId());
        if (null != rels) {
            sb.append(",");
            for (Iterator<?> iterator = rels.iterator(); iterator.hasNext();) {
                SysCompanyUserRel ele = (SysCompanyUserRel) iterator.next();
                sb.append(ele.getId().getChildUserId());
                if (iterator.hasNext()) {
                    sb.append(",");
                }
            }
        }
        return sb.toString();
    }

    protected Date getCurrDate() {
        return Calendar.getInstance().getTime();
    }

    @Resource
    public void setSysCompUserService(SysCompUserService sysCompUserService) {
        this.sysCompUserService = sysCompUserService;
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
