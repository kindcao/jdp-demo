package crm.dto;

import java.util.List;

import crm.model.Customer;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 8, 2011 9:53:25 PM
 */
public class CustExtDto extends Customer {

    public String industryName;

    private String custSysCompNames;

    private String custSysCompIds;

    private List<Integer> custSysCompIdsList;

    public String getIndustryName() {
        return industryName;
    }

    public void setIndustryName(String industryName) {
        this.industryName = industryName;
    }

    public String getCustSysCompNames() {
        return custSysCompNames;
    }

    public void setCustSysCompNames(String custSysCompNames) {
        this.custSysCompNames = custSysCompNames;
    }

    public String getCustSysCompIds() {
        return custSysCompIds;
    }

    public void setCustSysCompIds(String custSysCompIds) {
        this.custSysCompIds = custSysCompIds;
    }

    public List<Integer> getCustSysCompIdsList() {
        return custSysCompIdsList;
    }

    public void setCustSysCompIdsList(List<Integer> custSysCompIdsList) {
        this.custSysCompIdsList = custSysCompIdsList;
    }

}
