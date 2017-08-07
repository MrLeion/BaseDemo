package org.tzl.basedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
public class InnerListViewAdapter extends BaseAdapter {


    private Context mContext;

    private List<String> mList;

    public InnerListViewAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        if (mList!=null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (mList!=null) {
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
            convertView = View.inflate(mContext, R.layout.item_inner_listview, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTv.setText(mList.get(position));
        return convertView;
    }


    static class ViewHolder {
        @Bind(R.id.tv)
        TextView mTv;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}