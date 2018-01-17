package com.qzw.jidongdemo.fragment;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qzw.jidongdemo.adapter.HeadAdapter;
import com.qzw.jidongdemo.bean.HeadBean;
import com.qzw.jidongdemo.bean.HeadGvBean;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.utils.OKhttpUtils;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Created by QiZhiWei on 2017/12/12.
 */

public class HeadFragment extends Fragment implements View.OnClickListener {

    private static final int REQUEST_CODE = 5;
    private ImageView ivSao;
    private ImageView ivXin;
    private XRecyclerView xrvHead;
    private Banner banShow;
    private List<HeadBean.MiaoshaBean.ListBeanX> headDataBean;
    private boolean isBan=true;
    private View inflateBan;
    private LinearLayout llsou;
    private HeadAdapter headAdapter;
    private View inflate1;
    private List<HeadGvBean.DataBean> listData;
    private List<HeadBean.TuijianBean.ListBean> tuijianBeen;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate1 = inflater.inflate(R.layout.layout_head_fragment, container, false);
        llsou = (LinearLayout) inflate1.findViewById(R.id.ll_shou);
        ivSao = (ImageView) inflate1.findViewById(R.id.iv_sao);
        ivXin = (ImageView) inflate1.findViewById(R.id.iv_xin);
        xrvHead = (XRecyclerView) inflate1.findViewById(R.id.xrv_head);
        headDataBean = new ArrayList<>();
        listData = new ArrayList<>();
        tuijianBeen = new ArrayList<>();
        xrvHead.setLoadingMoreEnabled(true);
        xrvHead.setPullRefreshEnabled(true);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        xrvHead.setLayoutManager(linearLayoutManager);
        headAdapter = new HeadAdapter(getContext(), headDataBean,listData,tuijianBeen);
        xrvHead.setAdapter(headAdapter);
        headAdapter.setOnItemClick(new HeadAdapter.OnItemClick() {
            @Override
            public void getMiao(int miao) {
                Toast.makeText(getContext(),""+miao,Toast.LENGTH_SHORT).show();
            }
        });

        xrvHead.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrvHead.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrvHead.loadMoreComplete();
            }
        });
        ivSao.setOnClickListener(this);
        ivXin.setOnClickListener(this);



        getData();




        return inflate1;
    }

    private void getJiu() {
        listData.clear();
        final View inflate = View.inflate(getContext(), R.layout.layout_head_item2_fragment, null);
        Map<String,String> map = new HashMap<>();
        OKhttpUtils.post("http://120.27.23.105/product/getCatagory", map, HeadGvBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                HeadGvBean cl= (HeadGvBean) o;
                List<HeadGvBean.DataBean> data = cl.getData();
                listData.addAll(data);
                headAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(Exception e) {

            }
        });


    }

    private void getData() {
        Map<String,String> map = new HashMap<>();
        OKhttpUtils.post("http://120.27.23.105/ad/getAd",map , HeadBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                HeadBean headBean= (HeadBean) o;
                List<HeadBean.DataBean> data = headBean.getData();

                    inflateBan = View.inflate(getContext(), R.layout.layout_banner, null);
                    banShow = (Banner)inflateBan.findViewById(R.id.ban_show);
                    List<String> images=new ArrayList<>();
                    for (int i = 0; i < data.size(); i++) {
                        String icon = data.get(i).getIcon();
                        images.add(icon);
                    }
                    banShow.setDelayTime(2000);
                    banShow.setImages(images);
                    banShow.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            /*.placeholder(R.mipmap.ic_launcher)*/
                    Glide.with(getContext()).load(path).into(imageView);
                        }
                    });
                    banShow.isAutoPlay(true);
                    banShow.start();
                    xrvHead.addHeaderView(inflateBan);
                HeadBean.MiaoshaBean miaosha = headBean.getMiaosha();
                List<HeadBean.MiaoshaBean.ListBeanX> listBeanXes = miaosha.getList();
                List<HeadBean.TuijianBean.ListBean> tuijian = headBean.getTuijian().getList();
                headDataBean.addAll(listBeanXes);
                tuijianBeen.addAll(tuijian);
                getJiu();
                headAdapter.notifyDataSetChanged();

                /*isBan=false;*/
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 5) {
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(getContext(), "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(getContext(), "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_sao:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.iv_xin:
                Toast.makeText(getContext(), "信息", Toast.LENGTH_LONG).show();
                break;
        }

    }
}
