package com.qzw.jidongdemo.bean;

import com.google.gson.Gson;

/**
 * author:Created by WangZhiQiang on 2017/11/27.
 */

public class GsonBean {
    private static volatile Gson instance;
    public static Gson getInstance(){
        if (instance==null){
            instance = new Gson();
        }
        return instance;
    }

}
