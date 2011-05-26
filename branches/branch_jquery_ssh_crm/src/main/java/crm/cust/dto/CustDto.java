package crm.cust.dto;

import java.util.Set;

import crm.model.Customer;
import crm.model.CustomerSysCompanyRel;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          May 1, 2011 2:20:28 PM
 */
public class CustDto {

    private Customer custObj;

    private Set<CustomerSysCompanyRel> custSysCompRels;

    // private Set<CustomerSysUserRel> custSysUserRels;

    public Customer getCustObj() {
        return custObj;
    }

    public void setCustObj(Customer custObj) {
        this.custObj = custObj;
    }

    public Set<CustomerSysCompanyRel> getCustSysCompRels() {
        return custSysCompRels;
    }

    public void setCustSysCompRels(Set<CustomerSysCompanyRel> custSysCompRels) {
        this.custSysCompRels = custSysCompRels;
    }

    // public Set<CustomerSysUserRel> getCustSysUserRels() {
    // return custSysUserRels;
    // }
    //
    // public void setCustSysUserRels(Set<CustomerSysUserRel> custSysUserRels) {
    // this.custSysUserRels = custSysUserRels;
    // }

}
