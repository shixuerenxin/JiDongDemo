package com.qzw.jidongdemo.acyivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.fragment.Fragment1;
import com.qzw.jidongdemo.fragment.Fragment2;
import com.qzw.jidongdemo.fragment.Fragment3;
import com.qzw.jidongdemo.fragment.Fragment4;


public class IndexActivity extends AppCompatActivity {

    private TextView index;
    private SharedPreferences name;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            SharedPreferences.Editor edit = name.edit();
            if (what==0){
                edit.putInt("tiao",1);
                edit.commit();
                /*String string = index.getText().toString();
                int i = Integer.parseInt(string);
                if (i==0){
                    index.setText(i+"");
                    Intent intent = new Intent(IndexActivity.this, LoginActivity.class);
                    edit.putInt("tiao",1);
                    edit.commit();
                    startActivity(intent);
                    finish();
                    handler.removeMessages(0);
                    handler.removeMessages(1);
                } else {
                    i-=1;
                    index.setText(i+"");
                    handler.sendEmptyMessageDelayed(0,1000);
                }*/

            }else if (what==1){
                String string = index.getText().toString();
                int i = Integer.parseInt(string);
                if (i==0){
                    index.setText(i+"");
                    Intent intent = new Intent(IndexActivity.this, LoginActivity.class);
                    edit.putInt("tiao",1);
                    edit.commit();
                    startActivity(intent);
                    finish();
                    handler.removeMessages(1);
                    handler.removeMessages(0);
                } else {
                    i-=1;
                    index.setText(i+"");
                    handler.sendEmptyMessageDelayed(1,1000);
                }
            }

        }
    };
    private ViewPager vpShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        index = (TextView) findViewById(R.id.tv_index_num);
        vpShow = (ViewPager) findViewById(R.id.vp_show);
        name = getSharedPreferences("name", 0);

        int diao = name.getInt("tiao", 10);
        if (diao==1){
            handler.sendEmptyMessageDelayed(1,1000);
        }else {
            vpShow.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
                @Override
                public Fragment getItem(int position) {
                    Fragment fragment=null;
                    switch (position){
                        case 0:
                            fragment=new Fragment1();
                            break;
                        case 1:
                            fragment=new Fragment2();
                            break;
                        case 2:
                            fragment=new Fragment3();
                            break;
                        case 3:
                            fragment=new Fragment4();
                            break;
                    }

                    return fragment;
                }

                @Override
                public int getCount() {
                    return 4;
                }
            });
            handler.sendEmptyMessage(0);
        }




    }
}
