package org.tzl.basedemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * author: tangzenglei
 * created on: 2017/7/13 下午1:16
 * description:
 */
public class MyPagerAdapter extends PagerAdapter {





    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}