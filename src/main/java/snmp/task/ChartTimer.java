package snmp.task;

import java.util.Timer;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:38:44 PM
 */
public class ChartTimer {

    private AbstractChartTask ct;

    private Timer timer;

    public ChartTimer(AbstractChartTask ct) {
        this.ct = ct;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(ct, 0, ct.getPeriod());
    }

    public void stop() {
        if (timer != null)
            timer.cancel();
    }
}
