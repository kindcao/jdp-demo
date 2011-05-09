package crm.syssetup.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.SysCompanyUser;
import crm.syssetup.service.SysCompUserService;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:09:55 PM
 */

public class SysCompUserAction extends BaseAction {

    private static final long serialVersionUID = -2322517524694073991L;

    protected Logger log = LoggerFactory.getLogger(SysCompUserAction.class);

    private SysCompanyUser sysCompUser;

    private SysCompUserService sysCompUserService;

    @SuppressWarnings("unchecked")
    public String login() throws Exception {
        if (null == application.get(Constants.SYS_USER_APPLICATION_KEY)) {
            application.put(Constants.SYS_USER_APPLICATION_KEY, Constants.SYS_USER_MAP);
        }

        if (Constants.SYS_USER_MAP.containsKey(sysCompUser.getLoginId())) {
            log.warn("user " + sysCompUser.getLoginId() + " duplicate login.");
            HttpSession lastSession = (HttpSession) Constants.SYS_USER_MAP.get(sysCompUser.getLoginId());
            lastSession.removeAttribute(Constants.CURR_SYS_USER_SESSION_KEY);
            lastSession = request.getSession();
        }
        //
        JsonValidateResult jvr = new JsonValidateResult();
        SysCompanyUser _sysCompUser = new SysCompanyUser();
        _sysCompUser.setStatus(Constants.STATUS_A);
        _sysCompUser.setDeleteFlag(Constants.STATUS_N);
        _sysCompUser.setLoginId(sysCompUser.getLoginId());
        _sysCompUser.setPasswd(sysCompUser.getPasswd());
        List<?> list = sysCompUserService.findByExample(_sysCompUser);
        if (list != null && list.size() > 0) {
            log.info("user " + sysCompUser.getLoginId() + " login.");
            session.put(Constants.CURR_SYS_USER_SESSION_KEY, list.get(0));
            Constants.SYS_USER_MAP.put(sysCompUser.getLoginId(), request.getSession());
            jvr.setSuccess(true);
        } else {
            jvr.setSuccess(false);
            jvr.setErrors("Error username or password");
        }
        responseJsonData(jvr);
        return NONE;
    }

    public String logout() throws Exception {
        SysCompanyUser _sysCompUser = (SysCompanyUser) session.remove(Constants.CURR_SYS_USER_SESSION_KEY);
        if (_sysCompUser != null) {
            Constants.SYS_USER_MAP.remove(sysCompUser.getLoginId());
            log.info("user " + sysCompUser.getLoginId() + " logout.");
        }
        return LOGIN;
    }

    public String forward() throws Exception {
        if ("login".equals(getForward())) {
            return LOGIN;
        } else if ("list".equals(getForward())) {
            return "list";
        }
        return NONE;
    }

    public String addUser() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        sysCompUser.setStatus(Constants.STATUS_A);
        sysCompUser.setDeleteFlag(Constants.STATUS_N);
        if ("U".equals(getActionFlag())) {
            sysCompUser.setId(sysCompUser.getId());
            sysCompUserService.saveOrUpdate(sysCompUser);
            jvr.setSuccess(true);
        } else {
            SysCompanyUser _sysCompUser = new SysCompanyUser();
            _sysCompUser.setLoginId(sysCompUser.getLoginId());
            if (null == sysCompUserService.findByExample(_sysCompUser)) {
                sysCompUserService.saveOrUpdate(sysCompUser);
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
        // if (Integer.parseInt(id) > 0) {
        // SysUser sysUser = (SysUser)
        // sysCompUserService.getObject(SysUser.class, Integer.valueOf(id));
        // jor.setObj(sysUser);
        // }
        // responseJsonData(jor);
        return NONE;
    }

    public String delUser() throws Exception {
        // if (id != null) {
        // List<Integer> ids = new ArrayList<Integer>();
        // String[] _idStr = id.split(",");
        // for (int i = 0; i < _idStr.length; i++) {
        // ids.add(Integer.valueOf(_idStr[i]));
        // }
        // sysCompUserService.deleteAll(SysUser.class, ids);
        // jvr.setSuccess(true);
        // responseJsonData(jvr);
        // }
        return NONE;
    }

    @SuppressWarnings("unchecked")
    public String getUserList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        // Object list = session.get("mylist");
        // if (list != null) {
        // myCustomers = (List<User>) list;
        // } else {
        // log.debug("Build new List");
        // myCustomers = new ArrayList<User>();
        // }
        Map map = new HashMap();
        // map.put("username", "%" + sysCompUser.getLoginId() + "%");

        // int totalCount = userService.getUserTotalCount(map);
        // setRecord(totalCount);
        // if (loadonce) {
        // setGridModel(myCustomers);
        // } else {
        // setGridModel(userService.getUserByPage(getPage(), getRows(), map));
        // }
        // setTotal((int) Math.ceil((double) getRecord() / (double) getRows()));
        // session.put("mylist", myCustomers);
        int totalCount = sysCompUserService.getTotalCount(map);
        List<?> userList = sysCompUserService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);

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

    @Resource
    public void setSysUserService(SysCompUserService sysCompUserService) {
        this.sysCompUserService = sysCompUserService;
    }

    public SysCompanyUser getSysCompUser() {
        return sysCompUser;
    }

    public void setSysCompUser(SysCompanyUser sysCompUser) {
        this.sysCompUser = sysCompUser;
    }

}
