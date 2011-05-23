package crm.dto;

/**
 * @author Kind Cao
 * @version $Rev$, May 23, 2011 4:05:42 PM
 */
public class MktEvtCountItemDto {

    private String custName;

    private int visitNum;

    private int trainingNum;

    private int activityNum;

    private int othersNum;

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public int getVisitNum() {
        return visitNum;
    }

    public void setVisitNum(int visitNum) {
        this.visitNum = visitNum;
    }

    public int getTrainingNum() {
        return trainingNum;
    }

    public void setTrainingNum(int trainingNum) {
        this.trainingNum = trainingNum;
    }

    public int getActivityNum() {
        return activityNum;
    }

    public void setActivityNum(int activityNum) {
        this.activityNum = activityNum;
    }

    public int getOthersNum() {
        return othersNum;
    }

    public void setOthersNum(int othersNum) {
        this.othersNum = othersNum;
    }

}
