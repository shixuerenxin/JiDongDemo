package com.qzw.jidongdemo.acyivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.adapter.ShopDingAdapter;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.retrofit.bean.DingShopBean;
import com.qzw.jidongdemo.utils.OKhttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DingDanActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivDingBack;
    private TextView tvQuDing;
    private TextView tvYiDing;
    private TextView tvDaiDing;
    private TextView tvAllDing;
    private RecyclerView rlvDingShop;
    private String page=0+"";
    private String status=-1+"";
    private String uid="";
    private List<DingShopBean.DataBean> list;
    private List<String> stringList;
    private ShopDingAdapter shopDingAdapter;
    private Boolean isUid=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ding_dan);
        ivDingBack = (ImageView) findViewById(R.id.iv_ding_back);
        tvAllDing = (TextView) findViewById(R.id.tv_all_ding);
        tvDaiDing = (TextView) findViewById(R.id.tv_dai_ding);
        tvYiDing = (TextView) findViewById(R.id.tv_yi_ding);
        tvQuDing = (TextView) findViewById(R.id.tv_qu_ding);
        rlvDingShop = (RecyclerView) findViewById(R.id.rlv_ding_shop);
        tvAllDing.setOnClickListener(this);
        tvYiDing.setOnClickListener(this);
        tvQuDing.setOnClickListener(this);
        tvDaiDing.setOnClickListener(this);
        list = new ArrayList<>();
        stringList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager=
                new LinearLayoutManager(DingDanActivity.this,LinearLayoutManager.VERTICAL,false);
        rlvDingShop.setLayoutManager(linearLayoutManager);
        stringList.add("http://p1.wmpic.me/article/2017/12/25/1514180742_btBZnZnc.jpg");
        shopDingAdapter = new ShopDingAdapter(DingDanActivity.this, list, stringList);
        shopDingAdapter.setOnClickPosition(new ShopDingAdapter.OnClickPosition() {
            @Override
            public void onClick(String position) {

            }
        });

        rlvDingShop.setAdapter(shopDingAdapter);
        ivDingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initData();

    }

    private void initData() {
        if (isUid){
            Intent intent = getIntent();
            uid = intent.getStringExtra("uid");
        }
        //http://120.27.23.105/product/getOrders?uid=1299&page=0&status=-1
        Map<String ,String >map = new HashMap<>();
        Log.e("----", "initData: uid"+uid+"-----page"+page+"----status"+status );
        map.put("uid",""+uid);
        map.put("page",""+page);
        map.put("status",""+status);
        OKhttpUtils.getInstance().post("http://120.27.23.105/product/getOrders", map, DingShopBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                DingShopBean dingshopBean= (DingShopBean) o;
                List<DingShopBean.DataBean> data = dingshopBean.getData();
                list.addAll(data);
                shopDingAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(DingDanActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_all_ding:
                list.clear();
                status="-1";
                initData();
                break;
            case R.id.tv_dai_ding:
                list.clear();
                status="0";
                initData();
                break;
            case R.id.tv_yi_ding:
                list.clear();
                status="1";
                initData();
                break;
            case R.id.tv_qu_ding:
                list.clear();
                status="2";
                initData();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isUid=false;
    }
}
