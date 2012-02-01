package com.sysmonitor.snmp;

import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.OctetString;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableListener;

import com.sysmonitor.common.Constants;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 11, 2012 4:16:48 PM
 */
public class NetFlowTableListener implements TableListener {

    private boolean finished;

    //
    private IfEntry entry;

    public NetFlowTableListener(IfEntry entry) {
        this.entry = entry;
    }

    public void finished(TableEvent event) {
        finished = true;
        synchronized (event.getUserObject()) {
            event.getUserObject().notify();
        }
    }

    public boolean next(TableEvent event) {
        for (int i = 0; i < event.getColumns().length; i++) {
            String key = event.getColumns()[i].getOid().toString();
            String value = event.getColumns()[i].toValueString();
            //
            // entry.setOid(key);
            if (key.startsWith(Constants.IFINDEX)) {
                entry.setIfIndex(value);
            }
            if (key.startsWith(Constants.IFDESCR) && null == entry.getIfDescr()) {
                entry.setIfDescr(new String(OctetString.fromHexString(value).getValue()).trim());
            }
            if (key.startsWith(Constants.IFINOCTETS)) {
                entry.setIfInOctets(Long.valueOf(value));
                entry
                        .setTotalIfInOctets(entry.getTotalIfInOctets() + entry.getIfInOctets()
                                - entry.getLastIfInOctets());
            }
            if (key.startsWith(Constants.IFOUTOCTETS)) {
                entry.setIfOutOctets(Long.valueOf(value));
                entry.setTotalIfOutOctets(entry.getTotalIfOutOctets() + entry.getIfOutOctets()
                        - entry.getLastIfOutOctets());
            }
            //
            entry.setLastUpdateTime(System.currentTimeMillis());
        }
        ((Counter32) event.getUserObject()).increment();
        return true;
    }

    public boolean isFinished() {
        return finished;
    }

}
