package com.netmonitor.util;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 5:32:01 PM
 */
public class ChartUtil {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int width;// 后台计算

    private int height;// 后台计算

    private String title;

    private String subTitle;

    private String xName;

    private String[] yName;//

    public ChartUtil() {
        this("", "", "", null, 400, 250);
    }

    /***************************************************************************
     * constructor function
     * 
     * @param type
     * @param title
     * @param subTitle
     * @param xName
     * @param yName
     */
    public ChartUtil(String title, String subTitle, String xName, String[] yName, int width, int height) {
        this.title = title;
        this.subTitle = subTitle;
        this.xName = xName;
        this.yName = yName;
        this.width = width;
        this.height = height;

    }

    /** 根据TimeSeriesCollection创建JFreeChart对象 */
    public JFreeChart createChart(TimeSeriesCollection... datasets) {
        if (null == datasets || datasets.length == 0) {
            throw new IllegalArgumentException("null==datasets||datasets.length==0");
        }
        if (null == yName || yName.length == 0 || yName.length != datasets.length) {
            throw new IllegalArgumentException("null == yName || yName.length == 0 || yName.length != datasets.length");
        }
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
        final JFreeChart chart = new JFreeChart(title, plot);
        chart.getLegend().setVerticalAlignment(VerticalAlignment.CENTER);
        chart.setBorderPaint(Color.black);
        chart.setBorderVisible(true);
        chart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        // plot.setAxisOffset(new RectangleInsets(5D, 5D, 5D, 5D));
        plot.setNoDataMessage("No Data!");
        final ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000); // 60 seconds
        axis.setAutoRangeMinimumSize(1);

        // 设置子标题
        TextTitle subtitle = new TextTitle(this.subTitle, new Font("黑体", Font.PLAIN, 10));
        chart.addSubtitle(subtitle);
        // 设置主标题
        chart.setTitle(new TextTitle(this.title, new Font("黑书", Font.ITALIC, 12)));
        // 设置背景颜色
        chart.setBackgroundPaint(new GradientPaint(0, 0, Color.white, 0, 1000, Color.blue));
        // 字体模糊边界
        chart.setAntiAlias(true);
        //
        chart.getLegend().setItemFont(new Font("黑体", Font.PLAIN, 9));
        return chart;
    }

    /** 保存为文件 */
    public void writeChartAsPNG(JFreeChart chart, String outputPath) {
        BufferedOutputStream bos = null;
        try {
            File f = new File(outputPath);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
                logger.info("save chart img dir : " + f.getParentFile().getPath());
            }
            bos = new BufferedOutputStream(new FileOutputStream(outputPath));
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(bos, chart, width, height);
            // 保存为JPEG
            // ChartUtilities.writeChartAsJPEG(bos, chart, width, height);
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
