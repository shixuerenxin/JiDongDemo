package com.bwei.jidongdemo.headf;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * author:Created by QiZhiWei on 2017/12/6.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化二维码工具类
        ZXingLibrary.initDisplayOpinion(this);
        ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(MyApplication.this).build();
        ImageLoader.getInstance().init(build);
    }
}
