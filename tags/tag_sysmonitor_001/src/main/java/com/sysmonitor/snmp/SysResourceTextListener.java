package com.sysmonitor.snmp;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.smi.OID;

import com.sysmonitor.common.Constants.Storeage;
import com.sysmonitor.job.SysResourceBean;

/**
 * @author Kind Cao
 * @version $Rev: 315 $, Jan 11, 2012 4:03:31 PM
 */
public class SysResourceTextListener implements ResponseListener {

    private SysResourceBean bean;

    private Storeage storeage;

    public SysResourceTextListener(Storeage storeage, SysResourceBean bean) {
        this.bean = bean;
        this.storeage = storeage;
    }

    public void onResponse(ResponseEvent event) {
        ((Snmp) event.getSource()).cancel(event.getRequest(), this);
        parserData(event);
        synchronized (this) {
            this.notify();
        }
    }

    private void parserData(ResponseEvent event) {
        PDU response = event.getResponse();
        //        
        if (null != response) {
            // bean.setDesci(new String(response.getVariable(new
            // OID(storeage.getHrStorageDescr())).toString()).trim());
            // 
            long size = response.getVariable(new OID(storeage.getHrStorageSize())).toLong();
            long units = response.getVariable(new OID(storeage.getHrStorageAllocationUnits())).toLong();
            long use = response.getVariable(new OID(storeage.getHrStorageUsed())).toLong();
            bean.setTotalCapacity(size * units);
            bean.setFreeCapacity((size - use) * units);
        }
    }
}
