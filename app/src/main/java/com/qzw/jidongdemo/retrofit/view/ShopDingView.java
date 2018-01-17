package com.qzw.jidongdemo.retrofit.view;

import com.qzw.jidongdemo.retrofit.bean.CreatDingBean;
import com.qzw.jidongdemo.view.IView;

/**
 * author:Created by QiZhiWei on 2018/1/3.
 */

public interface ShopDingView extends IView,IDingView {

    void dingSuccess(CreatDingBean dingBean);
    void dingFaild(Throwable e);

}
