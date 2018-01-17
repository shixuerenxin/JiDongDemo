package com.qzw.jidongdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.acyivity.ClassItem1Activity;
import com.qzw.jidongdemo.acyivity.MainActivity;
import com.qzw.jidongdemo.adapter.ClassifyLeftAdapter;
import com.qzw.jidongdemo.adapter.ClassifyRightAdapter;
import com.qzw.jidongdemo.bean.ClassifyLeftBean;
import com.qzw.jidongdemo.bean.ClassifyRightBean;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.utils.OKhttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Created by QiZhiWei on 2017/12/12.
 */

public class ClassifyFragment extends Fragment {

    private RecyclerView rlvLeft;
    private RecyclerView rlvRight;
    private List<ClassifyLeftBean.DataBean> list;
    private ClassifyLeftAdapter classifyLeftAdapter;
    private int cid=1;
    private List<ClassifyRightBean.DataBean> classifyRightBean;
    private ClassifyRightAdapter classifyRightAdapter;
    private int uid;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        uid=((MainActivity)context).getUid();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.layout_classify_fragment, container, false);
        rlvLeft = (RecyclerView)inflate.findViewById(R.id.rlv_left_classify);
        rlvRight = (RecyclerView)inflate.findViewById(R.id.rlv_right_classify);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        //得到WindowManager
        WindowManager windowManager = getActivity().getWindowManager();
        Display display = windowManager.getDefaultDisplay();
        //得到屏幕宽
        int width = display.getWidth();
        //将RecyclerView宽设置为屏幕宽的1/5
        params.width = width * 1 / 5;
        rlvLeft.setLayoutParams(params);
        list = new ArrayList<>();
        classifyRightBean = new ArrayList<>();
        LinearLayout linearLayout=new LinearLayout(getContext());
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rlvLeft.setLayoutManager(linearLayoutManager);
        classifyLeftAdapter = new ClassifyLeftAdapter(getContext(), list);
        rlvLeft.setAdapter(classifyLeftAdapter);
        LinearLayoutManager linear =new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rlvRight.setLayoutManager(linear);
        classifyRightAdapter = new ClassifyRightAdapter(getContext(), classifyRightBean);
        rlvRight.setAdapter(classifyRightAdapter);
        classifyLeftAdapter.setOnClickItemListener(new ClassifyLeftAdapter.OnClickItemListener() {
            @Override
            public void onClick(int position, String id) {
                int i = Integer.parseInt(id);
                    cid=i;
                classifyLeftAdapter.setTagPosition(position);
                Toast.makeText(getContext(),""+id+"----"+position,Toast.LENGTH_SHORT).show();
                classifyLeftAdapter.notifyDataSetChanged();
                classifyRightAdapter.notifyDataSetChanged();
                getRightData();

            }
        });

        classifyRightAdapter.setOnClickItemListener(new ClassifyRightAdapter.OnClickItemListener() {
            @Override
            public void onClick(int position, String id) {
                Toast.makeText(getContext(),""+position+"-==--"+id,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), ClassItem1Activity.class);
                intent.putExtra("keywords",id);
                intent.putExtra("uid",uid);
                startActivity(intent);

            }
        });
        getData();
        getRightData();
        return inflate;
    }

    private void getRightData() {
        classifyRightBean.clear();
        Map<String,String> map = new HashMap<>();
        map.put("cid",""+cid);

        OKhttpUtils.post("http://120.27.23.105/product/getProductCatagory",map,ClassifyRightBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                ClassifyRightBean classifyLeftBean= (ClassifyRightBean) o;
                List<ClassifyRightBean.DataBean> data = classifyLeftBean.getData();
                if (data!=null){
                    classifyRightBean.addAll(data);

                    classifyRightAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void getData() {
        Map<String,String> map = new HashMap<>();

        OKhttpUtils.post("http://120.27.23.105/product/getCatagory",map,ClassifyLeftBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                ClassifyLeftBean classifyLeftBean= (ClassifyLeftBean) o;
                List<ClassifyLeftBean.DataBean> data = classifyLeftBean.getData();
                if (list!=null){
                    list.addAll(data);
                    classifyLeftAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
            }
        });
    }


}
