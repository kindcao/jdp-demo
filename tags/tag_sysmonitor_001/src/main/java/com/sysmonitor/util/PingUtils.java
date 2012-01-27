package com.sysmonitor.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 12, 2012 10:53:46 AM
 */
public class PingUtils {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public int doPingCmd(String destIp, int maxCount) {
        LineNumberReader input = null;
        try {
            String osName = System.getProperties().getProperty("os.name");
            String pingCmd = null;
            if (osName.startsWith("Windows")) {
                pingCmd = "cmd /c ping -n {0} {1}";
                pingCmd = MessageFormat.format(pingCmd, maxCount, destIp);
            } else if (osName.startsWith("Linux")) {
                pingCmd = "ping -c {0} {1}";
                pingCmd = MessageFormat.format(pingCmd, maxCount, destIp);
            } else {
                logger.warn("not support OS");
                return -2;
            }
            logger.info("OS name " + osName + ",pingCmd " + pingCmd);
            //
            Process process = Runtime.getRuntime().exec(pingCmd);
            InputStreamReader ir = new InputStreamReader(process.getInputStream());
            input = new LineNumberReader(ir);
            String line;
            List<String> reponse = new ArrayList<String>();
            while ((line = input.readLine()) != null) {
                if (!"".equals(line)) {
                    reponse.add(line);
                }
            }
            if (osName.startsWith("Windows")) {
                return parseWindowsMsg(reponse);
            } else if (osName.startsWith("Linux")) {
                return parseLinuxMsg(reponse);
            }
        } catch (IOException e) {
            logger.error("IOException   " + e.getMessage());
        } finally {
            if (null != input) {
                try {
                    input.close();
                } catch (IOException ex) {
                }
            }
        }
        return -1;
    }

    private int parseWindowsMsg(List<String> reponse) {
        int countTrue = 0;
        for (String str : reponse) {
            if (str.startsWith("ю╢вт") || str.startsWith("Reply from")) {
                countTrue++;
            }
        }
        return countTrue;
    }

    private int parseLinuxMsg(List<String> reponse) {
        int countTrue = 0;
        for (String str : reponse) {
            if (str.contains("bytes from") && str.contains("icmp_seq=")) {
                countTrue++;
            }
        }
        return countTrue;
    }
}
