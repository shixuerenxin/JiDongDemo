package com.bwei.jidongdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwei.jidongdemo.R;
import com.bwei.jidongdemo.bean.ClassifyLeftBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/14.
 */

public class ClassifyLeftAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ClassifyLeftBean.DataBean> list;
    private OnClickItemListener onClickItemListener;
    private static int tagPosition;

    public static void setTagPosition(int tagPosition) {
        ClassifyLeftAdapter.tagPosition = tagPosition;
    }

    public static int getTagPosition() {
        return tagPosition;
    }

    public OnClickItemListener getOnClickItemListener() {
        return onClickItemListener;
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public ClassifyLeftAdapter(Context context, List<ClassifyLeftBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_left_classify, null);
        ClassifyViewHolder classifyViewHolder = new ClassifyViewHolder(inflate);
        return classifyViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ClassifyViewHolder classifyViewHolder = (ClassifyViewHolder)holder;
        classifyViewHolder.tvClassufyLeft.setText(list.get(position).getName());
        if (position==getTagPosition()){
            classifyViewHolder.tvClassufyLeft.setBackgroundColor(Color.BLUE);
        }else {
            classifyViewHolder.tvClassufyLeft.setBackgroundColor(Color.WHITE);
        }
        classifyViewHolder.tvClassufyLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListener.onClick(position,list.get(position).getCid()+"");
            }
        });

    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class ClassifyViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvClassufyLeft;

        public ClassifyViewHolder(View itemView) {
            super(itemView);
            tvClassufyLeft = (TextView)itemView.findViewById(R.id.tv_classify_left);

        }
    }
    public interface OnClickItemListener{
        void onClick(int position,String id);
    }


}
