package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.widget.ScrollView;

import org.tzl.basedemo.R;
import org.tzl.basedemo.adapter.ParentListViewAdapter;
import org.tzl.basedemo.app.BaseApplication;
import org.tzl.baselibrary.banner.Banner;
import org.tzl.baselibrary.base.BaseActivity;
import org.tzl.baselibrary.bitmap.GlideImageLoaderStrategy;
import org.tzl.baselibrary.widget.ListViewForScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListView4ListViewActivity extends BaseActivity implements Banner.OnBannerListener {

    @Bind(R.id.listView4ScrollView)
    ListViewForScrollView mListView4ScrollView;
    @Bind(R.id.banner)
    Banner                banner;
    @Bind(R.id.scrollView)
    ScrollView            mScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view4_list_view);
        ButterKnife.bind(this);
        String[] funtions = getResources().getStringArray(R.array.funtion_list);
        List<List<String>> arrayLists = new ArrayList<>();
        List<String> funtionList = Arrays.asList(funtions);
        for (String s : funtionList) {
            arrayLists.add(funtionList);
        }
        ParentListViewAdapter parentListViewAdapter = new ParentListViewAdapter(this, arrayLists);
        mListView4ScrollView.setAdapter(parentListViewAdapter);
        banner.setImages(BaseApplication.urlList).setImageLoader(new GlideImageLoaderStrategy()).start();
        banner.setOnBannerListener(this);

        mScrollView.post(new Runnable() {
            @Override
            public void run() {
                mScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        //        View view = View.inflate(this, R.layout.view_banner, null);
        //        Banner banner
        //                = (Banner) view.findViewById(R.id.banner);
        //        banner.setImages(BaseApplication.urlList).setImageLoader(new GlideImageLoaderStrategy()).start();
        //        mListView4ScrollView.addHeaderView(view);
    }


    @Override
    protected void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }


    @Override
    protected void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }


    @Override
    public void OnBannerClick(int position) {
        showToast("hello Banner" + position);
    }
}
