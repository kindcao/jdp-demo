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
import crm.model.SysCompanyUserView;
import crm.syssetup.service.SysCompUserService;
import crm.util.Utils;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:09:55 PM
 */

@SuppressWarnings("serial")
public class SysCompUserAction extends BaseAction {

    protected Logger log = LoggerFactory.getLogger(SysCompUserAction.class);

    private SysCompUserService sysCompUserService;

    private SysCompanyUser sysCompUser;

    private SysCompanyUserView sysCompUserView;

    public String showLogin() throws Exception {
        return LOGIN;
    }

    public String showSysCompUserList() throws Exception {
        return "syscompuser.list";
    }

    public String showSysCompUserInfo() throws Exception {
        return "syscompuser.Info";
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

    public String saveSysCompUserInfo() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        sysCompUser.setDeleteFlag(Constants.STATUS_N);
        if (StringUtils.isBlank(getActionFlag())) {
            sysCompUser.setId(null);
            //
            SysCompanyUser _sysCompUser = new SysCompanyUser();
            _sysCompUser.setLoginId(sysCompUser.getLoginId());
            if (null == sysCompUserService.findByExample(_sysCompUser)) {
                sysCompUserService.saveOrUpdate(sysCompUser);
                jvr.setSuccess(true);
            } else {
                jvr.setSuccess(false);
                jvr.setErrors("User name has exist!");
            }
        } else {
            sysCompUserService.saveOrUpdate(sysCompUser);
            jvr.setSuccess(true);
        }
        responseJsonData(jvr);
        //      
        sysCompUser = new SysCompanyUser();
        return NONE;
    }

    public String deleteSysCompUser() throws Exception {
        JsonValidateResult jvr = new JsonValidateResult();
        if (StringUtils.isNotBlank(getIds())) {
            sysCompUserService.deleteAll(new SysCompanyUser(), Utils.getIds(getIds()));
            jvr.setSuccess(true);
        } else {
            log.info("delete ids is null");
            jvr.setErrors("delete ids is null");
        }
        responseJsonData(jvr);
        return NONE;
    }

    @SuppressWarnings("unchecked")
    public String getSysCompUserList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        if (null != sysCompUserView) {
            if (StringUtils.isNotBlank(sysCompUserView.getName())) {
                map.put("name", sysCompUserView.getName());
            }
            if (StringUtils.isNotBlank(sysCompUserView.getLoginId())) {
                map.put("loginId", sysCompUserView.getLoginId());
            }
            if (StringUtils.isNotBlank(sysCompUserView.getStatus())) {
                map.put("status", sysCompUserView.getStatus());
            }
            if (null != sysCompUserView.getSysCompanyId() && sysCompUserView.getSysCompanyId() > 0) {
                map.put("sysCompanyId", sysCompUserView.getSysCompanyId());
            }
            if (null != sysCompUserView.getSuperiorId() && sysCompUserView.getSuperiorId() > 0) {
                map.put("superiorId", sysCompUserView.getSuperiorId());
            }
        }
        int totalCount = sysCompUserService.getTotalCount(map);
        List<?> userList = sysCompUserService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        JsonListResult jlr = new JsonListResult();
        jlr.setTotal(totalCount);
        jlr.setRows(userList);
        responseJsonData(jlr);
        //
        sysCompUserView = new SysCompanyUserView();
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

    public SysCompanyUserView getSysCompUserView() {
        return sysCompUserView;
    }

    public void setSysCompUserView(SysCompanyUserView sysCompUserView) {
        this.sysCompUserView = sysCompUserView;
    }
}
