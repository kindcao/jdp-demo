package snmp.demo;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 10:53:43 AM
 */
public class IfEntry {

    private String oid;

    private String ifDescr;

    private String ifType;

    private long ifInOctets;

    private long ifOutOctets;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getIfDescr() {
        return ifDescr;
    }

    public void setIfDescr(String ifDescr) {
        this.ifDescr = ifDescr;
    }

    public String getIfType() {
        return ifType;
    }

    public void setIfType(String ifType) {
        this.ifType = ifType;
    }

    public long getIfInOctets() {
        return ifInOctets;
    }

    public void setIfInOctets(long ifInOctets) {
        this.ifInOctets = ifInOctets;
    }

    public long getIfOutOctets() {
        return ifOutOctets;
    }

    public void setIfOutOctets(long ifOutOctets) {
        this.ifOutOctets = ifOutOctets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.getClass().getSimpleName()).append(":");
        sb.append("\toid=" + oid);
        sb.append("\tifDescr=" + ifDescr);
        sb.append("\tifType=" + ifType);
        sb.append("\tifInOctets=" + ifInOctets);
        sb.append("\tifOutOctets=" + ifOutOctets);
        return sb.toString();
    }

}
