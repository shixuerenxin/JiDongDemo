package com.bwei.jidongdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.bwei.jidongdemo.R;
import com.bwei.jidongdemo.bean.ClassifyRightBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Created by QiZhiWei on 2017/12/14.
 */

public class ClassifyRightAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<ClassifyRightBean.DataBean> list;
    private OnClickItemListener onClickItemListener;
    private static int tagPosition=1;
    private int cid=1;
    private List<ClassifyRightBean.DataBean.ListBean> list1;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public static void setTagPosition(int tagPosition) {
        ClassifyRightAdapter.tagPosition = tagPosition;
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

    public ClassifyRightAdapter(Context context, List<ClassifyRightBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.layout_right_classify, null);
        /*View inflate = LayoutInflater.from(context).inflate(R.layout.layout_right_classify, null);*/
        ClassifyRightViewHolder classifyViewHolder = new ClassifyRightViewHolder(inflate);
        return classifyViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ClassifyRightViewHolder classifyRightViewHolder= (ClassifyRightViewHolder) holder;
        /*final GridView gvRightShow = classifyRightViewHolder.gvRightShow;*/
        classifyRightViewHolder.tvRightName.setText(this.list.get(position).getName());
        Map<String,String> map=new HashMap<>();
        list1 = list.get(position).getList();
        classifyRightViewHolder.gvRightShow.setAdapter(new ClassfiygvAdapter(context, list1));
        /*OKhttpUtils.post("http://120.27.23.105/product/getProductCatagory?cid="+list.get(position).getCid(),map , ClassifyRightBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                ClassifyRightBean classifyRightBean = (ClassifyRightBean) o;
                List<ClassifyRightBean.DataBean> data = classifyRightBean.getData();
                List<ClassifyRightBean.DataBean.ListBean> list1 = data.get(position).getList();
                classifyRightViewHolder.gvRightShow.setAdapter(new ClassfiygvAdapter(context,list1));
            }

            @Override
            public void onFailed(Exception e) {

            }
        });*/

        classifyRightViewHolder.gvRightShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onClickItemListener.onClick(i,0+"");
            }
        });


    }

    @Override
    public int getItemCount() {
        return list!=null?list.size():0;
    }

    public class ClassifyRightViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvRightName;
        private final GridView gvRightShow;

        public ClassifyRightViewHolder(View itemView) {
            super(itemView);
            tvRightName = (TextView)itemView.findViewById(R.id.tv_right_name);
            gvRightShow = (GridView)itemView.findViewById(R.id.gv_right_show);

        }
    }
    public interface OnClickItemListener{
        void onClick(int position, String id);
    }


}
