package com.sysmonitor.common;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 11:14:25 AM
 */
public class Constants {

    //

    public static String ifIndex = "1.3.6.1.2.1.2.2.1.1.";

    public static String ifDescr = "1.3.6.1.2.1.2.2.1.2.";

    public static String ifInOctets = "1.3.6.1.2.1.2.2.1.10.";

    public static String ifOutOctets = "1.3.6.1.2.1.2.2.1.16.";

    //  
    public static final String OS_TYPE_WINDOWS = "Windows";

    public static final String OS_TYPE_LINUX = "Linux";

    //
    public static final String UNDERLINE = "_";

    public static final String DOT = ".";

    public static final String SUB_CHART_SAVE_PATH = "images/chart/";

    public static final int DEFAULT_ROWS = 2;

    public static final int DEFAULT_PAGE = 1;

    public static final String CFG_FILE_PATH = "conf/sysmonitor-cfg.properties";

    public static final String LOG_FILE_PATH = "conf/log4j.properties";

    public static final String CTX_FILE_PATH = "conf/applicationContext.xml";

    //
    public static enum Storeage {
        Windows("1.3.6.1.2.1.25.2.3.1.3.", "1.3.6.1.2.1.25.2.3.1.4.", "1.3.6.1.2.1.25.2.3.1.5.",
                "1.3.6.1.2.1.25.2.3.1.6.");

        // 名称
        private String hrStorageDescr;

        // 分配单元
        private String hrStorageAllocationUnits;

        // 总簇数
        private String hrStorageSize;

        // 已用簇数
        private String hrStorageUsed;

        public String getHrStorageDescr() {
            return hrStorageDescr;
        }

        public void setHrStorageDescr(String hrStorageDescr) {
            this.hrStorageDescr = hrStorageDescr;
        }

        public String getHrStorageAllocationUnits() {
            return hrStorageAllocationUnits;
        }

        public void setHrStorageAllocationUnits(String hrStorageAllocationUnits) {
            this.hrStorageAllocationUnits = hrStorageAllocationUnits;
        }

        public String getHrStorageSize() {
            return hrStorageSize;
        }

        public void setHrStorageSize(String hrStorageSize) {
            this.hrStorageSize = hrStorageSize;
        }

        public String getHrStorageUsed() {
            return hrStorageUsed;
        }

        public void setHrStorageUsed(String hrStorageUsed) {
            this.hrStorageUsed = hrStorageUsed;
        }

        private Storeage(String hrStorageDescr, String hrStorageAllocationUnits, String hrStorageSize,
                String hrStorageUsed) {
            this.hrStorageDescr = hrStorageDescr;
            this.hrStorageAllocationUnits = hrStorageAllocationUnits;
            this.hrStorageSize = hrStorageSize;
            this.hrStorageUsed = hrStorageUsed;
        }
    }

}
