package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.tzl.basedemo.R;
import org.tzl.basedemo.adapter.PayModeAdapter;
import org.tzl.basedemo.app.constant.Api;
import org.tzl.basedemo.bean.PayBean;
import org.tzl.basedemo.request.PayRequest;
import org.tzl.basedemo.response.PayResponse;
import org.tzl.basedemo.view.CommonPopupWindow;
import org.tzl.baselibrary.base.BaseActivity;
import org.tzl.baselibrary.net.callback.Callback;
import org.tzl.baselibrary.net.json.HttpManager;

import java.util.List;

import butterknife.OnClick;

public class ThirdPartyPayActivity extends BaseActivity implements CommonPopupWindow.ViewInterface {

    private PayModeAdapter payModeAdapter;
    private CommonPopupWindow popupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_party_pay);
    }


     @OnClick({R.id.btn_pay})
     public void onViewClick(View view) {
         switch (view.getId()) {

             case R.id.btn_pay:

                 btn_pay();

                 break;
         }

     }


    private void btn_pay() {





    }

    /**
     * 底部弹框
     */
    private void showBottomDialog(final List<PayBean> modes) {

        if (popupWindow != null && popupWindow.isShowing())
            return;
        View upView = LayoutInflater.from(this).inflate(R.layout.view_popup_paymode, null);
        //测量View的宽高
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        upView.measure(w, h);
        ListView lv_pay = (ListView) upView.findViewById(R.id.lv_pay);
        payModeAdapter = new PayModeAdapter(this, modes);
        lv_pay.setAdapter(payModeAdapter);
        upView.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        upView.findViewById(R.id.btn_pay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                PayBean payBean = new PayBean();
                for (int i = 0; i < modes.size(); i++) {
                    if ("1".equals(modes.get(i).isDefault)) {
                        payBean = modes.get(i);
                    }
                }
                 pay(payBean.mode_id,"2");
            }
        });
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(upView)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//取值范围0.0f-1.0f 值越小越暗
                .setAnimationStyle(R.style.Anim2Top)
                .setViewOnclickListener(this)
                .create();

        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }


    /**
     *
     * @param mode_id
     * @param charge_id
     */
    private void pay(String mode_id, String charge_id) {
        PayRequest request = new PayRequest(mode_id,"");
        HttpManager.getInstance().post(this, Api.Pay.GETPAYMENTMODE, request, PayResponse.class,new Callback(){
            @Override
            public <C> void onSuccess(int statusCode, C response) {

            }

            @Override
            public void onError(int statusCode, String errorMsg) {

            }
        });
    }

    @Override
    public void getChildView(View view, int layoutResId) {

    }
}
