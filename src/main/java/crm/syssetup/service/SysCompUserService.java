package crm.syssetup.service;

import java.util.List;

import crm.base.service.BaseService;
import crm.model.SysCompanyUserRel;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 4, 2010 9:15:00 PM
 */
public interface SysCompUserService extends BaseService {

    List<?> findSysCompanyUserRel(SysCompanyUserRel object) throws Exception;
}
