package com.sysmonitor.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.DefaultDrawingSupplier;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.HorizontalAlignment;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.VerticalAlignment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sysmonitor.common.Config;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 5:32:01 PM
 */
public class ChartUtils {

    private final static Logger logger = LoggerFactory.getLogger(ChartUtils.class);

    // public static JFreeChart createChart(ChartInfo ci, TimeSeriesCollection
    // datasets) {
    // return createChart(ci, new TimeSeriesCollection[] { datasets });
    // }

    /** 根据TimeSeriesCollection创建JFreeChart对象 */
    public static JFreeChart createChart(ChartInfo ci, TimeSeriesCollection datasets) {
        if (null == datasets) {
            throw new IllegalArgumentException("null==datasets");
        }

        String[] yName = ci.getYName();
        if (null == yName || yName.length == 0) {
            throw new IllegalArgumentException("null == yName || yName.length == 0");
        }
        // String xName = ci.getXName();
        //
        // final CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new
        // DateAxis(xName));
        // for (int i = 0; i < datasets.length; i++) {
        // NumberAxis rangeAxis = new NumberAxis(yName[i]);
        // rangeAxis.setAutoRangeIncludesZero(false);
        // rangeAxis.setAutoRange(true);
        // rangeAxis.setNumberFormatOverride(new DecimalFormat("#,##0.0"));
        // rangeAxis.setLabelFont(new Font("Verdana", Font.PLAIN, 10));
        // rangeAxis.setMinorTickCount(3);
        // XYPlot xyPlot = new XYPlot(datasets[i], null, rangeAxis, new
        // StandardXYItemRenderer());
        // // subplot.setBackgroundPaint(Color.lightGray);
        // xyPlot.setDomainGridlinePaint(Color.darkGray);
        // xyPlot.setRangeGridlinePaint(Color.darkGray);
        // //
        // xyPlot.setDomainMinorGridlinePaint(Color.lightGray);
        // xyPlot.setDomainMinorGridlinesVisible(true);
        // xyPlot.setRangeMinorGridlinePaint(Color.lightGray);
        // xyPlot.setRangeMinorGridlinesVisible(true);
        // //
        // plot.add(xyPlot);
        // }
        // // plot.setAxisOffset(new RectangleInsets(1, 1, 1, 1));
        // plot.setNoDataMessage("No Data!");
        // // plot.setDrawingSupplier(getSupplier());
        // final DateAxis axis = (DateAxis) plot.getDomainAxis();
        // axis.setNegativeArrowVisible(false);
        // axis.setAutoRange(true);
        // axis.setLowerMargin(0);
        // axis.setMinorTickCount(5);
        // axis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
        // // axis.setMinorTickMarksVisible(true);
        //
        // // axis.setInverted(true);
        // // axis.setVerticalTickLabels(true);
        // // axis.setFixedAutoRange(1 * 10 * 60 * 1000); // 60 seconds
        // // axis.setAutoRangeMinimumSize(1);
        // //
        // // final JFreeChart chart = new JFreeChart(ci.getTitle(), plot);

        final Font verdanaFont10 = new Font("Verdana", Font.PLAIN, 10);
        final JFreeChart chart = ChartFactory.createTimeSeriesChart("", "", "", datasets, true, false, false);
        //
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.darkGray);
        plot.setRangeGridlinePaint(Color.darkGray);
        plot.setDomainMinorGridlinePaint(Color.lightGray);
        plot.setDomainMinorGridlinesVisible(true);
        plot.setRangeMinorGridlinePaint(Color.lightGray);
        plot.setRangeMinorGridlinesVisible(true);
        plot.setNoDataMessage("No Data!");
        plot.setDrawingSupplier(getSupplier());
        plot.setOutlineVisible(false);
        plot.setAxisOffset(new RectangleInsets(0, 1, 1, 0));
        // plot.setBackgroundPaint(Color.lightGray);

        final DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setNegativeArrowVisible(false);
        // axis.setAutoRange(true);
        // axis.setLowerMargin(0);
        axis.setAutoRangeMinimumSize(60 * 1000);
        axis.setMinorTickCount(3);
        axis.setDateFormatOverride(new SimpleDateFormat("HH:mm"));
        axis.setLabelFont(verdanaFont10);
        axis.setTickLabelFont(verdanaFont10);
        // axis.setMinorTickMarksVisible(true);
        // axis.setInverted(true);
        // axis.setVerticalTickLabels(true);
         axis.setFixedAutoRange(Config.getInstance().getIntValue("chart.maximum.range"));

        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setLabel(yName[yName.length - 1]);
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setAutoRange(true);
        rangeAxis.setNumberFormatOverride(new DecimalFormat("#,##0.0"));
        rangeAxis.setLabelFont(verdanaFont10);
        rangeAxis.setTickLabelFont(verdanaFont10);
        rangeAxis.setMinorTickCount(3);
        plot.setRangeAxis(rangeAxis);

        // 
        chart.setBorderPaint(Color.black);
        chart.setBorderVisible(false);
        chart.setBackgroundPaint(Color.white);
        chart.setTitle(new TextTitle(ci.getTitle(), new Font("宋体", Font.ITALIC, 12)));
        // 图例字体清晰
        chart.setAntiAlias(false);
        chart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        // 设置子标题
        TextTitle subtitle = new TextTitle(ci.getSubTitle(), verdanaFont10);
        subtitle.setHorizontalAlignment(HorizontalAlignment.LEFT);
        subtitle.setVerticalAlignment(VerticalAlignment.CENTER);
        subtitle.setMargin(0, 10, 0, 0);
        chart.addSubtitle(subtitle);
        // 设置Legend背景颜色
        GradientPaint bgPaint = new GradientPaint(0, 0, Color.white, 0, 1000, Color.white);
        chart.setBackgroundPaint(bgPaint);
        LegendTitle lt = chart.getLegend();
        lt.setItemFont(verdanaFont10);
        lt.setBackgroundPaint(bgPaint);
        lt.setBorder(0, 0, 0, 0);
        lt.setPadding(1, 10, 1, 10);
        return chart;
    }

    /** 保存为文件 */
    public static void writeChartAsPNG(ChartInfo ci, JFreeChart chart) {
        BufferedOutputStream bos = null;
        File srcFile = null;
        try {
            srcFile = new File(ci.getSaveFilepath());
            if (!srcFile.getParentFile().exists()) {
                srcFile.getParentFile().mkdirs();
                logger.info("save chart img dir : " + srcFile.getParentFile().getPath());
            }
            bos = new BufferedOutputStream(new FileOutputStream(srcFile.getPath()));
            // 保存为PNG
            ChartUtilities.writeChartAsPNG(bos, chart, ci.getWidth(), ci.getHeight());
            bos.close();
            //
            File destFile = new File(srcFile.getPath() + ".png");
            if (destFile.exists()) {
                destFile.delete();
            }
            srcFile.renameTo(destFile);
            srcFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
        } finally {
            bos = null;
            srcFile = null;
        }
    }

    public static DefaultDrawingSupplier getSupplier() {
        return new DefaultDrawingSupplier(new Paint[] { Color.red, Color.blue, Color.green, Color.orange,
                Color.magenta, Color.cyan, Color.pink, Color.gray, Color.yellow, ChartColor.DARK_RED,
                ChartColor.DARK_GREEN, ChartColor.LIGHT_RED, ChartColor.DARK_YELLOW, ChartColor.DARK_MAGENTA,
                ChartColor.DARK_CYAN, Color.lightGray, ChartColor.LIGHT_RED, ChartColor.LIGHT_BLUE,
                ChartColor.LIGHT_GREEN, ChartColor.LIGHT_YELLOW, ChartColor.LIGHT_MAGENTA, ChartColor.LIGHT_CYAN },
                DefaultDrawingSupplier.DEFAULT_OUTLINE_PAINT_SEQUENCE, DefaultDrawingSupplier.DEFAULT_STROKE_SEQUENCE,
                DefaultDrawingSupplier.DEFAULT_OUTLINE_STROKE_SEQUENCE, DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
    }
}