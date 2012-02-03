package com.sysmonitor.snmp;

import java.io.IOException;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.sysmonitor.snmp.SnmpRequest;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 11, 2012 5:55:57 PM
 */
public class TestSnmpRequest {

    public static void main(String[] args) throws IOException {
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress("udp:127.0.0.1/161");
        sr.getVbs().add(new VariableBinding(new OID("1.3.6.1.2.1.25.3.3.1.2.1")));
     
        System.out.println(sr.send());
    }
}
