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
import com.qzw.jidongdemo.bean.JsonBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/5.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myHandler> {

    private Context context;
    private List<JsonBean.DataBean.ListBean> list;
    private OnitemClickLinster onitemClickLinster;

    public MyAdapter(Context context, List<JsonBean.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public myHandler onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_item1_found, null);
        myHandler myHandler = new myHandler(inflate);
        return myHandler;
    }

    @Override
    public void onBindViewHolder(final myHandler holder, final int position) {
        Glide.with(context).load(list.get(position).getBigpic()).into(holder.ivShow);
        holder.tv_description.setText(list.get(position).getFamilyName().trim());
        holder.tv_title.setText(list.get(position).getMyname().trim());
        holder.llBj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onitemClickLinster.onClick(holder.getAdapterPosition(),
                        list.get(position).getBigpic(),list.get(position).getFlv(),list.get(position).getFamilyName());
            }
        });
    }

    @Override
    public int getItemCount() {
//list!=null?list.size():0
        return list!=null?list.size():0;
    }

    class myHandler extends RecyclerView.ViewHolder{
        private final ImageView ivShow;
        private final TextView tv_title;
        private final TextView tv_description;
        private final LinearLayout llBj;

        public myHandler(View itemView) {
            super(itemView);
            ivShow = (ImageView)itemView.findViewById(R.id.iv_show_found);
            tv_description = itemView.findViewById(R.id.tv_description_found);
            tv_title = itemView.findViewById(R.id.tv_title_found);
            llBj = (LinearLayout)itemView.findViewById(R.id.ll_bj_found);
        }
    }

    public void setOnitemClickLinster(OnitemClickLinster onitemClickLinster) {
        this.onitemClickLinster = onitemClickLinster;
    }

    public interface OnitemClickLinster{
        void onClick(int position, String view, String image,String name);
    }

}
