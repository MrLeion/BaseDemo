package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import org.tzl.basedemo.R;
import org.tzl.basedemo.fragment.Fragment1;
import org.tzl.basedemo.fragment.Fragment4;
import org.tzl.baselibrary.base.BaseActivity;
import org.tzl.baselibrary.utils.T;

import java.util.ArrayList;

import butterknife.Bind;

public class DrawerLayoutActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Bind(R.id.pager)
    ViewPager       mPager;
    @Bind(android.R.id.tabhost)
    FragmentTabHost fragmentTabHost;
    private ArrayList<TabItem> mTableItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);

        //设置toolbar为标题栏,别忘了设置主题欧～～
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //同步标题栏左侧按钮状态
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        //隐藏按钮
        //        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        //                this, drawer,R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //设置navigationView点击时间
        navigationView.setNavigationItemSelectedListener(this);

        //TODO: 运用普通布局进行替代


        initTabData();
        initTabHost();

    }


    @Override
    public void onBackPressed() {
        //当点击物理返回键时，如果DrawerLayout处于打开状态则关闭
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * =========================================================================================
     * 设置toolbar右侧菜单
     * ==========================================================================================
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

            T.showShortToast("gallery");

        } else if (id == R.id.nav_slideshow) {
            T.showShortToast("show");

        } else if (id == R.id.nav_manage) {
            T.showShortToast("manage");

        } else if (id == R.id.nav_share) {
            T.showShortToast("share");

        } else if (id == R.id.nav_send) {
            T.showShortToast("send");

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //点击过后关闭侧滑菜单
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    //初始化Tab数据
    private void initTabData() {
        mTableItemList = new ArrayList<>();
        //添加tab
        mTableItemList.add(new TabItem(this, TabItem.ITEM.WANGCAI.title, TabItem.ITEM.WANGCAI.drawable, new Fragment1()));
        mTableItemList.add(new TabItem(this, TabItem.ITEM.AI.title, TabItem.ITEM.AI.drawable, new Fragment1()));
        mTableItemList.add(new TabItem(this, TabItem.ITEM.FORUM.title, TabItem.ITEM.FORUM.drawable,new Fragment1()));
        mTableItemList.add(new TabItem(this, TabItem.ITEM.ME.title, TabItem.ITEM.ME.drawable, new Fragment4()));


    }


    //初始化主页选项卡视图
    private void initTabHost() {
        //实例化FragmentTabHost对象
//        FragmentTabHost fragmentTabHost = (FragmentTabHost) findViewById(R.id);
        //关联viewpager和tabhost
        fragmentTabHost.setup(this, getSupportFragmentManager(), R.id.pager);

        //去掉分割线
        fragmentTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mTableItemList.size(); i++) {
            TabItem tabItem = mTableItemList.get(i);
            //实例化一个TabSpec,设置tab的名称和视图
            TabHost.TabSpec tabSpec = fragmentTabHost.newTabSpec(tabItem.getTitle()).setIndicator(tabItem.getView());
            //关联fragmentHost和fragment
            fragmentTabHost.addTab(tabSpec, tabItem.getFragment().getClass(), null);

            //给Tab按钮设置背景
            fragmentTabHost.getTabWidget().getChildAt(i).setBackgroundColor(getResources().getColor(R.color.white));

            //默认选中第一个tab
            if (i == 0) {
                tabItem.setChecked(true);
            }
        }

        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                //重置Tab样式
                for (int i = 0; i < mTableItemList.size(); i++) {
                    TabItem tabitem = mTableItemList.get(i);
                    if (tabId.equals(tabitem.getTitle())) {
                        tabitem.setChecked(true);
                    } else {
                        tabitem.setChecked(false);
                    }
                }
            }
        });


        mPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return mTableItemList.get(position).fragment;
            }

            @Override
            public int getCount() {
                return mTableItemList.size();
            }
        });
    }


}
