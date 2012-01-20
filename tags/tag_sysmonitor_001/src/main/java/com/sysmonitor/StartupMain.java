package com.sysmonitor;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.sysmonitor.common.Config;
import com.sysmonitor.common.Constants;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 19, 2012 11:01:57 AM
 */
public class StartupMain {

    private static final Logger logger = LoggerFactory.getLogger(StartupMain.class);

    static {
        PropertyConfigurator.configure(Constants.LOG_FILE_PATH);
        Config.getInstance();
    }

    public static void main(String[] args) {
        logger.info("start server ...");
        ApplicationContext ctx = new FileSystemXmlApplicationContext(Constants.CTX_FILE_PATH);
    }

}
