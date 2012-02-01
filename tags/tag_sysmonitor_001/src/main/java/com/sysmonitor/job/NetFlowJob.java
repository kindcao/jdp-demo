package com.sysmonitor.job;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.sysmonitor.common.Config;
import com.sysmonitor.common.Constants;
import com.sysmonitor.model.NdOid;
import com.sysmonitor.model.NfHost;
import com.sysmonitor.model.NfHostOidRef;
import com.sysmonitor.model.NfLog;
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
        logger.debug("host address list size : " + list.size());
        //               
        String subOID = "";
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            NfHost host = (NfHost) iterator.next();
            subOID = getSubOID(host);
            fetchData(host, subOID);
            writeDB(host, subOID);
            createChart(host, subOID);
        }
    }

    private void createChart(NfHost host, String subOID) {
        String mapKey = host.getHostAddress() + Constants.UNDERLINE + subOID;
        TimeSeriesCollection datasets = beanMap.get(mapKey).getTsc();
        // ======================================//
        String inKey = "In ";
        TimeSeries inSeries = datasets.getSeries(inKey);
        if (null == inSeries) {
            inSeries = new TimeSeries(inKey);
            // inSeries.setMaximumItemCount(50);
            datasets.addSeries(inSeries);
        }
        //
        String outKey = "Out ";
        TimeSeries outSeries = datasets.getSeries(outKey);
        if (null == outSeries) {
            outSeries = new TimeSeries(outKey);
            // outSeries.setMaximumItemCount(50);
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
        inRate = (entry.getIfInOctets() - entry.getLastIfInOctets() * 1.0) / period / 1000;
        outRate = (entry.getIfOutOctets() - entry.getLastIfOutOctets() * 1.0) / period / 1000;
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
        ChartInfo ci = new ChartInfo();
        ci.setTitle(getDeviceDesc(host));
        // ci.setTitle(entry.getIfDescr() + "-" + getDeviceDesc(host));
        ci.setYName(new String[] { "In/Out (" + Utils.getUnit(3) + "/s)" });
        //
        String chartPath = Config.getInstance().getValue("data.dir") + "/" + Constants.SUB_CHART_SAVE_PATH
                + host.getHostAddress() + "/" + subOID;
        ci.setSaveFilepath(chartPath);
        ChartUtils.writeChartAsPNG(ci, ChartUtils.createChart(ci, datasets));;
        //
        inSeries.setKey(inKey);
        outSeries.setKey(outKey);
    }

    private void fetchData(NfHost host, String subOID) {
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress(host.getProtocol() + ":" + host.getHostAddress() + "/" + host.getPort());
        sr.setCommunity(host.getCommunity());
        //
        sr.getVbs().clear();
        List<NdOid> oidList = getOidList(host);
        for (Iterator<NdOid> iterator = oidList.iterator(); iterator.hasNext();) {
            NdOid oid = iterator.next();
            sr.getVbs().add(new VariableBinding(new OID(oid.getOid())));
        }
        //
        try {
            String mapKey = host.getHostAddress() + Constants.UNDERLINE + subOID;
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

    private List<NdOid> getOidList(NfHost host) {
        List<NdOid> oidList = new ArrayList<NdOid>();
        Set<?> nfHostOidRefs = host.getNfHostOidRefs();
        logger.debug("nfHostOidRefs set size : " + nfHostOidRefs.size());
        for (Iterator<?> iterator = nfHostOidRefs.iterator(); iterator.hasNext();) {
            NfHostOidRef ele = (NfHostOidRef) iterator.next();
            oidList.add(ele.getNdOid());
        }
        return oidList;
    }

    private String getSubOID(NfHost host) {
        List<NdOid> oidList = getOidList(host);
        String subOID = "";
        for (Iterator<NdOid> iterator = oidList.iterator(); iterator.hasNext();) {
            NdOid oid = (NdOid) iterator.next();
            if (oid.getOid().startsWith(Constants.IFINDEX)) {
                subOID = oid.getOid().substring(oid.getOid().lastIndexOf(".") + 1);
                break;
            }
        }
        return subOID;
    }

    private String getDeviceDesc(NfHost host) {
        String desc = "";
        Set<?> nfHostOidRefs = host.getNfHostOidRefs();
        for (Iterator<?> iterator = nfHostOidRefs.iterator(); iterator.hasNext();) {
            NfHostOidRef ele = (NfHostOidRef) iterator.next();
            if (ele.getNdOid().getOid().startsWith(Constants.IFDESCR)) {
                desc = ele.getNdOid().getRemarks();
                break;
            }
        }
        return desc;
    }

    @Override
    protected void writeFile() {
        // ignore
    }

    private void writeDB(NfHost host, String subOID) {
        String mapKey = host.getHostAddress() + Constants.UNDERLINE + subOID;
        IfEntry entry = beanMap.get(mapKey).getEntry();
        //
        long inOctets = entry.getIfInOctets() - entry.getLastIfInOctets();
        long outOctets = entry.getIfOutOctets() - entry.getLastIfOutOctets();
        if (inOctets > host.getInOctets() || outOctets > host.getOutOctets()) {
            NfLog nl = new NfLog();
            nl.setInOctets(inOctets);
            nl.setOutOctets(outOctets);
            nl.setOccurrenceTime(Calendar.getInstance().getTime());
            //           

            // NfHostOidRef ref=new NfHostOidRef(hsot,,null);
            //            
            // nl.setNfHostOidRef(getHostRefOid(host, entry.getOid()));
            // netFlowService.saveOrUpdate(nl);
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
    class DataBean {

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
