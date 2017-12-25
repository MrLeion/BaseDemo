package org.tzl.basedemo.request;


import org.tzl.baselibrary.base.BaseRequest;

/**
 * Created by hp on 2017/8/2.
 */

public class PayRequest extends BaseRequest {
    public String order_id;
    public String mode_id;
    public String pro_id;


    public PayRequest() {
    }

    public PayRequest(String order_id) {
        this.order_id = order_id;
    }
    public PayRequest(String mode_id, String pro_id) {
        this.mode_id = mode_id;
        this.pro_id = pro_id;
    }




}
