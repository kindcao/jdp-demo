package snmp;

import org.apache.log4j.PropertyConfigurator;

import snmp.common.Constants;
import snmp.net.SnmpRequest;
import snmp.task.ChartTimer;
import snmp.task.ChartTimerTask;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:36:53 PM
 */
public class SnmpMain {

    static {
        PropertyConfigurator.configure(Constants.LOG_FILE_PATH);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        SnmpRequest req = new SnmpRequest("udp:127.0.0.1/161");
        req.setCommunity("public");
        ChartTimer ct = new ChartTimer(new ChartTimerTask(req, 5 * 1000));
        ct.start();
    }
}
