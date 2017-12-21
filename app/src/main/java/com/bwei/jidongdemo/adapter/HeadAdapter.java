package com.bwei.jidongdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.bwei.jidongdemo.R;
import com.bwei.jidongdemo.bean.HeadBean;
import com.bwei.jidongdemo.bean.HeadGvBean;
import com.bwei.jidongdemo.headadapter.HeadgvAdapter;
import com.bwei.jidongdemo.headadapter.HeadgvMiaoAdapter;
import com.bwei.jidongdemo.view.LooperTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/14.
 */

public class HeadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TYPRONE = 0;
    private final int TYPETWO = 1;
    private final int TYPETHREE = 2;
    private Context context;
    private List<HeadBean.MiaoshaBean.ListBeanX> list;
    private List<HeadGvBean.DataBean> listData;
    private OnItemClick onItemClick;

    public interface OnItemClick{
        void getMiao(int miao);
    }

    public OnItemClick getOnItemClick() {
        return onItemClick;
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public HeadAdapter(Context context, List<HeadBean.MiaoshaBean.ListBeanX> list, List<HeadGvBean.DataBean> listData) {
        this.context = context;
        this.list = list;
        this.listData = listData;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position){
            case 0:
                return TYPRONE;
            case 1:
                return TYPETWO;
            case 2:
                return TYPETWO;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType==TYPRONE){
            View inflate = View.inflate(context, R.layout.layout_head_item2_fragment, null);

            RecyclerView.ViewHolder myViewHolder= new MyViewHolder(inflate);
            return myViewHolder;
        }else if(viewType==TYPETWO){
            View inflate = View.inflate(context, R.layout.layout_head_miaosha_fragment, null);
            RecyclerView.ViewHolder myViewHolder1= new MymiaoshaHolder(inflate);

            return myViewHolder1;
        }else if(viewType==TYPETHREE){
            View inflate = View.inflate(context, R.layout.layout_head_item2_fragment, null);
            RecyclerView.ViewHolder myViewHolder2= new MyViewHolder(inflate);
            return myViewHolder2;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder myViewHolder= (MyViewHolder) holder;
            myViewHolder.ltv.setTipList(generateTips());
            HeadgvAdapter headgvAdapter = new HeadgvAdapter(context, listData);
            myViewHolder.gvHeadItem2.setAdapter(headgvAdapter);
            myViewHolder.gvHeadItem2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    onItemClick.getMiao(i);
                }
            });


        }else if(position==TYPETWO){
            MymiaoshaHolder mymiaoshaHolder= (MymiaoshaHolder) holder;

            mymiaoshaHolder.gvHeadItemmiso.setAdapter(new HeadgvMiaoAdapter
                    (context,list));
            mymiaoshaHolder.gvHeadItemmiso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    onItemClick.getMiao(i);
                }
            });


        }else if(position==TYPETHREE){


        }

    }

    @Override
    public int getItemCount() {
        /*list!=null?list.size():0*/
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        private final GridView gvHeadItem2;
        private final LooperTextView ltv;

        public MyViewHolder(View itemView) {
            super(itemView);
            gvHeadItem2 = (GridView)itemView.findViewById(R.id.gv_head_item2);
            ltv = (LooperTextView)itemView.findViewById(R.id.ltv);


        }
    }
    public class MymiaoshaHolder extends RecyclerView.ViewHolder{


        private final GridView gvHeadItemmiso;

        public MymiaoshaHolder(View itemView) {
            super(itemView);
            gvHeadItemmiso = (GridView)itemView.findViewById(R.id.gv_head_miao_item2);


        }
    }
    private List<String> generateTips() {
        List<String> tips = new ArrayList<>();
        tips.add("赵丽颖");
        tips.add("杨颖");
        tips.add("郑爽");
        tips.add("杨幂");
        tips.add("刘诗诗");
        tips.add("迪丽热巴");
        tips.add("李沁");
        tips.add("唐嫣");
        tips.add("林心如");
        tips.add("陈乔恩");
        tips.add("范冰冰");
        tips.add("刘亦菲");
        tips.add("李小璐");
        tips.add("佟丽娅");
        return tips;
    }




}
