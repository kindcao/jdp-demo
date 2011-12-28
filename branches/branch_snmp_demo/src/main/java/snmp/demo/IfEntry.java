package snmp.demo;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Dec 27, 2011 10:53:43 AM
 */
public class IfEntry {

    private String oid;

    private String ifIndex;

    private String ifDescr;

    private String ifType;

    private long ifInOctets;

    private long ifOutOctets;

    // for chart
    private long lastIfInOctets = -1;

    private long lastIfOutOctets = -1;

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
        if (lastIfInOctets == -1) {
            this.lastIfInOctets = ifInOctets;
        }
        this.ifInOctets = ifInOctets;
    }

    public long getIfOutOctets() {
        return ifOutOctets;
    }

    public void setIfOutOctets(long ifOutOctets) {
        if (lastIfOutOctets == -1) {
            this.lastIfOutOctets = ifOutOctets;
        }
        this.ifOutOctets = ifOutOctets;
    }

    public String getIfIndex() {
        return ifIndex;
    }

    public void setIfIndex(String ifIndex) {
        this.ifIndex = ifIndex;
    }

    public long getLastIfInOctets() {
        return lastIfInOctets;
    }

    public void setLastIfInOctets(long lastIfInOctets) {
        this.lastIfInOctets = lastIfInOctets;
    }

    public long getLastIfOutOctets() {
        return lastIfOutOctets;
    }

    public void setLastIfOutOctets(long lastIfOutOctets) {
        this.lastIfOutOctets = lastIfOutOctets;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(getClass().getSimpleName() + ":");
        sb.append("\toid = " + oid);
        sb.append("\tifIndex = " + ifIndex);
        sb.append("\tifDescr = " + ifDescr);
        sb.append("\tifType = " + ifType);
        sb.append("\tifInOctets = " + ifInOctets);
        sb.append("\tifOutOctets = " + ifOutOctets);
        sb.append("\tlastIfInOctets = " + lastIfInOctets);
        sb.append("\tlastIfOutOctets = " + lastIfOutOctets);
        return sb.toString();
    }

}
