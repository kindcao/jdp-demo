package snmp.demo;

import java.io.IOException;
import java.util.Vector;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 12, 2011 10:02:13 PM
 */
public class SnmpUtil {

    private Snmp snmp = null;

    private Address targetAddress = null;

    public void initComm() throws IOException {
        // ����Agent����IP�Ͷ˿�
        // targetAddress = GenericAddress.parse("udp:192.168.10.191/161");
        targetAddress = GenericAddress.parse("udp:127.0.0.1/161");

        TransportMapping transport = new DefaultUdpTransportMapping();
        snmp = new Snmp(transport);
        transport.listen();
    }

    public ResponseEvent sendPDU(PDU pdu) throws IOException {
        // ���� target
        CommunityTarget target = new CommunityTarget();
        target.setCommunity(new OctetString("public"));
        target.setAddress(targetAddress);
        // ͨ�Ų��ɹ�ʱ�����Դ���
        target.setRetries(2);
        // ��ʱʱ��
        target.setTimeout(1500);
        target.setVersion(SnmpConstants.version1);
        // ��Agent����PDU��������Response
        return snmp.send(pdu, target);
    }

    public void setPDU() throws IOException {
        // set PDU
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(new int[] { 1, 3, 6, 1, 2, 1, 1, 7, 0 }), new OctetString("8899")));
        pdu.setType(PDU.SET);
        sendPDU(pdu);
    }

    public void getPDU() throws IOException {
        // get PDU
        PDU pdu = new PDU();
        pdu.add(new VariableBinding(new OID(new int[] { 1, 3, 6, 1, 2, 1, 1, 6, 0 })));// pcName
        pdu.setType(PDU.GET);
        readResponse(sendPDU(pdu));
    }

    public void readResponse(ResponseEvent respEvnt) {
        // ����Response
        System.out.println("----------����Response-------------");
        if (respEvnt != null && respEvnt.getResponse() != null) {
            Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getResponse().getVariableBindings();
            for (int i = 0; i < recVBs.size(); i++) {
                VariableBinding recVB = recVBs.elementAt(i);
                System.out.println(recVB.getOid() + " : " + recVB.getVariable());
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("----------start-------------");
        try {
            SnmpUtil util = new SnmpUtil();
            util.initComm();
            util.setPDU();
            util.getPDU();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}