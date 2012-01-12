package com.base.json;

/**
 * 
 * @author Kind Cao
 * @version 1.0 <br>
 *          Apr 22, 2011 3:28:32 PM
 */
public class JsonValidateResult implements JsonResult {

    private boolean success;

    private String errors = "";

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

}
