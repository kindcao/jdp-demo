package snmp.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 12, 2011 10:16:04 PM
 */
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);

    private static final Properties properties = new Properties();

    private static final Config cfg = new Config();

    static {
        try {
            properties.load(new FileInputStream(Constants.CFG_FILE_PATH));
        } catch (IOException e) {
            logger.info("Load config file error: " + e);
        }
    }

    private Config() {
    }

    public static final Config getInstance() {
        return cfg;
    }

    /**
     * 根据key获取value
     * 
     * @param oid
     * @return
     */
    public String getValue(String key) {
        return properties.getProperty(key);
    }

    public void setValue(String key, String newValue) {
        properties.setProperty(key, newValue);
        try {
            properties.store(new FileOutputStream(Constants.CFG_FILE_PATH), Constants.CFG_FILE_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
