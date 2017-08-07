package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.widget.ListView;

import org.tzl.basedemo.R;
import org.tzl.basedemo.adapter.ItemsAdapter;
import org.tzl.baselibrary.base.BaseActivity;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ListViewWithItems extends BaseActivity {

    @Bind(R.id.lv)
    ListView mLv;
    private ItemsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_with_items);
        ButterKnife.bind(this);
        String[] funtions = getResources().getStringArray(R.array.funtion_list);
        List<String> funtionList = Arrays.asList(funtions);
        mAdapter = new ItemsAdapter(this, funtionList);
        mLv.setAdapter(mAdapter);
    }
}
