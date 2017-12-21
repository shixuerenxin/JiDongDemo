package com.bwei.qizhiwei20171215;

import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.qizhiwei20171215.bean.ShoppingBean;
import com.bwei.qizhiwei20171215.utils.CallBack;
import com.bwei.qizhiwei20171215.utils.OkhttpUtils;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Banner banShow;
    private TextView tvTitle;
    private TextView tvOldPrice;
    private TextView tvNewPrice;
    private Button butAdd;
    private List<String> list;
    private String msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intiData();
        getData();
        list = new ArrayList<>();
        butAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,""+msg,Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getData() {
        OkhttpUtils okhttpUtils=new OkhttpUtils();
        Map<String,String> map=new HashMap<>();
        okhttpUtils.post("https://www.zhaoapi.cn/product/getProductDetail?source=android&pid=71", map, ShoppingBean.class, new CallBack() {
            @Override
            public void onSuccess(Object o) {
                ShoppingBean shoppingBean= (ShoppingBean) o;
                String icon = shoppingBean.getData().getImages();
                String[] split = icon.split("\\|");
                for (int i = 0; i < split.length; i++) {
                    list.add(split[i]);
                }
                banShow.setDelayTime(2000);
                banShow.setImages(list);
                banShow.setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(MainActivity.this).load(path).into(imageView);
                    }
                });
                banShow.isAutoPlay(true);
                banShow.start();
                tvTitle.setText(shoppingBean.getData().getTitle());
                tvOldPrice.setText("原价"+shoppingBean.getData().getPrice()+"");
                tvOldPrice.getPaint().setFlags(Paint. STRIKE_THRU_TEXT_FLAG ); //中间横线
                tvNewPrice.setText("优惠价："+shoppingBean.getData().getBargainPrice()+"");
                msg=shoppingBean.getMsg();

            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void intiData() {
        banShow = (Banner) findViewById(R.id.ban_show);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvOldPrice = (TextView) findViewById(R.id.tv_oldprice);
        tvNewPrice = (TextView) findViewById(R.id.tv_newprice);
        butAdd = (Button) findViewById(R.id.but_addshopingcar);
    }
}
