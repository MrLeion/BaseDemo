package org.tzl.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.tzl.baselibrary.inter.Loading;
import org.tzl.baselibrary.utils.SizeUtils;

/**
 * author: tangzenglei
 * created on: 2017/4/21 下午4:26
 * description: 网络加载动画加载框
 */
public class CustomProgressDialog extends Dialog implements Loading{


    private Context context;
    private int     height, width;
    private boolean isWidthFixed;
    private boolean isHeigthFixed;
    private boolean cancelTouchOut;
    private View    view;
    private int animRes ;

    private CustomProgressDialog(CustomProgressDialog.Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        isWidthFixed = builder.isWidthFixed;
        isHeigthFixed = builder.isHeigthFixed;
        cancelTouchOut = builder.cancelTouchOut;
        view = builder.view;
        animRes = builder.animRes;
    }


    private CustomProgressDialog(CustomProgressDialog.Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        isWidthFixed = builder.isWidthFixed;
        isHeigthFixed = builder.isHeigthFixed;
        cancelTouchOut = builder.cancelTouchOut;
        view = builder.view;
        animRes = builder.animRes;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(view);


        setCanceledOnTouchOutside(cancelTouchOut);

        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        if (isWidthFixed) {
            lp.width = width;
        }
        if (isHeigthFixed) {
            lp.height = height;
        }
        win.setAttributes(lp);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        try {
            if (animRes!=-1) {
                View drawableView = view.findViewById(animRes);
                AnimationDrawable animationDrawable= (AnimationDrawable)drawableView.getBackground();
                animationDrawable.start();
            }
        } catch (Exception e) {
        }

    }

    @Override
    public void showLoading() {
        if (!this.isShowing()) {
            this.show();
        }
    }

    @Override
    public void hideLoading() {
        if (this.isShowing()) {
            this.dismiss();
        }
    }





    public static final class Builder {

        private Context context;
        private int     height;
        private  int width;
        private boolean isWidthFixed;
        private boolean isHeigthFixed;
        private boolean cancelTouchOut;
        private View    view;
        private int resStyle = -1;
        private int animRes= -1;


        public Builder aniRes(int animRes) {
            this.animRes = animRes;

            return this;
        }


        public Builder(Context context) {
            this.context = context;
        }

        public CustomProgressDialog.Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public CustomProgressDialog.Builder heightpx(int val) {
            height = val;
            return this;
        }

        public CustomProgressDialog.Builder widthpx(int val) {
            width = val;
            return this;
        }


        public CustomProgressDialog.Builder widthFixed(boolean isWidthFixed) {
            this.isWidthFixed = isWidthFixed;
            return this;
        }



        public CustomProgressDialog.Builder heigthFixed(boolean isHeigthFixed) {
            this.isHeigthFixed = isHeigthFixed;
            return this;
        }

        public CustomProgressDialog.Builder heightdp(float val) {
            height = SizeUtils.dp2px(val);
            return this;
        }

        public CustomProgressDialog.Builder widthdp(int val) {
            width = SizeUtils.dp2px(val);
            return this;
        }

        public CustomProgressDialog.Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public CustomProgressDialog.Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public CustomProgressDialog.Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public CustomProgressDialog.Builder cancelTouchOut(boolean val) {
            cancelTouchOut = val;
            return this;
        }

        public CustomProgressDialog.Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }


        public CustomProgressDialog build() {
            if (resStyle != -1) {
                return new CustomProgressDialog(this, resStyle);
            } else {
                return new CustomProgressDialog(this);
            }
        }
    }
}