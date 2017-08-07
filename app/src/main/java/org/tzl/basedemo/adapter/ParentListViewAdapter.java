package org.tzl.basedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.tzl.basedemo.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author: tangzenglei
 * created on: 2017/7/13 上午11:17
 * description:
 */
public class ParentListViewAdapter extends BaseAdapter {


    private Context mContext;

    private List<List<String>> mList;

    public ParentListViewAdapter(Context context, List<List<String>> list) {
        mContext = context;
        mList = list;
    }


    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mList != null) {
            return mList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_parent_list_view, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        List<String> list = mList.get(position);

        InnerListViewAdapter innerListViewAdapter = new InnerListViewAdapter(mContext, list);
        holder.mLv.setAdapter(innerListViewAdapter);
        setListViewHeightBasedOnChildren(holder.mLv);

        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv)
        TextView mTv;
        @Bind(R.id.lv)
        ListView mLv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * @param  listView
     * 此方法是本次listview嵌套listview的核心方法：计算parentlistview item的高度。
     * 如果不使用此方法，无论innerlistview有多少个item，则只会显示一个item。
     **/
    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {        return;    }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

}