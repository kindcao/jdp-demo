package com.sysmonitor.service;

import javax.annotation.Resource;

import com.base.service.BaseServiceImpl;
import com.sysmonitor.dao.SysResourceDao;

/**
 * @author Kind Cao
 * @version $Rev: $, Feb 3, 2012 3:11:37 PM
 */
public class SysResourceServiceImpl extends BaseServiceImpl implements SysResourceService {

    private SysResourceDao sysResourceDao;

    @Resource
    public void setSysResourceDao(SysResourceDao sysResourceDao) {
        this.sysResourceDao = sysResourceDao;
    }
}
