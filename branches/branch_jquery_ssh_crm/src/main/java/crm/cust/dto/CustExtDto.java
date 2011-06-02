package crm.cust.dto;

import java.util.Set;

import crm.model.Customer;
import crm.model.CustomerSysCompanyRel;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 8, 2011 9:53:25 PM
 */
@SuppressWarnings("serial")
public class CustExtDto extends Customer {

    public String industryName;

    private String custSysCompNames;

    private String custSysCompIds;

    private Set<CustomerSysCompanyRel> custSysCompRels;

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

    public Set<CustomerSysCompanyRel> getCustSysCompRels() {
        return custSysCompRels;
    }

    public void setCustSysCompRels(Set<CustomerSysCompanyRel> custSysCompRels) {
        this.custSysCompRels = custSysCompRels;
    }

}
