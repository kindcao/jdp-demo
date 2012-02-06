package com.sysmonitor.util;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 4, 2012 10:42:47 AM
 */
public class ChartInfo {

    private int width;

    private int height;

    private String title = "";

    private String subTitle = "";

    private String xName;

    private String[] yName;

    private String saveFilepath;

    public ChartInfo() {
        this(550, 250, "", "", "", null, ".");
    }

    public ChartInfo(int width, int height, String title, String subTitle, String xName, String[] yName,
            String saveFilepath) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.subTitle = subTitle;
        this.xName = xName;
        this.yName = yName;
        this.saveFilepath = saveFilepath;
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

    public String getSaveFilepath() {
        return saveFilepath;
    }

    public void setSaveFilepath(String saveFilepath) {
        this.saveFilepath = saveFilepath;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
