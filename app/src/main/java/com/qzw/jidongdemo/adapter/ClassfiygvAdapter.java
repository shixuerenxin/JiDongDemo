package com.qzw.jidongdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.bean.ClassifyRightBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/14.
 */

public class ClassfiygvAdapter extends BaseAdapter {

    private OnItemClickListener onItemClickListener;
    private Context context;
    private List<ClassifyRightBean.DataBean.ListBean> list;

    public ClassfiygvAdapter(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public ClassfiygvAdapter(Context context, List<ClassifyRightBean.DataBean.ListBean> list) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHoloder viewHoloder=null;
        if (view==null){
            viewHoloder=new ViewHoloder();
            view=LayoutInflater.from(context).inflate(R.layout.layout_image_item,null);
            viewHoloder.textView = (TextView) view.findViewById(R.id.tv_right_itemname);
            /*view=View.inflate(context, R.layout.layout_image_item,null);*/
            viewHoloder.imageView = (ImageView) view.findViewById(R.id.iv_classifg_item);
            view.setTag(viewHoloder);
        }else {
            viewHoloder= (ViewHoloder) view.getTag();
        }
        String icon = list.get(i).getIcon();
        Log.e("-----icon-----",""+icon);

        /*ImageLoader.getInstance().displayImage(list.get(i).getIcon(),viewHoloder.imageView);*/
        //compile 'com.github.bumptech.glide:glide:4.0.0'
        /*在glide:4.0.0RequestOptions options = new RequestOptions()
    .centerCrop()
                .placeholder(R.drawable.placeholder)
                .error(R.drawable.error)
                .priority(Priority.HIGH)
                .diskCacheStrategy(DiskCacheStrategy.NONE);*/
        /*Glide.with(fragment)
                .load(url)
                .apply(myOptions)
                .into(drawableView);*/
        viewHoloder.textView.setText(list.get(i).getName());
        Glide.with(context).load(icon).placeholder(R.mipmap.ic_launcher).into(viewHoloder.imageView);
        viewHoloder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onclick(list.get(i).getPcid(),list.get(i).getName());
            }
        });
        return view;
    }
    class ViewHoloder{
        ImageView imageView;
        TextView textView;
    }
    public interface OnItemClickListener{
        void onclick(int pcid,String pscid);
    }

}
