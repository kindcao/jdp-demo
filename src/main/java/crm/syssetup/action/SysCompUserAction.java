package crm.syssetup.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.action.BaseAction;
import crm.common.Constants;
import crm.json.JsonListResult;
import crm.json.JsonValidateResult;
import crm.model.SysCompany;
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

    private String name;

    private String loginId;

    private String status;

    public String showLogin() throws Exception {
        return LOGIN;
    }

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
            SysCompanyUser _currUser = (SysCompanyUser) list.get(0);
            log.info("user " + _currUser.getLoginId() + " login.");
            session.put(Constants.CURR_SYS_USER_SESSION_KEY, _currUser);
            Constants.SYS_USER_MAP.put(_currUser.getLoginId(), request.getSession());
            //
            Map<?, ?> _compMap = (Map<?, ?>) getCtx().getAttribute(SysCompany.class.getName());
            session.put(Constants.CURR_SYS_USER_COMP_SESSION_KEY, _compMap.get(_currUser.getSysCompanyId().toString()));
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

    public String showSysCompUserList() throws Exception {
        return "syscompuser.list";
    }

    @SuppressWarnings("unchecked")
    public String getSysCompUserList() throws Exception {
        JsonListResult jlr = new JsonListResult();
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(name)) {
            map.put("name", name);
        }
        if (StringUtils.isNotBlank(loginId)) {
            map.put("loginId", loginId);
        }
        if (StringUtils.isNotBlank(status)) {
            map.put("status", status);
        }

        int totalCount = sysCompUserService.getTotalCount(map);
        List<?> userList = sysCompUserService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        jlr.setTotal(totalCount);
        jlr.setRows(userList);
        responseJsonData(jlr);
        return NONE;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
