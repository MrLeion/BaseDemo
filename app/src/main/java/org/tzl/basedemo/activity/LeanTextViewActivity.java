package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.tzl.basedemo.R;
import org.tzl.basedemo.view.LeanTextView;
import org.tzl.baselibrary.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LeanTextViewActivity extends BaseActivity {

    @Bind(R.id.ltv_lt)
    LeanTextView mLtvLt;
    @Bind(R.id.ltv_rt)
    LeanTextView ltv_rt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_text_view);
        ButterKnife.bind(this);

        mLtvLt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textview = (TextView) v;
                showToast("text:"+textview.getText());
            }
        });

        ltv_rt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("left:"+v.getLeft()+"width:"+v.getWidth());
            }
        });




    }
}
