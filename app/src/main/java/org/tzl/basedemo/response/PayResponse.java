package org.tzl.basedemo.response;

import org.tzl.basedemo.bean.AliPayBean;
import org.tzl.basedemo.bean.PayBean;
import org.tzl.basedemo.bean.WePayBean;
import org.tzl.baselibrary.base.BaseResponse;

import java.util.List;

/**
 * Created by hp on 2017/8/2.
 */

public class PayResponse extends BaseResponse {
    public Data data;

    public class Data {
        public List<PayBean> modes;
        public String        total_fee;
        public String        hotline;
        public String        isSuccess;
        public WePayBean     weChatPayInfo;
        public AliPayBean    aliPayInfo;
    }
}
