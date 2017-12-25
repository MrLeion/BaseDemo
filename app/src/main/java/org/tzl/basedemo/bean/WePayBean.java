package org.tzl.basedemo.bean;

import java.io.Serializable;

/**
 * Created by hp on 2017/8/2.
 */

public class WePayBean implements Serializable {
    public String partnerid;
    public String perpayid;
//    public String package;
    public String noncestr;
    public String timestamp;
    public String sign;
    public String order_id;
}
