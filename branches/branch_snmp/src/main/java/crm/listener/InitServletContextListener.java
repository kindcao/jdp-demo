package crm.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import crm.common.MstDataLoader;

/**
 * @author Kind Cao
 * @version $Rev$, May 6, 2011 5:13:41 PM
 */
public class InitServletContextListener implements ServletContextListener {

    private final Logger log = LoggerFactory.getLogger(InitServletContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        log.info("Init servlet context listener stop.");
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        log.info("Init servlet context listener start....");
        MstDataLoader.loadMstData(event.getServletContext());
        log.info("Init servlet context listener end.");
    }

}
