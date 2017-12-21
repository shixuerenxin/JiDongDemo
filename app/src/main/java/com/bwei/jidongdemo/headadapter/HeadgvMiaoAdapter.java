package com.bwei.jidongdemo.headadapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.bwei.jidongdemo.bean.HeadBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/16.
 */

public class HeadgvMiaoAdapter extends BaseAdapter {

    private Context context;
    private List<HeadBean.MiaoshaBean.ListBeanX> list;

    public HeadgvMiaoAdapter(Context context, List<HeadBean.MiaoshaBean.ListBeanX> list) {
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
        MyViewHolder myViewHolder=null;
        if (view==null){
            myViewHolder=new MyViewHolder();
            view = View.inflate(context, R.layout.layout_head_miaosha_item1, null);
            myViewHolder.tv1 = view.findViewById(R.id.tv_miao_title);
            myViewHolder.iv1 = view.findViewById(R.id.iv_head_miao1);
            myViewHolder.iv2 = view.findViewById(R.id.iv_head_miao2);
            view.setTag(myViewHolder);
        }else {
            myViewHolder= (MyViewHolder) view.getTag();
        }
        String images = list.get(i).getImages();
        String[] split = images.split("\\|");
        myViewHolder.tv1.setText(list.get(i).getTitle());
        Glide.with(context).load(split[0]).placeholder(R.mipmap.ic_launcher).into(myViewHolder.iv1);
        Glide.with(context).load(split[1]).placeholder(R.mipmap.ic_launcher).into(myViewHolder.iv2);
        return view;
    }
    class MyViewHolder{
        TextView tv1;
        ImageView iv1;
        ImageView iv2;
    }
}
