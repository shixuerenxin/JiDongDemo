package com.qzw.jidongdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.bean.ClassItem1Bean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/26.
 */

public class ClassItem1Adapter extends RecyclerView.Adapter {
    private Context context;
    private List<ClassItem1Bean.DataBean> list;
    private OnItemClickLinenter onItemClickLinenter;

    public ClassItem1Adapter(OnItemClickLinenter onItemClickLinenter) {
        this.onItemClickLinenter = onItemClickLinenter;
    }

    public OnItemClickLinenter getOnItemClickLinenter() {
        return onItemClickLinenter;
    }

    public void setOnItemClickLinenter(OnItemClickLinenter onItemClickLinenter) {
        this.onItemClickLinenter = onItemClickLinenter;
    }

    public ClassItem1Adapter(Context context, List<ClassItem1Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_right_item1_class, null);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        String images1 = list.get(position).getImages();
        String[] split1 = images1.split("\\|");
        //String images = list.get(position).getImages();
        //String[] split = images.split("\\|");
        Glide.with(context).load(split1[0]).placeholder(R.mipmap.ic_launcher).into(myViewHolder.ivShow);
        //Glide.with(context).load(split1[0]).placeholder(R.mipmap.ic_launcher).into(myViewHolder.ivShow);
        myViewHolder.tvTitle.setText(list.get(position).getTitle());
        myViewHolder.tvPrice.setText(list.get(position).getPrice()+"元");
        myViewHolder.llClassItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pid = list.get(position).getPid();
                onItemClickLinenter.onClick(pid+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder{

        private final ImageView ivShow;
        private final TextView tvTitle;
        private final TextView tvPrice;
        private final LinearLayout llClassItem1;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivShow = (ImageView)itemView.findViewById(R.id.iv_class_item1_show);
            tvTitle = (TextView)itemView.findViewById(R.id.tv_class_item1_title);
            tvPrice = (TextView)itemView.findViewById(R.id.tv_class_item1_price);
            llClassItem1 = (LinearLayout)itemView.findViewById(R.id.ll_class_item1);


        }
    }
    public interface OnItemClickLinenter{
        void onClick(String id);
    }

}
