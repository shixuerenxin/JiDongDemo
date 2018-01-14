package com.qzw.jidongdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.bean.AddShoppingBean;
import com.qzw.jidongdemo.bean.XiangPageBean;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.utils.OKhttpUtils;

import java.util.HashMap;
import java.util.Map;

public class XiangActivity extends AppCompatActivity implements View.OnClickListener {

    private XiangPageBean xiangPageBean;
    ImageView ivXiangShow;
    TextView tvXiangTitle;
    TextView tvXiangPrice;
    private OKhttpUtils instance;
    private int uid = 71;
    private String pid;
    private ImageView icBackXiang;
    private TextView butXiangAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);
        ivXiangShow = (ImageView) findViewById(R.id.iv_xiang_show);
        icBackXiang = (ImageView) findViewById(R.id.iv_back_xiang);
        butXiangAdd = (TextView) findViewById(R.id.but_xiang_add);
        tvXiangTitle = (TextView) findViewById(R.id.tv_xiang_title);
        tvXiangPrice = (TextView) findViewById(R.id.tv_xiang_price);
        Intent intent = getIntent();
        pid = intent.getStringExtra("classpid");
        uid = intent.getIntExtra("uid", 71);
        Log.e("uid=====", "onCreate: " + uid);
        icBackXiang.setOnClickListener(this);
        butXiangAdd.setOnClickListener(this);
        getData();

    }

    private void getData() {
        final OKhttpUtils instance = OKhttpUtils.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("pid", "" + pid);
        instance.post("http://120.27.23.105/product/getProductDetail", map, XiangPageBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                XiangPageBean xiangPageBean1 = (XiangPageBean) o;
                xiangPageBean = xiangPageBean1;
                String images = xiangPageBean.getData().getImages();
                String[] split = images.split("!");
                Glide.with(XiangActivity.this).load(split[0]).into(ivXiangShow);
                tvXiangTitle.setText("商品信息:"+xiangPageBean.getData().getTitle());
                tvXiangPrice.setText("商品价格:"+xiangPageBean.getData().getPrice() + "元");

            }

            @Override
            public void onFailed(Exception e) {

            }
        });

    }

    private void initData() {
        instance = OKhttpUtils.getInstance();
        Map<String, String> map = new HashMap<>();
        map.put("source", "android");
        map.put("uid", "" + uid);
        map.put("pid", "" + xiangPageBean.getData().getPid());
        instance.post("http://120.27.23.105/product/addCart", map, AddShoppingBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                AddShoppingBean addShoppingBean = (AddShoppingBean) o;
                Toast.makeText(XiangActivity.this, "" + addShoppingBean.getMsg(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back_xiang:
                finish();
                break;
            case R.id.but_xiang_add:
                initData();
                finish();
                break;
        }
    }
}
