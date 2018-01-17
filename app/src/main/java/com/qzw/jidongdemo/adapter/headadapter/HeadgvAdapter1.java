package com.qzw.jidongdemo.adapter.headadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.bean.HeadGvBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/16.
 */

public class HeadgvAdapter1 extends BaseAdapter {

    private Context context;
    private List<HeadGvBean.DataBean> list;

    public HeadgvAdapter1(Context context, List<HeadGvBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.layout_image_head1, null);
        ImageView ivHeadItem = (ImageView)view.findViewById(R.id.iv_head_item1);
        TextView tvHeadItemName = (TextView)view.findViewById(R.id.tv_head_itemname1);
        int ishome = list.get(i).getIshome();
        if (ishome==1){
            Glide.with(context).load(list.get(i).getIcon()).placeholder(R.mipmap.ic_launcher).into(ivHeadItem);
            tvHeadItemName.setText(list.get(i).getName());
        }

        return view;
    }
}
