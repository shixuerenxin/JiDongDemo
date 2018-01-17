package com.qzw.jidongdemo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.acyivity.DingDanActivity;
import com.qzw.jidongdemo.acyivity.MainActivity;
import com.qzw.jidongdemo.adapter.ShoppingAdapter.ShoppingAdapter;
import com.qzw.jidongdemo.bean.ShopBean;
import com.qzw.jidongdemo.bean.ShoppingBean;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.retrofit.bean.CreatDingBean;
import com.qzw.jidongdemo.retrofit.bean.DeleteShopBean;
import com.qzw.jidongdemo.retrofit.presenter.MyDeletePresenter;
import com.qzw.jidongdemo.retrofit.presenter.MyDingPresenter;
import com.qzw.jidongdemo.retrofit.view.ShopDeleteView;
import com.qzw.jidongdemo.retrofit.view.ShopDingView;
import com.qzw.jidongdemo.utils.OKhttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * author:Created by QiZhiWei on 2017/12/12.
 */

public class ShoppingFragment extends Fragment implements ShopDeleteView,ShopDingView {

    private int count;
    private Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==0){
                allprice = (String) msg.obj;
                tvAllPrice.setText(allprice+"元");
            }else if (msg.what==1){
                cbAllCheck.setChecked((Boolean) msg.obj);
            }else if (msg.what==2){
                count= (int) msg.obj;
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
    private int uid=2584;
    private MyDeletePresenter myDeletePresenter;
    private TextView tvSettlement;
    private MyDingPresenter myDingPresenter;
    private String allprice;
    private TextView toggle;
    private String trim;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        uid = ((MainActivity) context).getUid();
        Log.e(TAG, "onAttach:aaaaaaa "+ uid);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View inflate = inflater.inflate(R.layout.layout_shopping_fragment, container, false);
        elvShow = (ExpandableListView) inflate.findViewById(R.id.elv_show);
        tvAllPrice = (TextView) inflate.findViewById(R.id.tv_all_price);
        cbAllCheck = (CheckBox) inflate.findViewById(R.id.cb_all_check);
        tvSettlement = (TextView) inflate.findViewById(R.id.tv_settlement);
        toggle = (TextView)inflate.findViewById(R.id.toggle);
        initData();
       /* //设置开关显示所用的图片
        toggle.setImageRes(R.drawable.kai, R.drawable.guan, R.drawable.centerbutter);
        //设置开关的默认状态    true开启状态
        toggle.setToggleState(true);*/
        myDingPresenter = new MyDingPresenter(ShoppingFragment.this);
        shopBeanList = new ArrayList<>();
        tvSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DingDanActivity.class);
                intent.putExtra("uid",""+uid);
                if (count>0){
                    Log.e("-----", "onClick: uid"+uid+"allprice"+allprice);
                    myDingPresenter.ding(""+uid,allprice);
                    startActivity(intent);
                }else {
                    Toast.makeText(getActivity(), "没有选中的商品", Toast.LENGTH_SHORT).show();
                }
            }
        });

        shoppingAdapter = new ShoppingAdapter(getContext(), shopBeanList,handler);
        elvShow.setAdapter(shoppingAdapter);
        cbAllCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                shoppingAdapter.setAllChecked(cbAllCheck.isChecked());
            }
        });
        myDeletePresenter = new MyDeletePresenter(ShoppingFragment.this);
        shoppingAdapter.setOnClickDeleteItem(new ShoppingAdapter.OnClickDeleteItem() {
            @Override
            public void onclick(String id) {
                shopBeanList.clear();
                myDeletePresenter.delete(uid+"",id);
                Log.e("----", "onclick: "+uid+"----pid"+id );
                Toast.makeText(getContext(), "删除"+uid+"pid"+id, Toast.LENGTH_SHORT).show();
                initData();

            }
        });
        trim = toggle.getText().toString().trim();

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trim = toggle.getText().toString().trim();
                if(trim.equals("编辑")){
                    toggle.setText("完成");
                    shoppingAdapter.setMyswitch(0);
                    shoppingAdapter.notifyDataSetChanged();
                } else {
                    toggle.setText("编辑");
                    shoppingAdapter.setMyswitch(1);
                    shoppingAdapter.notifyDataSetChanged();
                }
            }
        });
        /*//设置开关的监听
        toggle.setOnToggleStateListener(new MySwitchView.OnToggleStateListener() {
            @Override
            public void onToggleState(boolean state) {
                // TODO Auto-generated method stub
                if(state){
                    shoppingAdapter.setMyswitch(0);
                    Toast.makeText(getActivity(), "开关开启", Toast.LENGTH_SHORT).show();
                    shoppingAdapter.notifyDataSetChanged();
                } else {
                    shoppingAdapter.setMyswitch(1);
                    Toast.makeText(getActivity(), "开关关闭", Toast.LENGTH_SHORT).show();
                    shoppingAdapter.notifyDataSetChanged();
                }
            }
        });*/
        return inflate;
    }
    private void initData() {

        Map<String,String> map = new HashMap<>();
        map.put("source","android");
        map.put("uid",""+uid);

        OKhttpUtils.post("https://www.zhaoapi.cn/product/getCarts", map, ShoppingBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                ShoppingBean shoppingBean= (ShoppingBean) o;
                if (shoppingBean!=null){

                    List<ShoppingBean.DataBean> data = shoppingBean.getData();

                /*shoppingBeanlist.addAll(data);*/
                    if (data!=null){
                        for (int i = 0; i < data.size(); i++) {
                    /*if (i != 0) {*/
                            list = new ArrayList<>();
                            ShopBean shopBean = new ShopBean();
                            shopBean.setGroup(group);
                            shopBean.setSellerName(data.get(i).getSellerName());
                            for (int j = 0; j < data.get(i).getList().size(); j++) {
                                ShopBean.ListBean listBean = new ShopBean.ListBean();
                                listBean.setChild(child);
                                listBean.setPid(data.get(i).getList().get(j).getPid());
                                listBean.setNum(data.get(i).getList().get(j).getNum());
                                listBean.setPrice(data.get(i).getList().get(j).getPrice());
                                listBean.setImages(data.get(i).getList().get(j).getImages());
                                listBean.setSubhead(data.get(i).getList().get(j).getSubhead());
                                listBean.setTitle(data.get(i).getList().get(j).getTitle());
                                list.add(listBean);
                            }
                            shopBean.setList(list);
                            shopBeanList.add(shopBean);
                   /* }*/
                        }
                        shoppingAdapter.notifyDataSetChanged();
                        for(int i = 0; i < shoppingAdapter.getGroupCount(); i++){
                            elvShow.expandGroup(i);
                        }
                    }else {
                        Toast.makeText(getContext(),"没有数据",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getContext(), "没有数据", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailed(Exception e) {
                Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void loginSuccess(DeleteShopBean loginBean) {
        Toast.makeText(getContext(),""+loginBean.getMsg(),Toast.LENGTH_SHORT).show();
        Toast.makeText(getContext(),"超级删除",Toast.LENGTH_SHORT).show();
        shoppingAdapter.notifyDataSetChanged();
    }


    @Override
    public void loginFaild(Throwable e) {
        Toast.makeText(getContext(),""+e,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void dingSuccess(CreatDingBean dingBean) {
        Log.e(TAG, "loginSuccess: "+dingBean.getMsg());
    }

    @Override
    public void dingFaild(Throwable e) {
        Log.e("-------", "dingFaild: "+e.getMessage());
    }
}
