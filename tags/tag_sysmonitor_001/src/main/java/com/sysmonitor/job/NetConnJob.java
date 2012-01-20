package com.sysmonitor.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 19, 2012 10:13:35 AM
 */

@Component
public class NetConnJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void doJob() {
        logger.info("test...");
    }

}
