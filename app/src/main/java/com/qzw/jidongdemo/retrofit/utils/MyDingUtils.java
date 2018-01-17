package com.qzw.jidongdemo.retrofit.utils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author:Created by QiZhiWei on 2018/1/3.
 */

public class MyDingUtils {
    public static final String BASE_URL = "http://120.27.23.105/";
    private final Retrofit mRetrofit;

    public static class SINGLE_HOLDER {
        public static final MyDingUtils INSTANCER = new MyDingUtils(BASE_URL);
    }

    public static MyDingUtils getInstance() {
        return SINGLE_HOLDER.INSTANCER;
    }


    private MyDingUtils(String baseUrl) {
        mRetrofit = buildRetrofit();
    }

    private OkHttpClient buildOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .build();
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .client(buildOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T create(Class<T> clazz) {
        return mRetrofit.create(clazz);
    }
}
