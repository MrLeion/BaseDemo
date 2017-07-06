package org.tzl.baselibrary.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import org.tzl.baselibrary.utils.SizeUtils;

/**
 * author: tangzenglei
 * created on: 2017/4/21 下午3:43
 * description: 自定义dialog
 */
public class CustomDialog extends Dialog{


    private Context context;
    private int height, width;
    private boolean isWidthFixed;
    private boolean isHeigthFixed;
    private boolean cancelTouchOut;
    private View    view;

    private CustomDialog(Builder builder) {
        super(builder.context);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        isWidthFixed = builder.isWidthFixed;
        isHeigthFixed = builder.isHeigthFixed;
        cancelTouchOut = builder.cancelTouchOut;
        view = builder.view;
    }


    private CustomDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        context = builder.context;
        height = builder.height;
        width = builder.width;
        isWidthFixed = builder.isWidthFixed;
        isHeigthFixed = builder.isHeigthFixed;
        cancelTouchOut = builder.cancelTouchOut;
        view = builder.view;
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

    public static final class Builder {

        private Context context;
        private int     height;
        private  int width;
        private boolean isWidthFixed;
        private boolean isHeigthFixed;
        private boolean cancelTouchOut;
        private View    view;
        private int resStyle = -1;


        public Builder(Context context) {
            this.context = context;
        }

        public Builder view(int resView) {
            view = LayoutInflater.from(context).inflate(resView, null);
            return this;
        }

        public Builder heightpx(int val) {
            height = val;
            return this;
        }

        public Builder widthpx(int val) {
            width = val;
            return this;
        }


        public Builder widthFixed(boolean isWidthFixed) {
            this.isWidthFixed = isWidthFixed;
            return this;
        }



        public Builder heigthFixed(boolean isHeigthFixed) {
            this.isHeigthFixed = isHeigthFixed;
            return this;
        }

        public Builder heightdp(float val) {
            height = SizeUtils.dp2px(val);
            return this;
        }

        public Builder widthdp(int val) {
            width = SizeUtils.dp2px(val);
            return this;
        }

        public Builder heightDimenRes(int dimenRes) {
            height = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder widthDimenRes(int dimenRes) {
            width = context.getResources().getDimensionPixelOffset(dimenRes);
            return this;
        }

        public Builder style(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        public Builder cancelTouchOut(boolean val) {
            cancelTouchOut = val;
            return this;
        }

        public Builder addViewOnclick(int viewRes, View.OnClickListener listener) {
            view.findViewById(viewRes).setOnClickListener(listener);
            return this;
        }


        public CustomDialog build() {
            if (resStyle != -1) {
                return new CustomDialog(this, resStyle);
            } else {
                return new CustomDialog(this);
            }
        }
    }
}
