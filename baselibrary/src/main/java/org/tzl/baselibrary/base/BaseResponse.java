package org.tzl.baselibrary.base;

import java.io.Serializable;

public class BaseResponse implements Serializable {


    /**
     * code : C_000
     * msg : 成功
     * success : true
     */

    private String  code;
    private String  msg;
    private boolean success;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
