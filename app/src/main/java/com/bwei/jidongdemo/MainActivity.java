package com.bwei.jidongdemo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwei.jidongdemo.fragment.ClassifyFragment;
import com.bwei.jidongdemo.fragment.FoundFragment;
import com.bwei.jidongdemo.fragment.HeadFragment;
import com.bwei.jidongdemo.fragment.MyFragment;
import com.bwei.jidongdemo.fragment.ShoppingFragment;

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

    /*@Bind(R.id.vp_group)
            ViewPager vpGroup;
            @Bind(R.id.iv_head)
            ImageView ivHead;
            @Bind(R.id.tv_head)
            TextView tvHead;
            @Bind(R.id.iv_classifg)
            ImageView ivClassifg;
            @Bind(R.id.tv_classifg)
            TextView tvClassifg;
            @Bind(R.id.iv_found)
            ImageView ivFound;
            @Bind(R.id.tv_found)
            TextView tvFound;
            @Bind(R.id.iv_shopping)
            ImageView ivShopping;
            @Bind(R.id.tv_shopping)
            TextView tvShopping;
            @Bind(R.id.iv_person)
            ImageView ivPerson;
            @Bind(R.id.tv_person)
            TextView tvPerson;
        */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWindows();
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
        /*ivHead.setImageResource(R.drawable.red_head);
        ivClassifg.setImageResource(R.drawable.black_classifg);
        ivFound.setImageResource(R.drawable.black_found);
        ivShopping.setImageResource(R.drawable.black_shopping);
        ivPerson.setImageResource(R.drawable.black_person);
        tvHead.setTextColor(Color.RED);
        tvClassifg.setTextColor(Color.BLACK);
        tvFound.setTextColor(Color.BLACK);
        tvShopping.setTextColor(Color.BLACK);
        tvPerson.setTextColor(Color.BLACK);*/
        vpGroup.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                switch (position) {
                    case 0:
                        fragment = new HeadFragment();
                        /*ivHead.setImageResource(R.drawable.red_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.RED);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.BLACK);*/
                        break;
                    case 1:
                        fragment = new ClassifyFragment();
                        /*ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.red_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.RED);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.BLACK);*/

                        break;
                    case 2:
                        fragment = new FoundFragment();
                       /* ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.red_refresh);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.RED);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.BLACK);*/

                        break;
                    case 3:
                        fragment = new ShoppingFragment();
                        /*ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.red_shopping);
                        ivPerson.setImageResource(R.drawable.black_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.RED);
                        tvPerson.setTextColor(Color.BLACK);*/

                        break;
                    case 4:
                        fragment = new MyFragment();
                        /*ivHead.setImageResource(R.drawable.black_head);
                        ivClassifg.setImageResource(R.drawable.black_classifg);
                        ivFound.setImageResource(R.drawable.black_found);
                        ivShopping.setImageResource(R.drawable.black_shopping);
                        ivPerson.setImageResource(R.drawable.red_person);
                        tvHead.setTextColor(Color.BLACK);
                        tvClassifg.setTextColor(Color.BLACK);
                        tvFound.setTextColor(Color.BLACK);
                        tvShopping.setTextColor(Color.BLACK);
                        tvPerson.setTextColor(Color.RED);
                        vpGroup.setCurrentItem(4);*/

                        break;
                }

                return fragment;
            }

            @Override
            public int getCount() {
                return 5;
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
    private void initWindows() {
        Window window = getWindow();
        int color = getResources().getColor(android.R.color.holo_blue_light);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            //设置状态栏颜色
            window.setStatusBarColor(color);
            //设置导航栏颜色
            window.setNavigationBarColor(color);
            ViewGroup contentView = ((ViewGroup) findViewById(android.R.id.content));
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //设置contentview为fitsSystemWindows
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            View childAt = contentView.getChildAt(0);
            if (childAt != null) {
                childAt.setFitsSystemWindows(true);
            }
            
        }
    }
}
