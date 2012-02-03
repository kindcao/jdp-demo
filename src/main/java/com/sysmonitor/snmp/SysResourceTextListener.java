package com.sysmonitor.snmp;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;

import com.sysmonitor.common.Constants;
import com.sysmonitor.common.Constants.Storeage;
import com.sysmonitor.model.SrResource;

/**
 * @author Kind Cao
 * @version $Rev: 315 $, Jan 11, 2012 4:03:31 PM
 */
public class SysResourceTextListener implements ResponseListener {

    private SysResourceEntry entry;

    private Storeage storeage;

    public SysResourceTextListener(Storeage storeage, SysResourceEntry entry) {
        this.entry = entry;
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
            entry.setDesci(new String(OctetString.fromHexString(
                    response.getVariable(new OID(storeage.getHrStorageDescr())).toString()).getValue()).trim());
            // 
            long size = response.getVariable(new OID(storeage.getHrStorageSize())).toLong();
            long units = response.getVariable(new OID(storeage.getHrStorageAllocationUnits())).toLong();
            long use = response.getVariable(new OID(storeage.getHrStorageUsed())).toLong();
            
        
        }
    }
}
