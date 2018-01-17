package com.qzw.jidongdemo.utils;

import android.os.Handler;
import android.util.Log;

import com.qzw.jidongdemo.model.IModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author:Created by QiZhiWei on 2017/12/13.
 */

public class OKhttpUtils {

        private static volatile OKhttpUtils instance=null;
    private static Handler handler=new Handler();

        public OKhttpUtils() {
        }

        public static OKhttpUtils getInstance(){
            if (instance==null){
                synchronized (OKhttpUtils.class){
                    if (null==instance){
                        instance=new OKhttpUtils();
                    }
                }
            }
            return instance;
        }


    /*public synchronized static Handler getHandler(){
        if (handler==null){
            handler=new Handler();
        }
        return handler;
    }*/
        public static void get(String path, final Class cls, final IModel callback){
            OkHttpClient okHttpClient = new OkHttpClient();
            Request build = new Request.Builder()
                    .get()
                    .url(path)
                    .build();
            Call call = okHttpClient.newCall(build);
            /*call.enqueue(callback);*/
            call.enqueue(new Callback() {
                             @Override
                             public void onFailure(Call call, final IOException e) {
                                 handler.post(new Runnable() {
                                     @Override
                                     public void run() {
                                         callback.onFailed(e);
                                     }
                                 });
                             }

                             @Override
                             public void onResponse(Call call, Response response) throws IOException {
                                 final String string = response.body().string();
                                 Log.e("", "-------请求到的数据--------" + string);
                                 final Gson gson = new Gson();
                                 handler.post(new Runnable() {
                                     Object o = gson.fromJson(string, cls);

                                     @Override
                                     public void run() {
                                         callback.onSuccess(o);
                                     }
                                 });
                             }
                         }
            );
        }
        public static void post(String path, Map<String,String> map, final Class cls, final IModel callback){
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String,String> entry:map.entrySet()) {
                builder.add(entry.getKey(),entry.getValue());
            }
            OkHttpClient builder1 = new OkHttpClient();
            Request build = new Request.Builder()
                    .post(builder.build())
                    .url(path)
                    .build();
            Call call = builder1.newCall(build);
            /*call.enqueue(callback);*/
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onFailed(e);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String string = response.body().string();
                    Log.e("", "-------请求到的数据--------" + string);
                    final Gson gson = new Gson();
                    handler.post(new Runnable() {
                        Object o = gson.fromJson(string, cls);

                        @Override
                        public void run() {
                            callback.onSuccess(o);
                        }
                    });
                }

            });

        }


}
