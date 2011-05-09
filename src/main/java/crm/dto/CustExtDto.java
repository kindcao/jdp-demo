package crm.dto;

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

}
