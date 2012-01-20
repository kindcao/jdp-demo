package com.sysmonitor.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sysmonitor.util.PingUtils;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 19, 2012 10:13:35 AM
 */

@Component
public class NetConnJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PingUtils pm = new PingUtils();

    @Override
    public void doJob() {
        logger.info("test...");

    }

}
