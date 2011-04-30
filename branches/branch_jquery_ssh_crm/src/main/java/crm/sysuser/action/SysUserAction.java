package crm.sysuser.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.action.BaseAction;
import core.common.Constants;
import core.json.JsonListResult;
import core.json.JsonObjectResult;
import core.json.JsonValidateResult;
import crm.model.SysUser;
import crm.sysuser.service.SysUserService;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:09:55 PM
 */

public class SysUserAction extends BaseAction {

    private static final long serialVersionUID = -2322517524694073991L;

    protected Log log = LogFactory.getLog(SysUserAction.class);

    private String id;

    private String username;

    private String password;

    private String email;

    private SysUserService sysUserService;

    private JsonValidateResult jvr = new JsonValidateResult();

    private JsonListResult jlr = new JsonListResult();

    private JsonObjectResult jor = new JsonObjectResult();

    @SuppressWarnings("unchecked")
    public String login() throws Exception {
        // String pwd = (String) DATA_MAP.get(username);
        // if (pwd != null && pwd.length() > 0 && pwd.equals(password)) {
        // log.info("user " + username + " duplicate login.");
        // responseJsonData("{success:false,errors:{info:'Duplicate login!'}}");
        // return Action.NONE;
        // }
        //
        SysUser sysUser = sysUserService.findUserByNameAndPass(username,
                password);
        if (sysUser != null) {
            log.info("user " + sysUser.getUsername() + " login.");
            getSession().put(Constants.CURR_SYS_USER_SESSION_KEY, sysUser);
            Constants.SYS_USER_MAP.put(sysUser.getUsername(), sysUser
                    .getPassword());
            jvr.setSuccess(true);
        } else {
            jvr.setSuccess(false);
            jvr.setErrors("Error username or password");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String logout() throws Exception {
        SysUser sysUser = (SysUser) getSession().remove(
                Constants.CURR_SYS_USER_SESSION_KEY);
        if (sysUser != null) {
            Constants.SYS_USER_MAP.remove(sysUser.getUsername());
            log.info("user " + sysUser.getUsername() + " logout.");
        }
        return LOGIN;
    }

    public String forward() throws Exception {
        if ("login".equals(getForward())) {
            return LOGIN;
        } else if ("list".equals(getForward())) {
            username = "";
            return "list";
        }
        return NONE;
    }

    public String addUser() throws Exception {
        SysUser sysUser = new SysUser();
        sysUser.setEmail(email);
        sysUser.setPassword(password);
        sysUser.setUsername(username);
        if ("U".equals(getActionFlag())) {
            sysUser.setId(Integer.valueOf(id));
            sysUserService.saveOrUpdate(sysUser);
            jvr.setSuccess(true);
        } else {
            if (null == sysUserService.findUserByName(username)) {
                sysUserService.saveOrUpdate(sysUser);
                jvr.setSuccess(true);
            } else {
                jvr.setSuccess(false);
                jvr.setErrors("User name has exist!");
            }
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String editUser() throws Exception {
        if (Integer.parseInt(id) > 0) {
            SysUser sysUser = (SysUser) sysUserService.getObject(SysUser.class,
                    Integer.valueOf(id));
            jor.setObj(sysUser);
        }
        responseJsonData(jor);
        return NONE;
    }

    public String delUser() throws Exception {
        if (id != null) {
            List<Integer> ids = new ArrayList<Integer>();
            String[] _idStr = id.split(",");
            for (int i = 0; i < _idStr.length; i++) {
                ids.add(Integer.valueOf(_idStr[i]));
            }
            sysUserService.deleteAll(SysUser.class, ids);
            jvr.setSuccess(true);
            responseJsonData(jvr);
        }
        return NONE;
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
        int totalCount = sysUserService.getTotalCount(map);
        List<?> userList = sysUserService.findPageByQuery((getPage() - 1)
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

    @Resource
    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

}
