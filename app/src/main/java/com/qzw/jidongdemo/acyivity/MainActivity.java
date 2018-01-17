package com.qzw.jidongdemo.acyivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.fragment.ClassifyFragment;
import com.qzw.jidongdemo.fragment.FoundFragment;
import com.qzw.jidongdemo.fragment.HeadFragment;
import com.qzw.jidongdemo.fragment.MyFragment;
import com.qzw.jidongdemo.fragment.ShoppingFragment;
import com.qzw.jidongdemo.view.BubbleDrawer;
import com.qzw.jidongdemo.view.FloatBubbleView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewPager vpGroup;
    private ImageView ivHead;
    private ImageView ivClassifg;
    private ImageView ivFound;
    private ImageView ivShopping;
    private ImageView ivPerson;
    private TextView tvHead;
    private TextView tvClassifg;
    private TextView tvFound;
    private TextView tvShopping;
    private TextView tvPerson;
    private int uid;
    private FloatBubbleView mDWView;

    public MainActivity() {


    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        uid=intent.getIntExtra("uid",71);
        Log.e("-----------log", "MainActivity: "+ this.uid);

        vpGroup = (ViewPager) findViewById(R.id.vp_group);
        ivHead = (ImageView) findViewById(R.id.iv_head);
        ivClassifg = (ImageView) findViewById(R.id.iv_classifg);
        ivFound = (ImageView) findViewById(R.id.iv_found);
        ivShopping = (ImageView) findViewById(R.id.iv_shopping);
        ivPerson = (ImageView) findViewById(R.id.iv_person);
        tvHead = (TextView) findViewById(R.id.tv_head);
        tvClassifg = (TextView) findViewById(R.id.tv_classifg);
        tvFound = (TextView) findViewById(R.id.tv_found);
        tvShopping = (TextView) findViewById(R.id.tv_shopping);
        tvPerson = (TextView) findViewById(R.id.tv_person);
        //初始化操作
        initView1();
        initData1();
        /*ButterKnife.bind(this);*/
        ivHead.setOnClickListener(this);
        ivClassifg.setOnClickListener(this);
        ivFound.setOnClickListener(this);
        ivShopping.setOnClickListener(this);
        ivPerson.setOnClickListener(this);
        tvHead.setOnClickListener(this);
        tvClassifg.setOnClickListener(this);
        tvFound.setOnClickListener(this);
        tvShopping.setOnClickListener(this);
        tvPerson.setOnClickListener(this);
        vpGroup.setScrollContainer(false);
        vpGroup.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new HeadFragment();
                        break;
                    case 1:
                        fragment = new ClassifyFragment();
                        break;
                    case 2:
                        fragment = new FoundFragment();
                        break;
                    case 3:
                        fragment = new ShoppingFragment();
                        break;
                    case 4:
                        fragment = new MyFragment();
                        break;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        vpGroup.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // position :当前页面，及你点击滑动的页面；positionOffset:当前页面偏移的百分比；positionOffsetPixels:当前页面偏移的像素位置
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ivHead.setImageResource(R.drawable.red_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.RED);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.BLACK);
                        vpGroup.setCurrentItem(0);
                        break;
                    case 1:
                        ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.red_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.RED);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.BLACK);
                        vpGroup.setCurrentItem(1);
                        break;
                    case 2:
                        ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.red_refresh);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.RED);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.BLACK);
                        vpGroup.setCurrentItem(2);
                        break;
                    case 3:
                        ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.red_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.RED);
                        tvPerson.setTextColor(Color.BLACK);
                        vpGroup.setCurrentItem(3);
                        break;
                    case 4:
                        ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.red_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.RED);
                        vpGroup.setCurrentItem(4);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //state ==1的时表示正在滑动，state==2的时表示滑动完毕了，state==0的时表示什么都没做。

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
            case R.id.tv_head:
                ivHead.setImageResource(R.drawable.red_head);
                ivClassifg.setImageResource(R.drawable.black_classifg);
                ivFound.setImageResource(R.drawable.black_found);
                ivShopping.setImageResource(R.drawable.black_shopping);
                ivPerson.setImageResource(R.drawable.black_person);
                tvHead.setTextColor(Color.RED);
                tvClassifg.setTextColor(Color.BLACK);
                tvFound.setTextColor(Color.BLACK);
                tvShopping.setTextColor(Color.BLACK);
                tvPerson.setTextColor(Color.BLACK);
                vpGroup.setCurrentItem(0);
                break;
            case R.id.iv_classifg:
            case R.id.tv_classifg:
                ivHead.setImageResource(R.drawable.black_head);
                ivClassifg.setImageResource(R.drawable.red_classifg);
                ivFound.setImageResource(R.drawable.black_found);
                ivShopping.setImageResource(R.drawable.black_shopping);
                ivPerson.setImageResource(R.drawable.black_person);
                tvHead.setTextColor(Color.BLACK);
                tvClassifg.setTextColor(Color.RED);
                tvFound.setTextColor(Color.BLACK);
                tvShopping.setTextColor(Color.BLACK);
                tvPerson.setTextColor(Color.BLACK);
                vpGroup.setCurrentItem(1);
                break;
            case R.id.iv_found:
            case R.id.tv_found:
                ivHead.setImageResource(R.drawable.black_head);
                ivClassifg.setImageResource(R.drawable.black_classifg);
                ivFound.setImageResource(R.drawable.red_refresh);
                ivShopping.setImageResource(R.drawable.black_shopping);
                ivPerson.setImageResource(R.drawable.black_person);
                tvHead.setTextColor(Color.BLACK);
                tvClassifg.setTextColor(Color.BLACK);
                tvFound.setTextColor(Color.RED);
                tvShopping.setTextColor(Color.BLACK);
                tvPerson.setTextColor(Color.BLACK);
                vpGroup.setCurrentItem(2);
                break;
            case R.id.iv_shopping:
            case R.id.tv_shopping:
                ivHead.setImageResource(R.drawable.black_head);
                ivClassifg.setImageResource(R.drawable.black_classifg);
                ivFound.setImageResource(R.drawable.black_found);
                ivShopping.setImageResource(R.drawable.red_shopping);
                ivPerson.setImageResource(R.drawable.black_person);
                tvHead.setTextColor(Color.BLACK);
                tvClassifg.setTextColor(Color.BLACK);
                tvFound.setTextColor(Color.BLACK);
                tvShopping.setTextColor(Color.RED);
                tvPerson.setTextColor(Color.BLACK);
                vpGroup.setCurrentItem(3);
                break;
            case R.id.iv_person:
            case R.id.tv_person:
                ivHead.setImageResource(R.drawable.black_head);
                ivClassifg.setImageResource(R.drawable.black_classifg);
                ivFound.setImageResource(R.drawable.black_found);
                ivShopping.setImageResource(R.drawable.black_shopping);
                ivPerson.setImageResource(R.drawable.red_person);
                tvHead.setTextColor(Color.BLACK);
                tvClassifg.setTextColor(Color.BLACK);
                tvFound.setTextColor(Color.BLACK);
                tvShopping.setTextColor(Color.BLACK);
                tvPerson.setTextColor(Color.RED);
                vpGroup.setCurrentItem(4);
                break;
        }
    }
    /**
     * 初始化View
     */
    private void initView1() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        mDWView = (FloatBubbleView) findViewById(R.id.fbv_maine);
    }

    /**
     * 初始化Data
     */
    private void initData1() {
        //设置气泡绘制者
        BubbleDrawer bubbleDrawer = new BubbleDrawer(this);
        //设置渐变背景 如果不需要渐变 设置相同颜色即可
        bubbleDrawer.setBackgroundGradient(new int[]{0xffffffff, 0xffffffff});
        //给SurfaceView设置一个绘制者
        mDWView.setDrawer(bubbleDrawer);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mDWView.onDrawResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDWView.onDrawPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDWView.onDrawDestroy();
    }

}
