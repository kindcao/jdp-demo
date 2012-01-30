package com.sysmonitor.job;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sysmonitor.model.NfHost;
import com.sysmonitor.model.NfLog;
import com.sysmonitor.service.NetFlowService;

/**
 * @author Kind Cao
 * @version $Rev: $, Jan 30, 2012 2:23:05 PM
 */
public class NetFlowJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private NetFlowService netFlowService;

    @Override
    protected void job() {
        NfHost nh = new NfHost();
        nh.setStatus("1");
        List<?> list = netFlowService.findByExample(nh);
        logger.info("host address list size : " + list.size());

        //
        List<NfHost> results = new ArrayList<NfHost>();
        NfLog nl = null;
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            NfHost ele = (NfHost) iterator.next();
        }
    }

    @Resource
    public void setNetFlowService(NetFlowService netFlowService) {
        this.netFlowService = netFlowService;
    }

}
