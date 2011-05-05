package crm.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 27, 2011 8:42:05 PM
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
                    set.add(Integer.valueOf(ss[i]));
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
                idsList.add(Integer.valueOf(_idsArr[i]));
            }
        }
        return idsList;
    }
}
