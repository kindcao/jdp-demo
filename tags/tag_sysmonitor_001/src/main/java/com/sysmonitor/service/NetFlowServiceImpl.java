package com.sysmonitor.service;

import javax.annotation.Resource;

import com.base.service.BaseServiceImpl;
import com.sysmonitor.dao.NetFlowDao;

/**
 * @author Kind Cao
 * @version $Rev: $, Jan 27, 2012 3:16:20 PM
 */
public class NetFlowServiceImpl extends BaseServiceImpl implements NetFlowService {

    private NetFlowDao netFlowDao;

    @Resource
    public void setNetFlowDao(NetFlowDao netFlowDao) {
        this.netFlowDao = netFlowDao;
    }

}
