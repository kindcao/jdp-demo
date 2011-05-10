package crm.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import crm.model.CustomerContact;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.CycleDetectionStrategy;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 10, 2011 10:27:54 AM
 */
public class JsonUtils {

    public static void main(String[] args) {
        String[] filedName = new String[] { "name", "department", "posit", "phone", "mobile", "email", "address",
                "isPrimary" };
        JsonConfig cfg = JsonUtils.setIncludes(CustomerContact.class, filedName);
        System.out.println(11);
    }

    public static JsonConfig setIncludes(Class c, String... p) {
        JsonConfig config = new JsonConfig();
        Set<String> set = new HashSet<String>();
        Field[] fs = c.getDeclaredFields();
        for (Field f : fs) {
            set.add(f.getName());
        }
        if (p != null) {
            for (String str : p) {
                set.remove(str);
            }
        }
        String[] str = new String[set.size()];
        config.setExcludes(set.toArray(str));
        return config;
    }

    /**
     * JSON 时间解析器具
     * 
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String datePattern) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(new String[] { "" });
        jsonConfig.setIgnoreDefaultExcludes(false);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(datePattern));
        return jsonConfig;
    }

    /**
     * 除去不想生成的字段（特别适合去掉级联的对象）+时间转换
     * 
     * @param excludes
     *            除去不想生成的字段
     * @param datePattern
     * @return
     */
    public static JsonConfig configJson(String[] excludes, String datePattern) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.setExcludes(excludes);
        jsonConfig.setIgnoreDefaultExcludes(true);
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(datePattern));
        return jsonConfig;
    }

}

class JsonDateValueProcessor implements JsonValueProcessor {

    private String format = "yyyy-MM-dd HH:mm:ss";

    public JsonDateValueProcessor() {
    }

    public JsonDateValueProcessor(String format) {
        this.format = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value instanceof Date[]) {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date[] dates = (Date[]) value;
            obj = new String[dates.length];
            for (int i = 0; i < dates.length; i++) {
                obj[i] = sf.format(dates[i]);
            }
        }
        return obj;
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value instanceof Date) {
            String str = new SimpleDateFormat(format).format((Date) value);
            return str;
        }
        return value == null ? null : value.toString();
    }
}
