package snmp.demo;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:36:53 PM
 */
public class TestMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SnmpRequest req = new SnmpRequest("udp:127.0.0.1/161");
        req.setCommunity("public");
        ChartTimer ct = new ChartTimer(new ChartTimerTask(req, "1", 5 * 1000));
        ct.start();
    }
}
