package com.netmonitor.task;

import java.util.Timer;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 8:38:44 PM
 */
public class ChartTimer {

    private AbstractChartTask chartTask;

    private Timer timer;

    public ChartTimer(AbstractChartTask chartTask) {
        this.chartTask = chartTask;
    }

    public void start() {
        timer = new Timer();
        timer.schedule(chartTask, 0, chartTask.getPeriod());
    }

    public void stop() {
        if (timer != null)
            timer.cancel();
    }

    
    public AbstractChartTask getChartTask() {
        return chartTask;
    }

    
}
