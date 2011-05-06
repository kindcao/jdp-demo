package crm.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.base.service.BaseService;
import crm.base.service.BaseServiceImpl;

/**
 * @author Kind Cao
 * @version $Rev$, May 6, 2011 5:13:41 PM
 */
public class InitServletContextListener implements ServletContextListener {

    private final Logger log = LoggerFactory.getLogger(InitServletContextListener.class);

    private ServletContext context;

    private BaseService baseService = new BaseServiceImpl();

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        log.info("Init servlet context listener stop.");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("Init servlet context listener start.");
        context = event.getServletContext();
    }
    
    private void loadMstData(){
        
    }

}
