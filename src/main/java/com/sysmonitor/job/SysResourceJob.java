package com.sysmonitor.job;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import com.sysmonitor.common.Constants;
import com.sysmonitor.common.Constants.Storeage;
import com.sysmonitor.model.SrHost;
import com.sysmonitor.model.SrLog;
import com.sysmonitor.model.SrResource;
import com.sysmonitor.service.SysResourceService;
import com.sysmonitor.snmp.SnmpRequest;
import com.sysmonitor.snmp.SysResourceEntry;
import com.sysmonitor.snmp.SysResourceTextListener;

/**
 * @author Kind Cao
 * @version $Rev: $, Feb 3, 2012 3:15:36 PM
 */
public class SysResourceJob extends AbstractJob {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private SysResourceService sysResourceService;

    @Override
    protected void job() {
        SrHost sr = new SrHost();
        sr.setStatus("1");
        List<?> list = sysResourceService.findByExample(sr);
        logger.debug("host address list size : " + list.size());

        //
        SysResourceEntry sre = null;
        List<SrLog> results = new ArrayList<SrLog>();
        SrLog sl = null;
        //
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            SrHost srHost = (SrHost) iterator.next();
            for (Iterator<?> iterator2 = srHost.getSrResources().iterator(); iterator2.hasNext();) {
                SrResource srResource = (SrResource) iterator2.next();
                //
                sre = new SysResourceEntry();
                sre.setDesci(srResource.getRemarks());
                fetchData(srResource, sre);
                //
                // if (sre.getFreeCapacity() > 0 && sre.getFreeCapacity() <
                // srHost.getFreeDisk()) {
                // sl = new SrLog();
                // sl.setFreeDisk(sre.getFreeCapacity());
                // sl.setOccurrenceTime(Calendar.getInstance().getTime());
                // sl.setSrResource(srResource);
                // }
                // if (sre.getFreeCapacity() > 0 && sre.getFreeCapacity() <
                // srHost.getFreeRam()) {
                // sl = new SrLog();
                // sl.setFreeRam(sre.getFreeCapacity());
                // sl.setOccurrenceTime(Calendar.getInstance().getTime());
                // sl.setSrResource(srResource);
                // }
                // if (sre.getUseCpuRate() > srHost.getUseCpuRate()) {
                // sl = new SrLog();
                // sl.setUseCpuRate(sre.getUseCpuRate());
                // sl.setOccurrenceTime(Calendar.getInstance().getTime());
                // sl.setSrResource(srResource);
                // }
            }
        }
    }

    private void fetchData(SrResource srResource, SysResourceEntry sre) {
        SrHost srHost = srResource.getSrHost();
        SnmpRequest sr = new SnmpRequest();
        sr.setAddress(srHost.getProtocol() + ":" + srHost.getHostAddress() + "/" + srHost.getPort());
        sr.setCommunity(srHost.getCommunity());
        //
        Storeage storeage = null;
        if (Constants.OS_TYPE_WINDOWS.equalsIgnoreCase(srHost.getOsType())) {
            storeage = Storeage.Windows;
        } else {
            throw new RuntimeException("Nonsupport os type " + srHost.getOsType());
        }
        //
        sr.getVbs().clear();
        sr.getVbs().add(
                new VariableBinding(new OID(storeage.getHrStorageDescr()).append(srResource.getResourceIndex())));
        sr.getVbs().add(
                new VariableBinding(new OID(storeage.getHrStorageAllocationUnits()).append(srResource
                        .getResourceIndex())));
        sr.getVbs()
                .add(new VariableBinding(new OID(storeage.getHrStorageSize()).append(srResource.getResourceIndex())));
        sr.getVbs()
                .add(new VariableBinding(new OID(storeage.getHrStorageUsed()).append(srResource.getResourceIndex())));
        //
        try {
            sr.send(new SysResourceTextListener(storeage, sre));
            logger.info(sre.toString());
        } catch (IOException e) {
            logger.error("fetchData", e);
        } finally {
            sr.getVbs().clear();
        }
    }

    @Resource
    public void setSysResourceService(SysResourceService sysResourceService) {
        this.sysResourceService = sysResourceService;
    }

}
