package org.tzl.baselibrary.base;

/**
 * author: tangzenglei
 * created on: 2017/4/16 下午2:42
 * description:
 */
public class BaseResponseT<T> {
    /**
     * code : C_000
     * msg : 成功
     * success : true
     */

    private String  code;
    private String  msg;
    private boolean success;
    private T data;

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}