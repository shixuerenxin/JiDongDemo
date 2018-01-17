package com.qzw.jidongdemo.retrofit.view;

import com.qzw.jidongdemo.retrofit.bean.DeleteShopBean;
import com.qzw.jidongdemo.view.IView;

/**
 * author:Created by QiZhiWei on 2018/1/3.
 */

public interface DeleteView extends IView, IDeleteView {

    void loginSuccess(DeleteShopBean loginBean);
    void loginFaild(Throwable e);

}
