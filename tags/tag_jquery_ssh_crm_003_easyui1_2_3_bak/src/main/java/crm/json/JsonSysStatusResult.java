package crm.json;

/**
 * @author Kind Cao
 * @version $Rev$, May 13, 2011 11:05:52 AM
 */
public class JsonSysStatusResult implements JsonResult {

    private int statusCode;

    private int onlineUserNum;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getOnlineUserNum() {
        return onlineUserNum;
    }

    public void setOnlineUserNum(int onlineUserNum) {
        this.onlineUserNum = onlineUserNum;
    }

}
