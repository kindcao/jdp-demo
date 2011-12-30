package com.netmonitor.snmp;

import java.io.IOException;
import java.util.Vector;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.PDUv1;
import org.snmp4j.ScopedPDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Counter32;
import org.snmp4j.smi.IpAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.AbstractTransportMapping;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableListener;
import org.snmp4j.util.TableUtils;

import com.netmonitor.common.Constants;

/**
 * @author Kind Cao
 * @version $Rev$, Dec 15, 2011 10:04:27 AM
 */
public class SnmpRequest implements PDUFactory {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private OID authProtocol;

    private OID privProtocol;

    private OctetString privPassphrase;

    private OctetString authPassphrase;

    private OctetString community = new OctetString("public");

    private OctetString contextEngineID;

    private OctetString contextName = new OctetString();

    private OctetString securityName = new OctetString();

    private OctetString localEngineID = new OctetString(MPv3.createLocalEngineID());

    private int engineBootCount = 0;

    private int version = SnmpConstants.version1;

    private Target target;

    private IpAddress address;

    private PDUv1 v1TrapPDU = new PDUv1();

    private int retries = 1;

    private int timeout = 1;

    private int pduType = PDU.GETNEXT;

    private int maxRepetitions = 10;

    private int nonRepeaters = 0;

    private int maxSizeResponsePDU = 65535;

    private Vector<VariableBinding> vbs = new Vector<VariableBinding>();

    // table options
    private OID lowerBoundIndex;

    private OID upperBoundIndex;

    public PDU send() throws IOException {
        Snmp snmp = createSnmpSession();
        this.target = createTarget();
        target.setVersion(version);
        target.setAddress(address);
        target.setRetries(retries);
        target.setTimeout(timeout);
        target.setMaxSizeRequestPDU(maxSizeResponsePDU);
        snmp.listen();

        PDU request = createPDU(target);
        if (request.getType() == PDU.GETBULK) {
            request.setMaxRepetitions(maxRepetitions);
            request.setNonRepeaters(nonRepeaters);
        }
        for (int i = 0; i < vbs.size(); i++) {
            request.add((VariableBinding) vbs.get(i));
        }

        PDU response = null;
        ResponseEvent responseEvent;
        long startTime = System.currentTimeMillis();
        responseEvent = snmp.send(request, target);
        if (responseEvent != null) {
            response = responseEvent.getResponse();
            logger.info("Received response after " + (System.currentTimeMillis() - startTime) + " millis");
        }
        snmp.close();
        return response;
    }

    public PDU createPDU(Target target) {
        PDU request;
        if (target.getVersion() == SnmpConstants.version3) {
            request = new ScopedPDU();
            ScopedPDU scopedPDU = (ScopedPDU) request;
            if (contextEngineID != null) {
                scopedPDU.setContextEngineID(contextEngineID);
            }
            if (contextName != null) {
                scopedPDU.setContextName(contextName);
            }
        } else {
            if (pduType == PDU.V1TRAP) {
                request = v1TrapPDU;
            } else {
                request = new PDU();
            }
        }
        request.setType(pduType);
        return request;
    }

    private Target createTarget() {
        if (version == SnmpConstants.version3) {
            UserTarget target = new UserTarget();
            if (authPassphrase != null) {
                if (privPassphrase != null) {
                    target.setSecurityLevel(SecurityLevel.AUTH_PRIV);
                } else {
                    target.setSecurityLevel(SecurityLevel.AUTH_NOPRIV);
                }
            } else {
                target.setSecurityLevel(SecurityLevel.NOAUTH_NOPRIV);
            }
            target.setSecurityName(securityName);
            return target;
        } else {
            CommunityTarget target = new CommunityTarget();
            target.setCommunity(community);
            return target;
        }
    }

    private Snmp createSnmpSession() throws IOException {
        AbstractTransportMapping<?> transport;
        if (address instanceof TcpAddress) {
            transport = new DefaultTcpTransportMapping();
        } else {
            transport = new DefaultUdpTransportMapping();
        }
        // Could save some CPU cycles:
        // transport.setAsyncMsgProcessingSupported(false);
        Snmp snmp = new Snmp(transport);
        ((MPv3) snmp.getMessageProcessingModel(MPv3.ID)).setLocalEngineID(localEngineID.getValue());

        if (version == SnmpConstants.version3) {
            USM usm = new USM(SecurityProtocols.getInstance(), localEngineID, engineBootCount);
            SecurityModels.getInstance().addSecurityModel(usm);
            addUsmUser(snmp);
        }
        return snmp;
    }

    private void addUsmUser(Snmp snmp) {
        snmp.getUSM().addUser(securityName,
                new UsmUser(securityName, authProtocol, authPassphrase, privProtocol, privPassphrase));
    }

    private static IpAddress getAddress(String transportAddress) {
        String transport = "udp";
        int colon = transportAddress.indexOf(':');
        if (colon > 0) {
            transport = transportAddress.substring(0, colon);
            transportAddress = transportAddress.substring(colon + 1);
        }
        // set default port
        if (transportAddress.indexOf('/') < 0) {
            transportAddress += "/161";
        }
        if (transport.equalsIgnoreCase("udp")) {
            return new UdpAddress(transportAddress);
        } else if (transport.equalsIgnoreCase("tcp")) {
            return new TcpAddress(transportAddress);
        }
        throw new IllegalArgumentException("Unknown transport " + transport);
    }

    public void table(TableListener listener) throws IOException {
        Snmp snmp = createSnmpSession();
        this.target = createTarget();
        target.setVersion(version);
        target.setAddress(address);
        target.setRetries(retries);
        target.setTimeout(timeout);
        snmp.listen();

        TableUtils tableUtils = new TableUtils(snmp, this);
        tableUtils.setMaxNumRowsPerPDU(maxRepetitions);
        Counter32 counter = new Counter32();

        OID[] columns = new OID[vbs.size()];
        for (int i = 0; i < columns.length; i++) {
            columns[i] = ((VariableBinding) vbs.get(i)).getOid();
        }
        long startTime = System.currentTimeMillis();
        synchronized (counter) {
            tableUtils.getTable(target, columns, listener, counter, lowerBoundIndex, upperBoundIndex);
            try {
                counter.wait(timeout);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            logger.info("Table received in " + (System.currentTimeMillis() - startTime) + " milliseconds.");
            snmp.close();
        }
    }

    public void fetchData(ConcurrentMap<String, IfEntry> IfEntry) {
        int ifNumber = getIfNumber();
        logger.info("ifNumber = " + ifNumber);
        //
        if (ifNumber > 0) {
            getVbs().clear();
            getVbs().add(new VariableBinding(new OID(Constants.IFINDEX)));
            getVbs().add(new VariableBinding(new OID(Constants.IFDESCR)));
            getVbs().add(new VariableBinding(new OID(Constants.IFTYPE)));
            getVbs().add(new VariableBinding(new OID(Constants.IFINOCTETS)));
            getVbs().add(new VariableBinding(new OID(Constants.IFOUTOCTETS)));
            // setUpperBoundIndex(new OID(String.valueOf(ifNumber)));
            try {
                table(new TextTableListener(IfEntry));
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                getVbs().clear();
            }
        }
    }

    public int getIfNumber() {
        OID oid = new OID(Constants.IFNUMBER);
        getVbs().clear();
        getVbs().add(new VariableBinding(oid));
        PDU response;
        try {
            response = send();
            return response.getVariable(oid).toInt();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Vector<VariableBinding> getVbs() {
        return vbs;
    }

    public void setVbs(Vector<VariableBinding> vbs) {
        this.vbs = vbs;
    }

    public OID getLowerBoundIndex() {
        return lowerBoundIndex;
    }

    public void setLowerBoundIndex(OID lowerBoundIndex) {
        this.lowerBoundIndex = lowerBoundIndex;
    }

    public OID getUpperBoundIndex() {
        return upperBoundIndex;
    }

    public void setUpperBoundIndex(OID upperBoundIndex) {
        this.upperBoundIndex = upperBoundIndex;
    }

    public OctetString getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        if (StringUtils.isNotBlank(community)) {
            this.community = new OctetString(community);
        }
    }

    public String getAddress() {
        if (null != address) {
            return address.getInetAddress().getHostAddress();
        }
        return null;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        if (StringUtils.isNotBlank(timeout)) {
            this.timeout = Integer.valueOf(timeout);
        }
        this.timeout *= 1000;
    }

    public int getRetries() {
        return retries;
    }

    public void setRetries(String retries) {
        if (StringUtils.isNotBlank(retries)) {
            this.retries = Integer.valueOf(retries);
        }
        this.retries *= 1000;
    }

    public void setAddress(String address) {
        this.address = getAddress(address);
    }

    class TextTableListener implements TableListener {

        private boolean finished;

        //
        private ConcurrentMap<String, IfEntry> dataMap;

        public TextTableListener(ConcurrentMap<String, IfEntry> dataMap) {
            if (null == dataMap) {
                throw new IllegalArgumentException("dataMap is null");
            }
            this.dataMap = dataMap;
        }

        public void finished(TableEvent event) {
            // System.out.println("Table walk completed with status " +
            // event.getStatus() + ". Received "
            // + event.getUserObject() + " rows.");
            finished = true;
            synchronized (event.getUserObject()) {
                event.getUserObject().notify();
            }
        }

        public boolean next(TableEvent event) {
            // System.out.println("Index = " + event.getIndex() + ":");
            for (int i = 0; i < event.getColumns().length; i++) {
                String key = event.getColumns()[i].getOid().toString();
                String value = event.getColumns()[i].toValueString();
                // System.out.println(key + " = " + value);
                if (!dataMap.containsKey(key)) {
                    dataMap.put(key, new IfEntry());
                }

                IfEntry entry = dataMap.get(key);
                entry.setOid(key);
                if (key.startsWith(Constants.IFINDEX)) {
                    entry.setIfIndex(value);
                }
                if (key.startsWith(Constants.IFDESCR) && null == entry.getIfDescr()) {
                    entry.setIfDescr(new String(OctetString.fromHexString(value).getValue()).trim());
                }
                if (key.startsWith(Constants.IFTYPE) && null == entry.getIfType()) {
                    entry.setIfType(value);
                }
                if (key.startsWith(Constants.IFINOCTETS)) {
                    entry.setIfInOctets(Long.valueOf(value));
                }
                if (key.startsWith(Constants.IFOUTOCTETS)) {
                    entry.setIfOutOctets(Long.valueOf(value));
                }
                //
                dataMap.put(key, entry);
            }
            ((Counter32) event.getUserObject()).increment();
            return true;
        }

        public boolean isFinished() {
            return finished;
        }
    }

}
