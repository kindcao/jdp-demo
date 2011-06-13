package crm.common;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import crm.base.service.BaseService;
import crm.model.CustomerIndustry;
import crm.model.IndustryNewsType;
import crm.model.MarketEventType;
import crm.model.SysCompany;
import crm.model.SysCompanyUser;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 8, 2011 10:23:40 AM
 */
public class MstDataLoader {

    private static final Logger log = LoggerFactory.getLogger(MstDataLoader.class);

    private static BaseService baseServiceImpl;

    public static void loadMstData(ServletContext ctx) {
        log.info("loadMstData begin...");
        baseServiceImpl = (BaseService) WebApplicationContextUtils.getWebApplicationContext(ctx).getBean(
                "baseServiceImpl");
        //
        loadCustomerIndustry(ctx);
        loadSysCompany(ctx);
        loadMarketEventType(ctx);
        loadSysCompanyUser(ctx);
        loadIndustryNewsType(ctx);
        log.info("loadMstData end.");
    }

    public static void loadIndustryNewsType(ServletContext ctx) {
        log.info("loadIndustryNewsType begin...");
        try {
            List<?> list = baseServiceImpl.loadAll(IndustryNewsType.class);
            if (null != list) {
                Map<String, Object> map = new TreeMap<String, Object>();
                for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                    IndustryNewsType object = (IndustryNewsType) iterator.next();
                    map.put(object.getId().toString(), object);
                }
                ctx.setAttribute(IndustryNewsType.class.getName(), map);
                log.info("loadIndustryNewsType map size " + map.size());
            } else {
                log.warn("loadIndustryNewsType result is null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("loadIndustryNewsType end");
    }

    public static void loadMarketEventType(ServletContext ctx) {
        log.info("loadMarketEventType begin...");
        try {
            List<?> list = baseServiceImpl.loadAll(MarketEventType.class);
            if (null != list) {
                Map<String, Object> map = new TreeMap<String, Object>();
                for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                    MarketEventType object = (MarketEventType) iterator.next();
                    map.put(object.getId().toString(), object);
                }
                ctx.setAttribute(MarketEventType.class.getName(), map);
                log.info("loadMarketEventType map size " + map.size());
            } else {
                log.warn("loadMarketEventType result is null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("loadMarketEventType end");
    }

    public static void loadSysCompanyUser(ServletContext ctx) {
        log.info("loadSysCompanyUser begin...");
        try {
            SysCompanyUser sysCompUser = new SysCompanyUser();
            // sysCompUser.setStatus(Constants.STATUS_A);
            sysCompUser.setDeleteFlag(Constants.STATUS_N);
            List<?> list = baseServiceImpl.findByExample(sysCompUser);
            if (null != list) {
                Map<String, Object> map = new TreeMap<String, Object>();
                for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                    SysCompanyUser object = (SysCompanyUser) iterator.next();
                    map.put(object.getId().toString(), object);
                }
                ctx.setAttribute(SysCompanyUser.class.getName(), map);
                log.info("loadSysCompanyUser map size " + map.size());
            } else {
                log.warn("loadSysCompanyUser result is null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("loadSysCompanyUser end");
    }

    public static void loadSysCompany(ServletContext ctx) {
        log.info("loadSysCompany begin...");
        try {
            SysCompany sysComp = new SysCompany();
            // sysComp.setStatus(Constants.STATUS_A);
            List<?> list = baseServiceImpl.findByExample(sysComp);
            if (null != list) {
                Map<String, Object> map = new TreeMap<String, Object>();
                for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                    SysCompany object = (SysCompany) iterator.next();
                    map.put(object.getId().toString(), object);
                }
                ctx.setAttribute(SysCompany.class.getName(), map);
                log.info("loadSysCompany map size " + map.size());
            } else {
                log.warn("loadSysCompany result is null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("loadSysCompany end");
    }

    public static void loadCustomerIndustry(ServletContext ctx) {
        log.info("loadCustomerIndustry begin...");
        try {
            List<?> list = baseServiceImpl.loadAll(CustomerIndustry.class);
            if (null != list) {
                Map<String, Object> map = new TreeMap<String, Object>();
                for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
                    CustomerIndustry object = (CustomerIndustry) iterator.next();
                    map.put(object.getId().toString(), object);
                }
                ctx.setAttribute(CustomerIndustry.class.getName(), map);
                log.info("loadCustomerIndustry map size " + map.size());
            } else {
                log.warn("loadCustomerIndustry result is null");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        log.info("loadCustomerIndustry end");
    }
}
