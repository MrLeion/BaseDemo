package org.tzl.basedemo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.tzl.baselibrary.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @Bind(R.id.lv)
    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        String[] funtionList = getResources().getStringArray(R.array.funtion_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, funtionList);
        mLv.setAdapter(adapter);

    }


}
