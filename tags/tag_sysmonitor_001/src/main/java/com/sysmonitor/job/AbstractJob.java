package com.sysmonitor.job;

import java.io.File;
import java.io.IOException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JSONUtils;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.json.JsonListResult;
import com.sysmonitor.common.Config;

/**
 * @author Kind Cao
 * @version $Rev$, Jan 19, 2012 10:58:08 AM
 */
public abstract class AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected final JsonListResult jlr = new JsonListResult();

    protected final void doJob() {
        logger.debug("start ...");
        job();
        //
        writeFile();
        //
        alarm();
        logger.debug("end");

    }

    protected void alarm() {
        logger.info("alerm ...");
    }

    protected void writeFile() {
        String dirPath = Config.getInstance().getValue("data.dir");
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        //
        JsonConfig cfg = new JsonConfig();
        StringBuilder sb = new StringBuilder();
        if (JSONUtils.isNull(jlr)) {
            logger.warn("JSONUtils.isNull(obj)");
            return;
        }
        if (JSONUtils.isArray(jlr)) {
            sb.append(JSONArray.fromObject(jlr, cfg));
        } else if (JSONUtils.isObject(jlr)) {
            sb.append(JSONObject.fromObject(jlr, cfg));
        }
        try {
            FileUtils.writeStringToFile(new File(dirFile + "/" + getClass().getSimpleName()), sb.toString(), "UTF-8");
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    protected abstract void job();
}
