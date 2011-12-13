package snmp.demo;

import java.net.InetAddress;

import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageException;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.StateReference;
import org.snmp4j.mp.StatusInformation;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 12, 2011 10:15:12 PM
 */
public class OTAAgent {

    public static class Handler implements CommandResponder {

        protected java.lang.String mAddress = null;

        protected int mPort = 0;

        protected java.lang.String mMyCommunityName = null;

        protected Snmp mSNMP = null;

        public Handler() {
        }

        public void configure() {
            // mAddress = "192.168.10.191";
            mAddress = "127.0.0.1";
            mPort = 161;
            mMyCommunityName = "OAagent";
        }

        public void start() {
            try {
                TransportMapping<?> mServerSocket = new DefaultUdpTransportMapping(new UdpAddress(InetAddress
                        .getByName(mAddress), mPort));
                mSNMP = new Snmp(mServerSocket);
                mSNMP.addCommandResponder(this);
                mServerSocket.listen();
            } catch (java.net.UnknownHostException vException) {
                System.out.println(vException);
            } catch (java.io.IOException vException) {
                System.out.println(vException);
            }
        }

        public synchronized void processPdu(CommandResponderEvent aEvent) {
            java.lang.String vCommunityName = new java.lang.String(aEvent.getSecurityName());
            System.out.println("Community name " + vCommunityName);
            PDU vPDU = aEvent.getPDU();
            if (vPDU == null) {
                System.out.println("Null pdu");
            } else {
                System.out.println("(rcv) " + vPDU.toString());
                switch (vPDU.getType()) {
                case PDU.GET:
                case PDU.GETNEXT:
                    break;
                case PDU.SET:
                    System.out.println("------SET----------");
                    // String reciv =
                    // vPDU.get(0).getVariable().getSyntaxString();
                    System.out.println("----set------" + vPDU.get(0).toString());
                    String setoid = vPDU.get(0).toString();
                    System.out.println("-----set-----" + setoid.substring(0, setoid.indexOf("=") - 1));
                    System.out.println("-----set-----" + setoid.substring(setoid.indexOf("=") + 1));
                    Config.getInstance().setValueByOID(setoid.substring(0, setoid.indexOf("=") - 1).trim(),
                            setoid.substring(setoid.indexOf("=") + 1).trim());
                }
                StatusInformation statusInformation = new StatusInformation();
                StateReference ref = aEvent.getStateReference();
                try {
                    System.out.println("Sending response");
                    vPDU.setType(PDU.RESPONSE);

                    OID oid = vPDU.get(0).getOid();
                    String setoid = vPDU.get(0).toString();
                    System.out.println("----get------" + setoid.substring(0, setoid.indexOf("=") - 1));
                    System.out.println("-----get-----" + setoid.substring(setoid.indexOf("=") + 1));
                    vPDU.set(0, new VariableBinding(oid, new OctetString(Config.getInstance().getValueByOID(
                            setoid.substring(0, setoid.indexOf("=") - 1).trim()))));

                    aEvent.getMessageDispatcher().returnResponsePdu(aEvent.getMessageProcessingModel(),
                            aEvent.getSecurityModel(), aEvent.getSecurityName(), aEvent.getSecurityLevel(), vPDU,
                            aEvent.getMaxSizeResponsePDU(), ref, statusInformation);
                } catch (MessageException vException) {
                    System.out.println(vException);
                }
            }
        }
    }

    public static void main(String argv[]) {
        Handler h = new Handler();
        /** 初始化参数 * */
        h.configure();
        h.start();
        /** Do nothing loop * */
        while (true) {
            System.out.println("----------loop-------------");
            synchronized (OTAAgent.class) {
                try {
                    OTAAgent.class.wait();
                } catch (Exception e) {
                }
            }
        }
    }
}
