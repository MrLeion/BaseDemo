package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.tzl.basedemo.R;
import org.tzl.baselibrary.base.BaseActivity;
import org.tzl.baselibrary.utils.T;

import java.util.ArrayList;

import butterknife.Bind;

public class UiAnimatorActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.lv)
    ListView mLv;
    private ArrayList<String> mList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_animator);

        initData();
        initView();
        initListener();


    }


    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mList.add("第" + i + "行");
        }
    }



    private void initView() {
        mLv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,mList));
    }




    private void initListener() {
        mLv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {

            case R.id.lv:
                T.showShortToast(mList.get(position));
                break;
        }
    }
}
