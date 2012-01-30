package com.sysmonitor.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.sysmonitor.common.Config;
import com.sysmonitor.common.Constants;
import com.sysmonitor.model.NfHost;
import com.sysmonitor.model.NfHostOidRef;
import com.sysmonitor.model.NfLog;
import com.sysmonitor.service.NetFlowService;
import com.sysmonitor.snmp.IfEntry;
import com.sysmonitor.snmp.NetFlowTableListener;
import com.sysmonitor.snmp.SnmpRequest;
import com.sysmonitor.util.ChartInfo;
import com.sysmonitor.util.ChartUtils;

/**
 * @author Kind Cao
 * @version $Rev: $, Jan 30, 2012 2:23:05 PM
 */
public class NetFlowJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private NetFlowService netFlowService;

    private Map<String, DataBean> dataMap = new ConcurrentHashMap<String, DataBean>();

    @Override
    protected void job() {
        NfHost nh = new NfHost();
        nh.setStatus("1");
        List<?> list = netFlowService.findByExample(nh);
        logger.info("host address list size : " + list.size());

        //
        List<NfHost> results = new ArrayList<NfHost>();
        NfLog nl = null;
        SnmpRequest sr;
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            NfHost ele = (NfHost) iterator.next();
            createChart(ele);
        }
    }

    private void createChart(NfHost host) {
        fetchData(host);
        //
        ChartInfo ci = new ChartInfo();
        String subOID = getSubOID(host);
        Map<String, TimeSeriesCollection> tscMap = dataMap.get(host.getHostAddress()).getTscMap();
        TimeSeriesCollection datasets = tscMap.get(subOID);
        if (null == datasets) {
            datasets = new TimeSeriesCollection();
            tscMap.put(subOID, datasets);
            //
            TimeSeries inSeries = new TimeSeries("In");
            // inSeries.setMaximumItemCount(30);
            datasets.addSeries(inSeries);
            //
            TimeSeries outSeries = new TimeSeries("Out");
            // outSeries.setMaximumItemCount(30);
            datasets.addSeries(outSeries);
        }
        //

        //
        String chartPath = Config.getInstance().getValue("data.dir") + "/" + Constants.SUB_CHART_SAVE_PATH
                + host.getHostAddress() + "/" + subOID;
        ci.setSaveFilepath(chartPath);
        ChartUtils.writeChartAsPNG(ci, ChartUtils.createChart(ci, datasets));;
    }

    private void fetchData(NfHost host) {
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress(host.getProtocol() + ":" + host.getHostAddress() + "/" + host.getPort());
        sr.setCommunity(host.getCommunity());
        //
        sr.getVbs().clear();
        List<String> oidList = getOidList(host);
        for (Iterator<String> iterator = oidList.iterator(); iterator.hasNext();) {
            String oid = iterator.next();
            sr.getVbs().add(new VariableBinding(new OID(oid)));
        }
        //
        try {
            if (!dataMap.containsKey(host.getHostAddress())) {
                dataMap.put(host.getHostAddress(), new DataBean());
            }
            sr.table(new NetFlowTableListener(dataMap.get(host.getHostAddress()).getDataMap()));
        } catch (IOException e) {
            logger.error("fetchData", e);
        } finally {
            sr.getVbs().clear();
        }
    }

    private List<String> getOidList(NfHost host) {
        List<String> oidList = new ArrayList<String>();
        Set<?> nfHostOidRefs = host.getNfHostOidRefs();
        logger.info("nfHostOidRefs set size : " + nfHostOidRefs.size());
        for (Iterator<?> iterator = nfHostOidRefs.iterator(); iterator.hasNext();) {
            NfHostOidRef ele = (NfHostOidRef) iterator.next();
            oidList.add(ele.getNdOid().getOid());
        }
        return oidList;
    }

    private String getSubOID(NfHost host) {
        List<String> oidList = getOidList(host);
        String subOID = "";
        for (Iterator<String> iterator = oidList.iterator(); iterator.hasNext();) {
            String oid = (String) iterator.next();
            if (oid.startsWith(Constants.IFINOCTETS) || oid.startsWith(Constants.IFOUTOCTETS)) {
                subOID = oid.substring(oid.lastIndexOf(".") + 1);
                break;
            }
        }
        return subOID;
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

        private Map<String, TimeSeriesCollection> tscMap = new ConcurrentHashMap<String, TimeSeriesCollection>();

        private Map<String, IfEntry> dataMap = new ConcurrentHashMap<String, IfEntry>();

        public Map<String, TimeSeriesCollection> getTscMap() {
            return tscMap;
        }

        public Map<String, IfEntry> getDataMap() {
            return dataMap;
        }

    }

}
