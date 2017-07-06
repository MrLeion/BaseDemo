package org.tzl.basedemo.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.tzl.basedemo.R;

/**
 * author: tangzenglei
 * created on: 2017/4/26 下午4:42
 * description:
 */
class TabItem {


    private Context mContext;
    private String title;

    private int drawableId;
    //tab对应的fragment
    public Fragment fragment;
    public TextView  textView;


    public TabItem(Context context, String title, int drawableId, Fragment fragmentClass) {
        mContext = context;
        this.title = title;
        this.drawableId = drawableId;
        this.fragment = fragmentClass;
    }

    public Fragment getFragment() {
        return fragment;
    }


    public String getTitle() {
        return  title;
    }



    public View getView() {

            this.textView= (TextView)LayoutInflater.from(mContext).inflate(R.layout.item_tab_fragment_host, null);
            Drawable topDrawable = mContext.getResources().getDrawable(drawableId);
            /**
             * 设置图片
             */
            if (topDrawable!=null) {
                this.textView.setCompoundDrawables(null, topDrawable, null, null);
            }
            /**
             * 设置文字
             */
            if (!TextUtils.isEmpty(title)) {
                this.textView.setText(title);
            }

        return this.textView;
    }


    public void setChecked(boolean checked) {
        textView.setSelected(checked);

    }


    /**
     * 底部菜单栏数据
     */
    public static enum ITEM{

        WANGCAI(1,"望财",R.drawable.selector_main_tab_one_icon),
        AI(2,"智能理财",R.drawable.selector_main_tab_two_icon),
        FORUM(3,"社区",R.drawable.selector_main_tab_three_icon),
        ME(4,"我", R.drawable.selector_main_tab_four_icon);


        public int id;
        public String title;
        public int drawable;


        ITEM(int id, String title, int drawable) {
            this.id = id;
            this.title = title;
            this.drawable = drawable;
        }
    }
}