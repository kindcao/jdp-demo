package com.sysmonitor.service;

import javax.annotation.Resource;

import com.base.service.BaseServiceImpl;
import com.sysmonitor.dao.NetConnDao;

/**
 * @author Kind Cao
 * @version $Rev: $, Jan 27, 2012 3:16:20 PM
 */
public class NetConnServiceImpl extends BaseServiceImpl implements NetConnService {

    private NetConnDao netConnDao;

    @Resource
    public void setNetConnDao(NetConnDao netConnDao) {
        this.netConnDao = netConnDao;
    }

}
