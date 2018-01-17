package com.bwei.qizhiwei20171227;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * author:Created by QiZhiWei on 2017/12/27.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
