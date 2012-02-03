package com.sysmonitor.job;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sysmonitor.model.NcHost;
import com.sysmonitor.model.NcLog;
import com.sysmonitor.service.NetConnService;
import com.sysmonitor.util.PingUtils;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 19, 2012 10:13:35 AM
 */

public class NetConnJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PingUtils pu = new PingUtils();

    private NetConnService netConnService;

    // private int maxCount =
    // Config.getInstance().getIntValue("ping.max.count");

    @Override
    public void job() {
        NcHost nh = new NcHost();
        nh.setStatus("1");
        List<?> list = netConnService.findByExample(nh);
        logger.info("host address list size : " + list.size());
        //
        List<NcHost> results = new ArrayList<NcHost>();
        NcLog nl = null;
        int count = 0;
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            NcHost ele = (NcHost) iterator.next();
            if (StringUtils.isNotBlank(ele.getHostAddress())) {
                count = pu.doPingCmd(ele.getHostAddress(), ele.getTimes());
                if (count != ele.getTimes()) {
                    nl = new NcLog();
                    nl.setNcHost(ele);
                    nl.setOccurrenceTime(Calendar.getInstance().getTime());
                    netConnService.saveOrUpdate(nl);
                    logger.info("host address[" + ele.getHostAddress() + "] save log data.");
                    //
                    NcHost _nh = new NcHost();
                    _nh.setHostAddress(ele.getHostAddress());
                    _nh.setHostName(ele.getHostName());
                    results.add(_nh);
                }
            } else {
                logger.warn("hostAddress is null");
            }
        }
        //    
        jlr.setRows(results);
        jlr.setTotal(jlr.getRows().size());
    }

    @Resource
    public void setNetConnService(NetConnService netConnService) {
        this.netConnService = netConnService;
    }

}
