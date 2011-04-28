package crm.model;

/**
 * MarketEvent entity.
 * 
 * @author MyEclipse Persistence Tools
 */

public class MarketEvent implements java.io.Serializable {

    // Fields

    private Integer id;

    private Integer occurDate;

    private Integer beginTime;

    private Integer endTime;

    private Integer marketEventTypeId;

    private String situation;

    private String goods;

    private String remark;

    private String trainScale;

    private String subject;

    // Constructors

    /** default constructor */
    public MarketEvent() {
    }

    /** minimal constructor */
    public MarketEvent(Integer occurDate, Integer beginTime, Integer endTime, Integer marketEventTypeId,
            String situation) {
        this.occurDate = occurDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.marketEventTypeId = marketEventTypeId;
        this.situation = situation;
    }

    /** full constructor */
    public MarketEvent(Integer occurDate, Integer beginTime, Integer endTime, Integer marketEventTypeId,
            String situation, String goods, String remark, String trainScale, String subject) {
        this.occurDate = occurDate;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.marketEventTypeId = marketEventTypeId;
        this.situation = situation;
        this.goods = goods;
        this.remark = remark;
        this.trainScale = trainScale;
        this.subject = subject;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(Integer occurDate) {
        this.occurDate = occurDate;
    }

    public Integer getBeginTime() {
        return this.beginTime;
    }

    public void setBeginTime(Integer beginTime) {
        this.beginTime = beginTime;
    }

    public Integer getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Integer endTime) {
        this.endTime = endTime;
    }

    public Integer getMarketEventTypeId() {
        return this.marketEventTypeId;
    }

    public void setMarketEventTypeId(Integer marketEventTypeId) {
        this.marketEventTypeId = marketEventTypeId;
    }

    public String getSituation() {
        return this.situation;
    }

    public void setSituation(String situation) {
        this.situation = situation;
    }

    public String getGoods() {
        return this.goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTrainScale() {
        return this.trainScale;
    }

    public void setTrainScale(String trainScale) {
        this.trainScale = trainScale;
    }

    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

}