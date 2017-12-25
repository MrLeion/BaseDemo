package org.tzl.basedemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.common.base.Strings;

import org.tzl.basedemo.bean.PayBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hp on 2017/8/3.
 */

public class PayModeAdapter extends BaseAdapter {
    List<PayBean> dataList;
    Context       mContext;
    ViewHolder    holder;

    public String getMode_id() {
        return mode_id;
    }

    public void setMode_id(String mode_id) {
        this.mode_id = mode_id;
    }

    private String mode_id;

    public String getMode_name() {
        return mode_name;
    }

    public void setMode_name(String mode_name) {
        this.mode_name = mode_name;
    }

    private String mode_name;

    public PayModeAdapter(Context mContext, List<PayBean> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
    }

    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_study_paymode, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = ((ViewHolder) convertView.getTag());
        }
        if (!Strings.isNullOrEmpty(dataList.get(position).mode_name)) {
            holder.tv_payname.setText(dataList.get(position).mode_name);
            if (dataList.get(position).mode_name.contains("微信")) {
                Glide.with(mContext).load(dataList.get(position).mode_url).placeholder(R.mipmap.ic_pay_wei).into(holder.iv_pay);
            } else {
                Glide.with(mContext).load(dataList.get(position).mode_url).placeholder(R.mipmap.ic_pay_ali).into(holder.iv_pay);
            }
        } else {
            holder.tv_payname.setText("");
        }
        if (!Strings.isNullOrEmpty(dataList.get(position).isDefault)) {
            if (dataList.get(position).isDefault.equals("1")) {
                holder.cb_pay.setChecked(true);
                setMode_id(dataList.get(position).mode_id);
                setMode_name(dataList.get(position).mode_name);
            } else {
                holder.cb_pay.setChecked(false);
            }
        } else {
            dataList.get(position).isDefault = "0";
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.get(position).isDefault.equals("0")) {
                    for (int i = 0; i < dataList.size(); i++) {
                        if (position == i) {
                            dataList.get(i).isDefault = "1";
                        } else {
                            dataList.get(i).isDefault = "0";
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_pay)
        ImageView iv_pay;
        @Bind(R.id.tv_payname)
        TextView tv_payname;
        @Bind(R.id.cb_pay)
        CheckBox cb_pay;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
