package com.demo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.json.annotations.JSON;

import com.action.BaseAction;
import com.action.json.JsonValidateResult;
import com.demo.model.User;
import com.demo.service.UserService;
import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:09:55 PM
 */

public class UserAction extends BaseAction implements SessionAware {

    /**
     * 
     */
    private static final long serialVersionUID = -2322517524694073991L;

    protected Log log = LogFactory.getLog(UserAction.class);

    private String id;

    private String username;

    private String password;

    private String email;

    private UserService userService;

    private JsonValidateResult jvrResult = new JsonValidateResult();

    // ////////////////////
    private List<User> gridModel;

    private List<User> myCustomers;

    private Integer rows = 0;

    private Integer page = 0;

    private Integer total = 0;

    private Integer record = 0;

    // private String sord;
    // private String sidx;
    // private String searchField;
    // private String searchString;
    // private String searchOper;
    private boolean loadonce = false;

    private Map<String, Object> session;

    @SuppressWarnings("unchecked")
    public String login() throws Exception {
        // String pwd = (String) DATA_MAP.get(username);
        // if (pwd != null && pwd.length() > 0 && pwd.equals(password)) {
        // log.info("user " + username + " duplicate login.");
        // responseJsonData("{success:false,errors:{info:'Duplicate login!'}}");
        // return Action.NONE;
        // }
        //
        User user = userService.findUserByNameAndPass(username, password);

        if (user != null) {
            log.info("user " + user.getUsername() + " login.");
            ActionContext.getContext().getSession().put("_CURR_USER", user);
            DATA_MAP.put(user.getUsername(), user.getPassword());
            jvrResult.setSuccess(true);
            // responseJsonData("{\"success\":true}");
        } else {
            // responseJsonData("{\"success\":false,\"errors\":\"Error username
            // or password\"}");
            jvrResult.setSuccess(false);
            jvrResult.setErrors("Error username or password");
        }
        return INPUT;
    }

    public String logout() throws Exception {
        User user = (User) ServletActionContext.getContext().getSession()
                .remove("_CURR_USER");
        if (user != null) {
            DATA_MAP.remove(user.getUsername());
            log.info("user " + user.getUsername() + " logout.");
        }
        return NONE;
    }

    public String forward() throws Exception {
        String forward = ServletActionContext.getRequest().getParameter(
                "forward");
        if ("login".equals(forward)) {
            return LOGIN;
        } else if ("main".equals(forward)) {
            return "main";
        }
        return NONE;
    }

    public String addSysUser() throws Exception {
        boolean isSuccess = false;
        User sysUser = new User();
        sysUser.setEmail(email);
        sysUser.setPassword(password);
        sysUser.setUsername(username);
        try {
            userService.addSysUser(sysUser);
            isSuccess = true;
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/json;charset=UTF-8");
            response.getWriter().write(
                    "{success:" + isSuccess + ",id:" + sysUser.getId() + "}");
            response.getWriter().flush();
        }
        return null;
    }

    public String showUserList() throws Exception {
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String getUserList() throws Exception {
        Object list = session.get("mylist");
        if (list != null) {
            myCustomers = (List<User>) list;
        } else {
            log.debug("Build new List");
            myCustomers = new ArrayList<User>();
        }
        Map map = new HashMap();
        int totalCount = userService.getUserTotalCount(map);
        setRecord(totalCount);
        if (loadonce) {
            setGridModel(myCustomers);
        } else {
            setGridModel(userService.getUserByPage(getPage(), getRows(), map));
        }
        setTotal((int) Math.ceil((double) getRecord() / (double) getRows()));
        session.put("mylist", myCustomers);
        return SUCCESS;

        // HttpServletRequest request = ServletActionContext.getRequest();
        // int start = Integer.parseInt(request.getParameter("start"));
        // int limit = Integer.parseInt(request.getParameter("limit"));
        // System.out.println("start=" + start + " limit=" + limit);
        //
        // Map map = new HashMap();
        // try {
        // List<User> userList = userService.getUserByPage(start, limit, map);
        // if (userList != null && userList.size() > 0) {
        //
        // int totalCount = userService.getUserTotalCount(map);
        //
        // JSONArray jsonArray = JSONArray.fromObject(userList);
        // HttpServletResponse response = ServletActionContext
        // .getResponse();
        // response.setContentType("text/json;charset=UTF-8");
        // response.getWriter().write(
        // "{results:" + totalCount + ",List:" + jsonArray + "}");
        // log.info("{results:" + totalCount + ",List:" + jsonArray + "}");
        // }
        // } catch (Exception e) {
        // throw new Exception("find all user error," + e);
        // }
        // return null;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserService getUserService() {
        return userService;
    }

    @Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @JSON
    public JsonValidateResult getJvrResult() {
        return jvrResult;
    }

    public List<User> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<User> gridModel) {
        this.gridModel = gridModel;
    }

    public List<User> getMyCustomers() {
        return myCustomers;
    }

    public void setMyCustomers(List<User> myCustomers) {
        this.myCustomers = myCustomers;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecord() {
        return record;
    }

    public void setRecord(Integer record) {
        this.record = record;
    }

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public void setSession(Map<String, Object> session) {
        this.session = session;
    }

    public void setJvrResult(JsonValidateResult jvrResult) {
        this.jvrResult = jvrResult;
    }

}
