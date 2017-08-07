package org.tzl.basedemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.tzl.basedemo.R;

import java.util.List;

/**
 * author: tangzenglei
 * created on: 2017/7/13 上午10:47
 * description:
 */
public class ItemsAdapter  extends BaseAdapter{


    private Context      mContext;
    private List<String> mList;


    public ItemsAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    /**
     * 三种类型item,需要从0开始
     */
    final int TYPE_1 = 0;
    final int TYPE_2 = 1;
    final int TYPE_3 = 2;

    @Override
    public int getCount() {
        if (mList!=null) {
            return mList.size();
        }
        return 0;
    }

    @Override
    public String getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        viewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            holder = new viewHolder();
            switch (type) {
                case TYPE_1:
                    convertView = View.inflate(mContext, R.layout.item_layout1, null);
                    holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);
                    holder.textView = (TextView) convertView.findViewById(R.id.tv_1);
                    break;
                case TYPE_2:
                    convertView = View.inflate(mContext, R.layout.item_layout2, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tv_2);
                    break;
                case TYPE_3:
                    convertView = View.inflate(mContext, R.layout.item_layout3, null);
                    holder.textView = (TextView) convertView.findViewById(R.id.tv_3);
                    holder.imageView = (ImageView) convertView.findViewById(R.id.iv_3);
                    break;
                default:
                    break;
            }
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.textView.setText(Integer.toString(position));
        return convertView;
    }

    static class viewHolder{
        CheckBox checkBox;
        TextView textView;
        ImageView imageView;

    }


    @Override
    public int getItemViewType(int position) {
        int p = position % 6;
        if (p == 0)
            return TYPE_1;
        else if (p < 3)
            return TYPE_2;
        else if (p < 6)
            return TYPE_3;
        else
            return TYPE_1;
    }

    @Override
    public int getViewTypeCount() {
        return 3;
    }
}