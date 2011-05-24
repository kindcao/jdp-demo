package crm.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Kind Cao
 * @version $Rev$, May 23, 2011 3:56:57 PM
 */
public class MktEvtCountDto {

    private int induId;

    private String induName;

    private List<MktEvtCountItemDto> items = new ArrayList<MktEvtCountItemDto>();

    private MktEvtCountItemDto sum = new MktEvtCountItemDto();

    private int itemNum;

    public String getInduName() {
        return induName;
    }

    public void setInduName(String induName) {
        this.induName = induName;
    }

    public MktEvtCountItemDto getSum() {
        return sum;
    }

    public void setSum(MktEvtCountItemDto sum) {
        this.sum = sum;
    }

    public int getItemNum() {
        return itemNum;
    }

    public void setItemNum(int itemNum) {
        this.itemNum = itemNum;
    }

    public int getInduId() {
        return induId;
    }

    public void setInduId(int induId) {
        this.induId = induId;
    }

    public List<MktEvtCountItemDto> getItems() {
        return items;
    }

    public void setItems(List<MktEvtCountItemDto> items) {
        this.items = items;
    }
}
