package com.bwei.jidongdemo.utils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;

/**
 * author:Created by QiZhiWei on 2017/12/13.
 */

public abstract class GsonArrayCallback<T> implements Callback {
   /* private Handler handler = OKhttpUtils.getInstance().getHandler();*/
    //主线程处理
    public abstract void onUi(List<T> list);

    //主线程处理
    public abstract void onFailed(Call call, IOException e);

   /* @Override
    public void onFailure(final Call call, final IOException e) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                onFailed(call, e);
            }
        });
    }*/

   /* @Override
    public void onResponse(Call call, Response response) throws IOException {
        final List<T> mList = new ArrayList<T>();

        String json = response.body().string();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();

        Gson gson = new Gson();

        Class<T> cls = null;
        Class clz = this.getClass();
        ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
        Type[] actualTypeArguments = type.getActualTypeArguments();
        cls = (Class<T>) actualTypeArguments[0];

        for(final JsonElement elem : array){
            //循环遍历把对象添加到集合
            mList.add((T) gson.fromJson(elem, cls));
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                onUi(mList);



            }
        });
    }*/
}
