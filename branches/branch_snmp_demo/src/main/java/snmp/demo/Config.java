package snmp.demo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 12, 2011 10:16:04 PM
 */
public class Config {

    private static final String filePath = "./conf/mib.properties";

    private static final Properties properties = new Properties();

    private static final Config cfg = new Config();

    static {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Config() {
    }

    public static final Config getInstance() {
        return cfg;
    }

    /**
     * 根据oid获取value
     * 
     * @param oid
     * @return
     */
    public String getValueByOID(String oid) {
        return properties.getProperty(oid);
    }

    public void setValueByOID(String oid, String value) {
        properties.setProperty(oid, value);
        try {
            properties.store(new FileOutputStream(filePath), filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
