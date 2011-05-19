package crm.mktevt.service;

import java.util.List;

import crm.base.service.BaseService;

/**
 * @author Kind Cao
 * @version $Rev$, May 11, 2011 2:48:07 PM
 */
public interface MktEvtService extends BaseService {

    List<?> findMktEvtCal(Object object) throws Exception;
}
