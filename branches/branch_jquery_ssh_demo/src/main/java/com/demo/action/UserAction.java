package com.demo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.action.BaseAction;
import com.action.json.JsonListResult;
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

public class UserAction extends BaseAction {

    private static final long serialVersionUID = -2322517524694073991L;

    protected Log log = LogFactory.getLog(UserAction.class);

    private String id;

    private String username;

    private String password;

    private String email;

    private UserService userService;

    private JsonValidateResult jvr = new JsonValidateResult();

    private JsonListResult jlr = new JsonListResult();

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
            jvr.setSuccess(true);
        } else {
            jvr.setSuccess(false);
            jvr.setErrors("Error username or password");
        }
        responseJsonData(jvr);
        return LOGIN;
    }

    public String logout() throws Exception {
        User user = (User) ServletActionContext.getContext().getSession()
                .remove("_CURR_USER");
        if (user != null) {
            DATA_MAP.remove(user.getUsername());
            log.info("user " + user.getUsername() + " logout.");
        }
        return LOGIN;
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

    public String addUser() throws Exception {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setUsername(username);
        if (null == userService.findUserByName(username)) {
            userService.saveOrUpdate(user);
            jvr.setSuccess(true);

        } else {
            jvr.setSuccess(false);
            jvr.setErrors("User name has exist!");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String delUser() throws Exception {
        if (id != null) {
            List<Integer> ids = new ArrayList<Integer>();
            String[] _idStr = id.split(",");
            for (int i = 0; i < _idStr.length; i++) {
                ids.add(Integer.valueOf(_idStr[i]));
            }
            userService.deleteAll(User.class, ids);
            jvr.setSuccess(true);
            responseJsonData(jvr);
        }
        return NONE;
    }

    public String showUserList() throws Exception {
        username = "";
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String getUserList() throws Exception {
        // Object list = session.get("mylist");
        // if (list != null) {
        // myCustomers = (List<User>) list;
        // } else {
        // log.debug("Build new List");
        // myCustomers = new ArrayList<User>();
        // }
        Map map = new HashMap();
        map.put("username", "%" + username + "%");

        // int totalCount = userService.getUserTotalCount(map);
        // setRecord(totalCount);
        // if (loadonce) {
        // setGridModel(myCustomers);
        // } else {
        // setGridModel(userService.getUserByPage(getPage(), getRows(), map));
        // }
        // setTotal((int) Math.ceil((double) getRecord() / (double) getRows()));
        // session.put("mylist", myCustomers);
        int totalCount = userService.getTotalCount(map);
        List<User> userList = userService.findPageByQuery((getPage() - 1)
                * getRows(), getRows(), map);

        // StringBuffer sb = new StringBuffer();
        // sb.append("{\"total\":").append(totalCount).append(",");
        // sb.append("\"rows\":");
        // if (gridModel != null && gridModel.size() > 0) {
        // JSONArray jsonArray = JSONArray.fromObject(gridModel);
        // sb.append(jsonArray);
        // } else {
        // sb.append("{}");
        // }
        // sb.append("}");
        // responseJsonData(sb.toString());

        jlr.setTotal(totalCount);
        jlr.setRows(userList);
        responseJsonData(jlr);
        return NONE;

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
}
