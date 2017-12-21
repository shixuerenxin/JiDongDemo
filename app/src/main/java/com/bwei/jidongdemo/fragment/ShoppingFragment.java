package com.bwei.jidongdemo.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jidongdemo.R;
import com.bwei.jidongdemo.ShoppingAdapter.ShoppingAdapter;
import com.bwei.jidongdemo.bean.ShopBean;
import com.bwei.jidongdemo.bean.ShoppingBean;
import com.bwei.jidongdemo.model.IModel;
import com.bwei.jidongdemo.utils.OKhttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author:Created by QiZhiWei on 2017/12/12.
 */

public class ShoppingFragment extends Fragment {

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                tvAllPrice.setText(""+msg.obj);
            }else if (msg.what==1){
                cbAllCheck.setChecked((Boolean) msg.obj);
            }

        }
    };
    private List<ShopBean.ListBean> list;
    private List<ShopBean> shopBeanList;
    private ExpandableListView elvShow;
    private ShoppingAdapter shoppingAdapter;
    private List<ShoppingBean.DataBean> shoppingBeanlist;
    private TextView tvAllPrice;
    private CheckBox cbAllCheck;
    private Boolean child=false;
    private Boolean group=false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_shopping_fragment, container, false);
        elvShow = (ExpandableListView) inflate.findViewById(R.id.elv_show);
        tvAllPrice = (TextView) inflate.findViewById(R.id.tv_all_price);
        cbAllCheck = (CheckBox) inflate.findViewById(R.id.cb_all_check);
        initData();
        shopBeanList = new ArrayList<>();

        shoppingAdapter = new ShoppingAdapter(getContext(), shopBeanList,handler);
        elvShow.setAdapter(shoppingAdapter);
        cbAllCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shoppingAdapter.setAllChecked(cbAllCheck.isChecked());
            }
        });
        return inflate;
    }
    private void initData() {

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid","71");

        OKhttpUtils.post("https://www.zhaoapi.cn/product/getCarts", map, ShoppingBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                ShoppingBean shoppingBean= (ShoppingBean) o;
                List<ShoppingBean.DataBean> data = shoppingBean.getData();

                /*shoppingBeanlist.addAll(data);*/
                for (int i = 0; i < data.size(); i++) {
                    if (i != 0) {
                        list = new ArrayList<>();
                        ShopBean shopBean = new ShopBean();
                        shopBean.setGroup(group);
                        shopBean.setSellerName(data.get(i).getSellerName());
                        for (int j = 0; j < data.get(i).getList().size(); j++) {
                            ShopBean.ListBean listBean = new ShopBean.ListBean();
                            listBean.setChild(child);
                            listBean.setNum(data.get(i).getList().get(j).getNum());
                            listBean.setPrice(data.get(i).getList().get(j).getPrice());
                            listBean.setImages(data.get(i).getList().get(j).getImages());
                            listBean.setSubhead(data.get(i).getList().get(j).getSubhead());
                            listBean.setTitle(data.get(i).getList().get(j).getTitle());
                            list.add(listBean);
                        }
                        shopBean.setList(list);
                        shopBeanList.add(shopBean);
                    }
                }
                shoppingAdapter.notifyDataSetChanged();
                for(int i = 0; i < shoppingAdapter.getGroupCount(); i++){
                    elvShow.expandGroup(i);
                }
            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
            }
        });

    }

}
