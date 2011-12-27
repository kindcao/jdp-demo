package snmp.demo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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

    private int width;// 后台计算

    private int height;// 后台计算

    private String title;

    private String subTitle;

    private String xName;

    private String[] yName;//

    /***************************************************************************
     * constructor function
     * 
     * @param type
     * @param title
     * @param subTitle
     * @param xName
     * @param yName
     */
    public ChartUtil(String title, String subTitle, String xName, String[] yName) {
        this.title = title;
        this.subTitle = subTitle;
        this.xName = xName;
        this.yName = yName;
        this.width = 600;
        this.height = 400;

    }

    /** 根据TimeSeriesCollection创建JFreeChart对象 */
    public JFreeChart createChart(TimeSeriesCollection... datasets) {
        final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new DateAxis(xName));
        NumberAxis rangeAxis = new NumberAxis(yName[0]);
        rangeAxis.setAutoRangeIncludesZero(false);
        XYPlot subplot = new XYPlot(datasets[0], null, rangeAxis, new StandardXYItemRenderer());
        subplot.setBackgroundPaint(Color.lightGray);
        subplot.setDomainGridlinePaint(Color.white);
        subplot.setRangeGridlinePaint(Color.white);
        plot.add(subplot);
        //        
        rangeAxis = new NumberAxis(yName[1]);
        rangeAxis.setAutoRangeIncludesZero(false);
        subplot = new XYPlot(datasets[1], null, rangeAxis, new StandardXYItemRenderer());
        subplot.setBackgroundPaint(Color.lightGray);
        subplot.setDomainGridlinePaint(Color.white);
        subplot.setRangeGridlinePaint(Color.white);
        plot.add(subplot);

        final JFreeChart chart = new JFreeChart(title, plot);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.CENTER);
        chart.setBorderPaint(Color.black);
        chart.setBorderVisible(true);
        chart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 4, 4, 4, 4));
        final ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0); // 60 seconds

        // 设置子标题
        TextTitle subtitle = new TextTitle(this.subTitle, new Font("黑体", Font.BOLD, 12));
        chart.addSubtitle(subtitle);
        // 设置主标题
        chart.setTitle(new TextTitle(this.title, new Font("隶书", Font.ITALIC, 15)));
        // 设置背景颜色
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.blue));
        chart.setAntiAlias(true);
        return chart;
    }

    /** 保存为文件 */
    public void saveAsFile(JFreeChart chart, String outputPath) {
        FileOutputStream out = null;
        try {
            File outFile = new File(outputPath);
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            out = new FileOutputStream(outputPath);
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(out, chart, width, height);
            // 保存为JPEG
            // ChartUtilities.writeChartAsJPEG(out, chart, width, height);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // do nothing
                }
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getXName() {
        return xName;
    }

    public void setXName(String name) {
        xName = name;
    }

    public String[] getYName() {
        return yName;
    }

    public void setYName(String[] name) {
        yName = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

}
