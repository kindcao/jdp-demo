package com.sysmonitor.snmp;

import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 11, 2012 4:03:31 PM
 */
public class TextResponseListener implements ResponseListener {

    private PDU response;

    public PDU getResponse() {
        return response;
    }

    public void onResponse(ResponseEvent event) {
        ((Snmp) event.getSource()).cancel(event.getRequest(), this);
        response = event.getResponse();
        synchronized (this) {
            this.notify();
        }
    }

}
