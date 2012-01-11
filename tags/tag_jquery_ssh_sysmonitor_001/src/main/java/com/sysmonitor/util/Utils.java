package com.sysmonitor.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

/**
 * @author Kind Cao
 * @version $Rev$, Dec 30, 2011 1:38:57 PM
 */
public class Utils {

    /**
     * format and sort id string by delimiter character (default ",")
     * 
     * @param str
     *            id string
     * @return
     */
    public static final String fmtAndSortIds(String str) {
        return fmtAndSortIds(str, ",");
    }

    /**
     * format and sort id string by delimiter character
     * 
     * @param str
     *            id string
     * @param regex
     *            delimiter character
     * @return
     */
    public static final String fmtAndSortIds(String str, String regex) {
        Set<Integer> set = new TreeSet<Integer>();
        String s = StringUtils.strip(str, regex);
        if (StringUtils.isNotBlank(str)) {
            String r = "";
            String[] ss = s.split(regex);
            for (int i = 0; i < ss.length; i++) {
                if (StringUtils.isNotBlank(ss[i])) {
                    set.add(Integer.valueOf(ss[i].trim()));
                }
            }
            for (Iterator<Integer> iterator = set.iterator(); iterator.hasNext();) {
                Integer ele = (Integer) iterator.next();
                r += ele.intValue();
                if (iterator.hasNext()) {
                    r += regex;
                }
            }
            return r;
        } else {
            return str;
        }
    }

    public static final List<Integer> getIds(String str) {
        return getIds(str, ",");
    }

    public static final List<Integer> getIds(String str, String regex) {
        List<Integer> idsList = new ArrayList<Integer>();
        String ids = fmtAndSortIds(str, regex);
        if (StringUtils.isNotBlank(str)) {
            String[] _idsArr = ids.split(regex);
            for (int i = 0; i < _idsArr.length; i++) {
                idsList.add(Integer.valueOf(_idsArr[i].trim()));
            }
        }
        return idsList;
    }

    public static int getExponent(double value) {
        int exponent = 0;
        if ((int) (value / Math.pow(10, 12)) > 0) {
            exponent = 12;
        } else if ((int) (value / Math.pow(10, 9)) > 0) {
            exponent = 9;
        } else if ((int) (value / Math.pow(10, 6)) > 0) {
            exponent = 6;
        } else if ((int) (value / Math.pow(10, 3)) > 0) {
            exponent = 3;
        }
        return exponent;
    }

    public static String getUnit(int exponent) {
        StringBuilder sb = new StringBuilder();
        switch (exponent) {
        case 12:
            sb.append("T");
            break;
        case 9:
            sb.append("G");
            break;
        case 6:
            sb.append("M");
            break;
        case 3:
            sb.append("K");
            break;
        default:
            break;
        }
        sb.append("b");
        return sb.toString();
    }

    public static String fmtData(double value) {
        int exponent = getExponent(value);
        value /= Math.pow(10, exponent);
        return new DecimalFormat("#,##0.0").format(value) + " " + getUnit(exponent);
    }
}
