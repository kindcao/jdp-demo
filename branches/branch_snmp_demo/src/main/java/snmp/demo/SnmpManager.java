package snmp.demo;

import java.io.IOException;
import java.util.List;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.Target;
import org.snmp4j.TransportMapping;
import org.snmp4j.UserTarget;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.event.ResponseListener;
import org.snmp4j.mp.MPv3;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.security.AuthMD5;
import org.snmp4j.security.PrivDES;
import org.snmp4j.security.SecurityLevel;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.security.UsmUser;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.Variable;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.DefaultPDUFactory;
import org.snmp4j.util.PDUFactory;
import org.snmp4j.util.TableEvent;
import org.snmp4j.util.TableUtils;

/**
 * @author Kind Cao
 * @version $Rev$, Dec 13, 2011 1:55:48 PM
 */
public class SnmpManager {

    private Snmp snmp = null;

    private int version;

    public SnmpManager() {
        this(SnmpConstants.version1);
    }

    /**
     * @see SnmpConstants
     * @param version
     */
    public SnmpManager(int version) {
        try {
            this.version = version;
            TransportMapping<?> transport = new DefaultUdpTransportMapping();
            snmp = new Snmp(transport);
            if (SnmpConstants.version3 == version) {
                // ���ð�ȫģʽ
                USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
                SecurityModels.getInstance().addSecurityModel(usm);
            }
            // ��ʼ������Ϣ
            transport.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ����Ŀ���ַ����
     * 
     * @param addr
     *            Ŀ���ַ
     * @return Ŀ���ַ����
     */
    private Target getTarget(final String addr) {
        Address targetAddress = GenericAddress.parse(addr);
        Target target = null;
        if (SnmpConstants.version3 == version) {
            // ����û�
            snmp.getUSM().addUser(
                    new OctetString("MD5DES"),
                    new UsmUser(new OctetString("MD5DES"), AuthMD5.ID, new OctetString("MD5DESUserAuthPassword"),
                            PrivDES.ID, new OctetString("MD5DESUserPrivPassword")));

            target = new UserTarget();
            // ���ð�ȫ����
            ((UserTarget) target).setSecurityLevel(SecurityLevel.AUTH_PRIV);
            ((UserTarget) target).setSecurityName(new OctetString("MD5DES"));
            target.setVersion(SnmpConstants.version3);
        } else {
            target = new CommunityTarget();
            if (SnmpConstants.version1 == version) {
                target.setVersion(SnmpConstants.version1);
                ((CommunityTarget) target).setCommunity(new OctetString("public"));
            } else {
                target.setVersion(SnmpConstants.version2c);
                ((CommunityTarget) target).setCommunity(new OctetString("public"));
            }

        }
        // Ŀ������������
        target.setAddress(targetAddress);
        target.setRetries(5);
        target.setTimeout(1000);
        return target;
    }

    /**
     * ��д����
     * 
     * @param addr
     *            Ŀ���ַ
     * @param syn
     *            �Ƿ���ͬ��ģʽ
     * @param bro
     *            �Ƿ��ǹ㲥
     * @param pdu
     *            Ҫ���͵ı���
     * @throws IOException
     */

    private void sendMessage(final String addr, final boolean syn, final boolean bro, final PDU pdu) throws IOException {
        Target target = getTarget(addr);
        if (syn) {
            // ���ͱ��� ���ҽ�����Ӧ
            ResponseEvent response = snmp.send(pdu, target);
            // ������Ӧ
            System.out.println("Synchronize message from " + response.getPeerAddress() + "\nrequest:"
                    + response.getRequest() + "\nresponse:" + response.getResponse());
        } else {
            // ���ü�������
            ResponseListener listener = new ResponseListener() {

                @Override
                public void onResponse(ResponseEvent event) {
                    if (!bro) {
                        ((Snmp) event.getSource()).cancel(event.getRequest(), this);
                    }
                    // ������Ӧ
                    PDU request = event.getRequest();
                    PDU response = event.getResponse();
                    System.out.println("Asynchronise message from " + event.getPeerAddress() + "\nrequest:" + request
                            + "\nresponse:" + response);
                }
            };
            // ���ͱ���
            snmp.send(pdu, target, null, listener);
        }
    }

    public void getTab(final String addr, String oid) {
        PDUFactory pf = new DefaultPDUFactory(PDU.GET);
        TableUtils tu = new TableUtils(snmp, pf);
        OID[] columns = new OID[1];
        columns[0] = new VariableBinding(new OID(oid)).getOid();
        List<TableEvent> list = tu.getTable(getTarget(addr), columns, null, null);
        //
        if (null != list) {
            for (int i = 0; i < list.size(); i++) {
                TableEvent te = (TableEvent) list.get(i);
                VariableBinding[] vb = te.getColumns();
                if (null != vb) {
                    for (int j = 0; j < vb.length; j++) {
                        System.out.println(vb[j]);
                    }
                }
            }
        }
    }

    public void getPDU(final String addr, final boolean syn, final boolean bro, final String oid) throws IOException {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid)));// pcName
        pdu.setType(PDU.GETNEXT);
        sendMessage(addr, syn, bro, pdu);
    }

    public void setPDU(final String addr, final boolean syn, final boolean bro, final String oid, final Variable var)
            throws IOException {
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(oid), var));// pcName
        pdu.setType(PDU.SET);
        sendMessage(addr, syn, bro, pdu);
    }

    public static void main(String[] args) {
        final String HOSTS = "udp:127.0.0.1/161";
        SnmpManager manager = new SnmpManager();
        try {
            // ��ȡMIB����
            manager.getTab(HOSTS, ".");
            // ����get����
            manager.getPDU(HOSTS, false, true, "1.3.6.1.2.1.1.1.0");
            // ����set����
            // manager.setPDU(HOSTS, false, true, "1.3.6.1.2.1.1.6.0", new
            // OctetString("8812"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
