package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import org.tzl.basedemo.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ScrollView4ExListViewActivity extends AppCompatActivity {

    @Bind(R.id.exLv)
    ExpandableListView mExLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view4_list_view);
        ButterKnife.bind(this);


    }
}
