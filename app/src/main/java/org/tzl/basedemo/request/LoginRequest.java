package org.tzl.basedemo.request;

import org.tzl.baselibrary.base.BaseRequest;

/**
 * author: tangzenglei
 * created on: 2017/4/18 下午3:57
 * description:
 */
public class LoginRequest extends BaseRequest {


    private String phone;

    private String password;

    private String login_type;

    public LoginRequest(String phone, String password, String login_type) {
        this.phone = phone;
        this.password = password;
        this.login_type = login_type;
    }

    public String getLogin_type() {
        return login_type;
    }

    public void setLogin_type(String login_type) {
        this.login_type = login_type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}