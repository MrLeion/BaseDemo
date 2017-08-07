package org.tzl.basedemo;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.tzl.basedemo.activity.DrawerLayoutActivity;
import org.tzl.basedemo.activity.ListView4ListViewActivity;
import org.tzl.basedemo.activity.ListViewWithItems;
import org.tzl.basedemo.activity.MediaPlayActivity;
import org.tzl.basedemo.activity.NotficationCompatActivity;
import org.tzl.basedemo.activity.PopupWindowActivity;
import org.tzl.basedemo.activity.ScrollView4ExListViewActivity;
import org.tzl.basedemo.activity.ServiceTestActivity;
import org.tzl.basedemo.activity.Sound2WordActivity;
import org.tzl.basedemo.activity.UiAnimatorActivity;
import org.tzl.basedemo.activity.WebviewActivity;
import org.tzl.baselibrary.base.BaseActivity;
import org.tzl.baselibrary.utils.UIManager;

import butterknife.Bind;

public class MainActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.lv)
    ListView mLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] funtionList = getResources().getStringArray(R.array.funtion_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, funtionList);
        mLv.setAdapter(adapter);
        mLv.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Class<?> cls = null;
        switch (position) {
            case 0:
                cls = DrawerLayoutActivity.class;
                break;
            case 1:
                cls = PopupWindowActivity.class;
                break;
            case 2:
                cls = Sound2WordActivity.class;
                break;
            case 3:
                cls = WebviewActivity.class;
                break;
            case 4:
                cls = UiAnimatorActivity.class;
                break;

            case 5:
                cls = ListView4ListViewActivity.class;
                break;

            case 6:
                cls = ScrollView4ExListViewActivity.class;
                break;

            case 7:
                cls = ListViewWithItems.class;
                break;

            case 8:
                cls = ServiceTestActivity.class;
                break;

            case 9:
                cls = NotficationCompatActivity.class;
                break;

            case 10:
                cls = MediaPlayActivity.class;
                break;
        }

        UIManager.turnToAct(this, cls);
    }
}
