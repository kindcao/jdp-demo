package com.base.action.json;

import java.util.Collection;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 22, 2011 3:28:32 PM
 */
public class JsonListResult implements JsonResult {

    private int total;

    private Collection<?> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection<?> getRows() {
        return rows;
    }

    public void setRows(Collection<?> rows) {
        this.rows = rows;
    }

}
