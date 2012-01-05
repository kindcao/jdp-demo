package snmp.chart;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.VerticalAlignment;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 5:32:01 PM
 */
public class ChartUtil {

    public static JFreeChart createChart(ChartInfo ci, TimeSeriesCollection datasets) {
        return createChart(ci, new TimeSeriesCollection[] { datasets });
    }

    /** 根据TimeSeriesCollection创建JFreeChart对象 */
    public static JFreeChart createChart(ChartInfo ci, TimeSeriesCollection... datasets) {
        if (null == datasets || datasets.length == 0) {
            throw new IllegalArgumentException("null==datasets||datasets.length==0");
        }

        String[] yName = ci.getYName();
        if (null == yName || yName.length == 0 || yName.length != datasets.length) {
            throw new IllegalArgumentException("null == yName || yName.length == 0 || yName.length != datasets.length");
        }
        String xName = ci.getXName();
        //
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis(xName));
        for (int i = 0; i < datasets.length; i++) {
            NumberAxis rangeAxis = new NumberAxis(yName[i]);
            rangeAxis.setAutoRangeIncludesZero(false);
            rangeAxis.setAutoRange(true);
            rangeAxis.setNumberFormatOverride(new DecimalFormat("#,##0.0"));
            rangeAxis.setLabelFont(new Font("黑体", Font.PLAIN, 10));
            XYPlot subplot = new XYPlot(datasets[i], null, rangeAxis, new StandardXYItemRenderer());
            subplot.setBackgroundPaint(Color.lightGray);
            subplot.setDomainGridlinePaint(Color.white);
            subplot.setRangeGridlinePaint(Color.white);
            plot.add(subplot);
        }
        //
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // plot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        plot.setNoDataMessage("No Data!");
        final ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000); // 60 seconds
        axis.setAutoRangeMinimumSize(1);
        //
        final JFreeChart chart = new JFreeChart(ci.getTitle(), plot);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.CENTER);
        chart.setBorderPaint(Color.black);
        chart.setBorderVisible(false);
        chart.setBackgroundPaint(Color.white);
        // 设置子标题
        TextTitle subtitle = new TextTitle(ci.getSubTitle(), new Font("黑体", Font.PLAIN, 10));
        chart.addSubtitle(subtitle);
        // 设置主标题
        chart.setTitle(new TextTitle(ci.getTitle(), new Font("黑书", Font.ITALIC, 12)));
        // 设置背景颜色
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.blue));
        // 字体模糊边界
        // chart.setAntiAlias(true);
        chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 9));
        return chart;
    }

    /** 保存为文件 */
    public static void writeChartAsPNG(ChartInfo ci, JFreeChart chart) {
        BufferedOutputStream bos = null;
        try {
            File f = new File(ci.getSaveFilepath());
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            bos = new BufferedOutputStream(new FileOutputStream(f.getPath()));
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(bos, chart, ci.getWidth(), ci.getHeight());
            bos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }
}
