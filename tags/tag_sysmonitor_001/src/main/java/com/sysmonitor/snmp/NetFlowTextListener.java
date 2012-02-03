package com.sysmonitor.snmp;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;

import com.sysmonitor.common.Constants;

/**
 * @author Kind Cao
 * @version $Rev: 315 $, Jan 11, 2012 4:03:31 PM
 */
public class NetFlowTextListener implements ResponseListener {

    private IfEntry entry;

    public NetFlowTextListener(IfEntry entry) {
        this.entry = entry;
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
            entry.setIfDescr(new String(OctetString.fromHexString(
                    response.getVariable(new OID(Constants.ifDescr)).toString()).getValue()).trim());
            //    
            entry.setIfInOctets(response.getVariable(new OID(Constants.ifInOctets)).toLong());
            entry.setTotalIfInOctets(entry.getTotalIfInOctets() + entry.getIfInOctets() - entry.getLastIfInOctets());
            //    
            entry.setIfOutOctets(response.getVariable(new OID(Constants.ifOutOctets)).toLong());
            entry
                    .setTotalIfOutOctets(entry.getTotalIfOutOctets() + entry.getIfOutOctets()
                            - entry.getLastIfOutOctets());
            //
            entry.setLastUpdateTime(System.currentTimeMillis());
        }
    }

}
