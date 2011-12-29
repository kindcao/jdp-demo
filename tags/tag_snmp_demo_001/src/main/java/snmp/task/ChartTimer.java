package snmp.task;

import java.util.Timer;


/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:38:44 PM
 */
public class ChartTimer {

    private ChartTimerTask ctt;

    private Timer timer;

    public ChartTimer(ChartTimerTask ctt) {
        this.ctt = ctt;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(ctt, 0, ctt.getPeriod());
    }

    public void stop() {
        if (timer != null)
            timer.cancel();
    }
}
