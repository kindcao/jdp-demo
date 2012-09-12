package com.sysmonitor.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.sysmonitor.common.Config;
import com.sysmonitor.common.Constants;
import com.sysmonitor.model.NfHost;
import com.sysmonitor.model.NfLog;
import com.sysmonitor.model.NfSwitch;
import com.sysmonitor.service.NetFlowService;
import com.sysmonitor.snmp.IfEntry;
import com.sysmonitor.snmp.NetFlowTextListener;
import com.sysmonitor.snmp.SnmpRequest;
import com.sysmonitor.util.ChartInfo;
import com.sysmonitor.util.ChartUtils;
import com.sysmonitor.util.Utils;

/**
 * @author Kind Cao
 * @version $Rev: $, Jan 30, 2012 2:23:05 PM
 */
public class NetFlowJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private NetFlowService netFlowService;

    private Map<String, DataBean> beanMap = new ConcurrentHashMap<String, DataBean>();

    @Override
    protected void job() {
        NfHost nh = new NfHost();
        nh.setStatus("1");
        List<?> list = netFlowService.findByExample(nh);
        logger.info("host address list size : " + list.size());
        //      
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            NfHost nfHost = (NfHost) iterator.next();
            Set<?> switchs = nfHost.getNfSwitchs();
            for (Iterator<?> iterator2 = switchs.iterator(); iterator2.hasNext();) {
                NfSwitch nfSwitch = (NfSwitch) iterator2.next();
                if (!nfSwitch.getStatus().equals(nh.getStatus())) {
                    continue;
                }
                fetchData(nfSwitch);
                writeData(nfSwitch);
                // last
                createChart(nfSwitch);
            }
        }
    }

    private void createChart(NfSwitch nfSwitch) {
        String mapKey = nfSwitch.getNfHost().getHostAddress() + Constants.UNDERLINE + nfSwitch.getIfIndex();
        TimeSeriesCollection datasets = beanMap.get(mapKey).getTsc();
        // ======================================//
        String inKey = "In ";
        TimeSeries inSeries = datasets.getSeries(inKey);
        if (null == inSeries) {
            inSeries = new TimeSeries(inKey);
            // inSeries.setMaximumItemCount(500);
            inSeries.setMaximumItemAge(Config.getInstance().getIntValue("chart.maximum.range"));
            datasets.addSeries(inSeries);
        }
        //
        String outKey = "Out ";
        TimeSeries outSeries = datasets.getSeries(outKey);
        if (null == outSeries) {
            outSeries = new TimeSeries(outKey);
            // outSeries.setMaximumItemCount(500);
            outSeries.setMaximumItemAge(Config.getInstance().getIntValue("chart.maximum.range"));
            datasets.addSeries(outSeries);
        }
        // ======================================//
        long period = 0;
        double inRate = 0;
        double outRate = 0;
        IfEntry entry = beanMap.get(mapKey).getEntry();
        period = (System.currentTimeMillis() - entry.getLastUpdateTime()) / 1000;
        period = period != 0 ? period : 1;
        //
        // inRate = (entry.getIfInOctets() - entry.getLastIfInOctets() * 1.0) /
        // period / 1000;
        // outRate = (entry.getIfOutOctets() - entry.getLastIfOutOctets() * 1.0)
        // / period / 1000;
        inRate = (entry.getIfInOctets() - entry.getLastIfInOctets() * 1.0) / period;
        outRate = (entry.getIfOutOctets() - entry.getLastIfOutOctets() * 1.0) / period;
        // ======================================//
        inSeries.addOrUpdate(new Second(), inRate);
        logger.debug("ifInOctets=" + entry.getIfInOctets() + "\tlastIfInOctets=" + entry.getLastIfInOctets()
                + "\tinRate=" + inRate);
        entry.setLastIfInOctets(entry.getIfInOctets());
        //
        outSeries.addOrUpdate(new Second(), outRate);
        logger.debug("ifOutOctets=" + entry.getIfOutOctets() + "\tlastIfOutOctets=" + entry.getLastIfOutOctets()
                + "\toutRate=" + outRate);
        entry.setLastIfOutOctets(entry.getIfOutOctets());
        // ======================================//
        Calendar cal = Calendar.getInstance();
        double totalTime = (cal.getTimeInMillis() - entry.getTotalTime());
        totalTime = totalTime == 0 ? 1 : totalTime;
        cal.clear();
        cal.setTimeInMillis((long) totalTime + cal.getTimeInMillis());
        entry.setStrTotalTime(new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
        entry.setStrAvgIfInOctets(Utils.fmtData(entry.getTotalIfInOctets() / (totalTime / 1000)));
        entry.setStrTotalIfInOctets(Utils.fmtData(entry.getTotalIfInOctets()));
        //
        entry.setStrAvgIfOutOctets(Utils.fmtData(entry.getTotalIfOutOctets() / (totalTime / 1000)));
        entry.setStrTotalIfOutOctets(Utils.fmtData(entry.getTotalIfOutOctets()));
        // ======================================//
        StringBuilder sb = new StringBuilder(inKey);
        sb.append(" Now: " + Utils.fmtData(inRate));
        sb.append(" Avg: " + entry.getStrAvgIfInOctets());
        sb.append(" Total: " + entry.getStrTotalIfInOctets());
        inSeries.setKey(sb.toString());
        //
        sb = new StringBuilder(outSeries.getKey().toString());
        sb.append(" Now: " + Utils.fmtData(outRate));
        sb.append(" Avg: " + entry.getStrAvgIfOutOctets());
        sb.append(" Total: " + entry.getStrTotalIfOutOctets());
        outSeries.setKey(sb.toString());
        // ======================================//
        int maxExponent = Math.max(getMaxExponent(inSeries), getMaxExponent(outSeries));//
        fmtTimeSeriesDataItem(inSeries, maxExponent);
        fmtTimeSeriesDataItem(outSeries, maxExponent);
        // ======================================//
        ChartInfo ci = new ChartInfo();
        ci.setTitle(nfSwitch.getRemarks());
        // ci.setTitle(entry.getIfDescr() + "-" + getDeviceDesc(host));
        ci.setYName(new String[] { "In/Out (" + Utils.getUnit(maxExponent) + "/s)" });
        //
        String chartPath = Config.getInstance().getValue("data.dir") + "/" + Constants.SUB_CHART_SAVE_PATH
                + nfSwitch.getNfHost().getHostAddress() + "/" + nfSwitch.getIfIndex();
        ci.setSaveFilepath(chartPath);
        ChartUtils.writeChartAsPNG(ci, ChartUtils.createChart(ci, datasets));;
        //
        inSeries.setKey(inKey);
        outSeries.setKey(outKey);
        fmtTimeSeriesDataItem(inSeries, -maxExponent);
        fmtTimeSeriesDataItem(outSeries, -maxExponent);
    }

    @SuppressWarnings("unchecked")
    private int getMaxExponent(TimeSeries series) {
        List<TimeSeriesDataItem> items = series.getItems();
        TimeSeriesDataItem max = (TimeSeriesDataItem) Collections.max(items, new Comparator<TimeSeriesDataItem>() {

            @Override
            public int compare(TimeSeriesDataItem o1, TimeSeriesDataItem o2) {
                return Double.valueOf(o1.getValue().doubleValue()).compareTo(
                        Double.valueOf(o2.getValue().doubleValue()));
            }
        });
        return Utils.getExponent(max.getValue().doubleValue());
    }

    private void fmtTimeSeriesDataItem(TimeSeries series, int maxExponent) {
        for (Iterator<?> iterator = series.getItems().iterator(); iterator.hasNext();) {
            TimeSeriesDataItem ele = (TimeSeriesDataItem) iterator.next();
            series.addOrUpdate(ele.getPeriod(), ele.getValue().doubleValue() / (Math.pow(10, maxExponent)));
        }
    }

    private void fetchData(NfSwitch nfSwitch) {
        NfHost host = nfSwitch.getNfHost();
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress(host.getProtocol() + ":" + host.getHostAddress() + "/" + host.getPort());
        sr.setCommunity(host.getCommunity());
        //
        sr.getVbs().clear();
        sr.getVbs().add(new VariableBinding(new OID(Constants.ifDescr).append(nfSwitch.getIfIndex())));
        sr.getVbs().add(new VariableBinding(new OID(Constants.ifInOctets).append(nfSwitch.getIfIndex())));
        sr.getVbs().add(new VariableBinding(new OID(Constants.ifOutOctets).append(nfSwitch.getIfIndex())));
        //
        try {
            String mapKey = host.getHostAddress() + Constants.UNDERLINE + nfSwitch.getIfIndex();
            if (!beanMap.containsKey(mapKey)) {
                beanMap.put(mapKey, new DataBean());
            }
            sr.send(new NetFlowTextListener(beanMap.get(mapKey).getEntry()));
        } catch (IOException e) {
            logger.error("fetchData", e);
        } finally {
            sr.getVbs().clear();
        }
    }

    private void writeData(NfSwitch nfSwitch) {
        NfHost host = nfSwitch.getNfHost();
        String mapKey = host.getHostAddress() + Constants.UNDERLINE + nfSwitch.getIfIndex();
        IfEntry entry = beanMap.get(mapKey).getEntry();
        //
        NetFlowBean bean = null;
        long inOctets = entry.getIfInOctets() - entry.getLastIfInOctets();
        long outOctets = entry.getIfOutOctets() - entry.getLastIfOutOctets();
        if (inOctets > nfSwitch.getAlarmInOctets() || outOctets > nfSwitch.getAlarmOutOctets()) {
            NfLog nl = new NfLog();
            nl.setInOctets(inOctets);
            nl.setOutOctets(outOctets);
            nl.setOccurrenceTime(Calendar.getInstance().getTime());
            nl.setNfSwitch(nfSwitch);
            //
            logger.info("host address[" + host.getHostAddress() + "] save log data.");
            netFlowService.saveOrUpdate(nl);

            //
            bean = new NetFlowBean();
            bean.setHostAddress(host.getHostAddress());
            bean.setHostName(host.getHostName());
            bean.setInOctets(inOctets);
            bean.setOutOctets(outOctets);
            bean.setRemarks(nfSwitch.getRemarks());
            //         
            results.add(bean);
        }
    }

    @Resource
    public void setNetFlowService(NetFlowService netFlowService) {
        this.netFlowService = netFlowService;
    }

    /**
     * 
     * @author Kind Cao
     * @version $Rev: $, Jan 30, 2012 4:47:55 PM
     */
    private class DataBean {

        private TimeSeriesCollection tsc = new TimeSeriesCollection();

        private IfEntry entry = new IfEntry();

        public TimeSeriesCollection getTsc() {
            return tsc;
        }

        public IfEntry getEntry() {
            return entry;
        }

    }

}
