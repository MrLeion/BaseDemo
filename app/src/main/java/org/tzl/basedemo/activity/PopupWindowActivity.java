package org.tzl.basedemo.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;



import org.tzl.basedemo.R;
import org.tzl.basedemo.view.CommonPopupWindow;
import org.tzl.baselibrary.utils.T;
import org.tzl.baselibrary.widget.CustomDialog;
import org.tzl.baselibrary.widget.CustomProgressDialog;

public class PopupWindowActivity extends AppCompatActivity implements View.OnClickListener, CommonPopupWindow.ViewInterface {

    private TextView tvPop;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvPopBottom;
    private TextView tvFullScreen;
    private TextView tvDialog;

    CommonPopupWindow popupWindow;
    private CustomProgressDialog dialog;
    private CustomDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        initView();
    }

    private void initView() {
        tvFullScreen = (TextView) findViewById(R.id.tv_full_screen);
        tvDialog = (TextView) findViewById(R.id.tv_dialog);
        tvPop = (TextView) findViewById(R.id.tv_pop);
        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvPopBottom = (TextView) findViewById(R.id.tv_pop_bottom);
        tvPop.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvRight.setOnClickListener(this);
        tvPopBottom.setOnClickListener(this);
        tvFullScreen.setOnClickListener(this);
        tvDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pop:
                showBottomPopupWindow();
                break;
            case R.id.tv_left:
                showRightPopupWindow();
                break;
            case R.id.tv_right:
                showLeftPopupWindow();
                break;
            case R.id.tv_pop_bottom:
                showTopPopupWindow();
                break;
            case R.id.tv_dialog:
                showCenterDialog();
                break;
            case R.id.tv_full_screen:
                showBottomDialog();
                break;
        }
    }


    /**
     * 底部弹框
     */
    private void showBottomDialog() {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popup_up, null);
        //测量View的宽高
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        upView.measure(w, h);
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
     */
    private void showCenterDialog() {
        mDialog = new CustomDialog.Builder(this)
                .cancelTouchOut(false)
                .style(R.style.customDialog)
                .view(R.layout.dialog_side_by_side)
                .widthFixed(false)
                .heigthFixed(false)
                .addViewOnclick(R.id.btn_assure, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShortToast("success");
                        if (mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                    }
                })
                .addViewOnclick(R.id.btn_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShortToast("false");
                        if (mDialog.isShowing()) {
                            mDialog.dismiss();
                        }
                    }
                })
                .build();

        mDialog.show();
    }


    private void showBottomPopupWindow() {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_left_or_right)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimTranslate)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(tvPop,0,0);
    }


    private void showRightPopupWindow() {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_left_or_right)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimHorizontal)
                .setViewOnclickListener(this)
                .create();
        int[] location  = new int[2];
        tvLeft.getLocationOnScreen(location);
//        popupWindow.showAsDropDown(tvLeft, 0,0);
        popupWindow.showAsDropDown(tvLeft, tvLeft.getWidth(),-tvLeft.getHeight());
    }

    private void showLeftPopupWindow() {
        if (popupWindow != null && popupWindow.isShowing())
            return;
        popupWindow = new CommonPopupWindow.Builder(this)
                .setView(R.layout.popup_left_or_right)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.Anim2Left)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAsDropDown(tvRight, -popupWindow.getWidth(), -tvRight.getHeight());
    }

    /**
     * 一般方式显示popupWindow
     */
    private void showTopPopupWindow() {

        //设置popupWindow大小，注意一定要设置有布局参数，否则无法显示
        final PopupWindow popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //加载布局
        View contentView = LayoutInflater.from(this).inflate(R.layout.item_bank_card_tips, null);
        popupWindow.setContentView(contentView);

        //设置显示动画

        //设置PopupWindow消失监听
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        /**
         * ==========================================================================================
         * 常用配置
         * ==========================================================================================
         */
        //如果不设置背景，点击popupWindow之外就不会有反应
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        popupWindow.setFocusable(true);

        //设置超出屏幕
        popupWindow.setClippingEnabled(true);

        // TODO：设置显示位置
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int popupWindowWidth = contentView.getMeasuredWidth();
        int popupWindowHeight = contentView.getMeasuredHeight();
        int[] location = new int[2];
        tvPopBottom.getLocationOnScreen(location);
        popupWindow.showAtLocation(tvPopBottom, Gravity.NO_GRAVITY,
                (location[0] + tvPopBottom.getWidth()) - popupWindowWidth,
                location[1] - popupWindowHeight);
        ImageButton ib_close = (ImageButton) contentView.findViewById(R.id.ib_close);
        ib_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });


    }

    @Override
    public void getChildView(View view, int layoutResId) {

    }


}
