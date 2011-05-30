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
import crm.common.MstDataLoader;
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

    public String showLogin() throws Exception {
        return LOGIN;
    }

    public String showSysCompUserList() throws Exception {
        return "syscompuser.list";
    }

    public String showSysCompUserInfo() throws Exception {
        if (null != sysCompUser && sysCompUser.getId() > 0) {
            session.put(Constants.SYS_COMP_USER_VIEW_SESSION_KEY, sysCompUserService.getObject(
                    SysCompanyUserView.class, sysCompUser.getId()));
        } else {
            log.warn("sysCompUser is null or sysCompUser.getId() is 0");
        }
        return "syscompuser.info";
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
            Map<?, ?> _compMap = (Map<?, ?>) getCtx().getAttribute(SysCompany.class.getName());
            SysCompany _currSysComp = (SysCompany) _compMap.get(_currUser.getSysCompanyId().toString());
            if (Constants.STATUS_A.equals(_currSysComp.getStatus())) {
                log.info("user " + _currUser.getLoginId() + " login.");
                session.put(Constants.CURR_SYS_USER_SESSION_KEY, _currUser);
                session.put(Constants.CURR_SYS_USER_COMP_SESSION_KEY, _currSysComp);
                Constants.SYS_USER_MAP.put(_currUser.getLoginId(), request.getSession());
                jvr.setSuccess(true);
            } else {
                jvr.setErrors("Sys company access prohibited!");
            }
        } else {
            jvr.setErrors("Login name and password invalid!");
        }
        responseJsonData(jvr);
        //
        sysCompUser = new SysCompanyUser();
        return NONE;
    }

    public String logout() throws Exception {
        SysCompanyUser _sysCompUser = (SysCompanyUser) session.remove(Constants.CURR_SYS_USER_SESSION_KEY);
        if (_sysCompUser != null) {
            Constants.SYS_USER_MAP.remove(_sysCompUser.getLoginId());
            log.info("user " + _sysCompUser.getLoginId() + " logout.");
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
            List<?> _list = sysCompUserService.findByExample(_sysCompUser);
            if (null == _list || _list.size() == 0) {
                sysCompUserService.saveOrUpdate(sysCompUser);
                jvr.setSuccess(true);
            } else {
                jvr.setSuccess(false);
                jvr.setErrors("Login id already exists!");
            }
        } else {
            sysCompUserService.saveOrUpdate(sysCompUser);
            jvr.setSuccess(true);
        }
        responseJsonData(jvr);
        // 
        MstDataLoader.loadSysCompanyUser(getCtx());
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
        if (null != sysCompUser) {
            if (StringUtils.isNotBlank(sysCompUser.getName())) {
                map.put("name", sysCompUser.getName());
            }
            if (StringUtils.isNotBlank(sysCompUser.getLoginId())) {
                map.put("loginId", sysCompUser.getLoginId());
            }
            if (StringUtils.isNotBlank(sysCompUser.getStatus())) {
                map.put("status", sysCompUser.getStatus());
            }
            if (null != sysCompUser.getSysCompanyId() && sysCompUser.getSysCompanyId() > 0) {
                map.put("sysCompanyId", sysCompUser.getSysCompanyId());
            }
            if (null != sysCompUser.getSuperiorId() && sysCompUser.getSuperiorId() > 0) {
                map.put("superiorId", sysCompUser.getSuperiorId());
            }
        }
        int totalCount = sysCompUserService.getTotalCount(map);
        List<?> userList = sysCompUserService.findPageByQuery((getPage() - 1) * getRows(), getRows(), map);
        JsonListResult jlr = new JsonListResult();
        jlr.setTotal(totalCount);
        jlr.setRows(userList);
        responseJsonData(jlr);
        //
        sysCompUser = new SysCompanyUser();
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
}
