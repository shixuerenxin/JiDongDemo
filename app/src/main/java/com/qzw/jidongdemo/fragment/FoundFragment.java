package com.qzw.jidongdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.qzw.jidongdemo.acyivity.ImageShowActivity;
import com.qzw.jidongdemo.adapter.MyAdapter;
import com.qzw.jidongdemo.bean.JsonBean;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.utils.OKhttpUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Created by QiZhiWei on 2017/12/12.
 */

public class FoundFragment extends Fragment {

    private XRecyclerView xlvShow;
    private List<JsonBean.DataBean.ListBean> newslist;
    private MyAdapter myAdapter;
    private float num=1.0f;
    private Banner banCarousel;
    private List<String> images;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View inflate = inflater.inflate(R.layout.layout_found_fragment, container, false);
        xlvShow = (XRecyclerView) inflate.findViewById(R.id.xlv_show_found);
        xlvShow.setLoadingMoreEnabled(true);
        xlvShow.setPullRefreshEnabled(true);
        newslist=new ArrayList<>();
        images = new ArrayList<>();

        xlvShow.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                newslist.clear();
                num=1.0f;
                initData();
                myAdapter.notifyDataSetChanged();
                xlvShow.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                if (newslist!=null){
                    newslist.clear();
                }
                num+=2.0f;
                myAdapter.notifyDataSetChanged();
                initData();
                xlvShow.loadMoreComplete();

            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        xlvShow.setLayoutManager(linearLayoutManager);
        myAdapter = new MyAdapter(getActivity(), newslist);
        xlvShow.setAdapter(myAdapter);
        initDot();
        initData();
        myAdapter.setOnitemClickLinster(new MyAdapter.OnitemClickLinster() {
            @Override
            public void onClick(int position, String view,String image,String name) {
                Intent intent = new Intent(getActivity(), ImageShowActivity.class);
                intent.putExtra("position",image);
                intent.putExtra("view",view);
                intent.putExtra("name",name);
                startActivity(intent);
                Toast.makeText(getActivity(),"点击第"+position+"个图片",Toast.LENGTH_SHORT).show();
            }
        });
        return inflate;
    }
    private void initDot() {
        OKhttpUtils okhttpUtils=new OKhttpUtils();
        Map<String,String> map = new HashMap<>();
        map.put("lon","0.0");
        map.put("province","");
        map.put("lat","0.0");
        map.put("page",""+num);
        map.put("type","1home.firefoxchina.cn");
        //http://live.9158.com/Room/GetHotLive_v2?lon=0.0&province=&lat=0.0&page=1&type=1home.firefoxchina.cn
        okhttpUtils.post("http://live.9158.com/Room/GetHotLive_v2", map, JsonBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                JsonBean jsonBean= (JsonBean) o;
                List<JsonBean.DataBean.ListBean> list = jsonBean.getData().getList();
                View inflate = View.inflate(getActivity(), R.layout.layout_item2_found, null);
                banCarousel = (Banner)inflate.findViewById(R.id.ban_carousel);
                for (int i = 0; i < list.size(); i++) {
                    images.add(list.get(i).getSmallpic());
                }
                banCarousel.setImages(images);
                banCarousel.setDelayTime(2000);
                banCarousel.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load(path).into(imageView);
                    }
                });
                banCarousel.isAutoPlay(true);
                banCarousel.start();
                xlvShow.addHeaderView(inflate);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });


    }

    private void initData() {
        OKhttpUtils okhttpUtils=new OKhttpUtils();
        Map<String,String> map = new HashMap<>();
        map.put("lon","0.0");
        map.put("province","");
        map.put("lat","0.0");
        map.put("page",""+num);
        map.put("type","1home.firefoxchina.cn");
        //http://live.9158.com/Room/GetHotLive_v2?lon=0.0&province=&lat=0.0&page=1&type=1home.firefoxchina.cn
        okhttpUtils.post("http://live.9158.com/Room/GetHotLive_v2", map, JsonBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                JsonBean jsonBean= (JsonBean) o;
                List<JsonBean.DataBean.ListBean> list = jsonBean.getData().getList();
               /* xlvShow.addView(inflate);*/
                newslist.addAll(list);
                Toast.makeText(getActivity(),""+ newslist.get(0).getMyname(),Toast.LENGTH_SHORT).show();
                /*LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                xlvShow.setLayoutManager(linearLayoutManager);*/
                /*myAdapter = new MyAdapter(MainActivity.this, MainActivity.this.newslist);
                xlvShow.setAdapter(myAdapter);*/
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getActivity(),""+e,Toast.LENGTH_SHORT).show();
            }
        });

    }
}
