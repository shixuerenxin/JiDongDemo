package com.qzw.jidongdemo.adapter.ShoppingAdapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.bean.ShopBean;

import java.util.List;

/**
 * author:Created by QiZhiWei on 2017/12/9.
 */

public class ShoppingAdapter extends BaseExpandableListAdapter {

    private Handler handler;
    private OnClickGroupListener onClickGroupListener;
    private OnClickChildListener onClickChildListener;
    private Context context;
    private List<ShopBean> list;
    private OnClickDeleteItem onClickDeleteItem;
    private int myswitch=1;

    public int getMyswitch() {
        return myswitch;
    }

    public void setMyswitch(int myswitch) {
        this.myswitch = myswitch;
    }

    public OnClickDeleteItem getOnClickDeleteItem() {
        return onClickDeleteItem;
    }

    public void setOnClickDeleteItem(OnClickDeleteItem onClickDeleteItem) {
        this.onClickDeleteItem = onClickDeleteItem;
    }

    public OnClickGroupListener getOnClickGroupListener() {
        return onClickGroupListener;
    }

    public void setOnClickGroupListener(OnClickGroupListener onClickGroupListener) {
        this.onClickGroupListener = onClickGroupListener;
    }

    public OnClickChildListener getOnClickChildListener() {
        return onClickChildListener;
    }

    public void setOnClickChildListener(OnClickChildListener onClickChildListener) {
        this.onClickChildListener = onClickChildListener;
    }

    public ShoppingAdapter(Context context, List<ShopBean> list, Handler handler) {
        this.context = context;
        this.list = list;
        this.handler = handler;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View view, ViewGroup viewGroup) {
        view = View.inflate(context, R.layout.shopping_layout_group, null);
        /*view = LayoutInflater.from(context).inflate(R.layout.shopping_layout_group, null);*/
        final CheckBox cbGroup = (CheckBox) view.findViewById(R.id.cb_ground);
        TextView groupName = (TextView) view.findViewById(R.id.tv_group_name);
        cbGroup.setChecked(list.get(i).getGroup());
        groupName.setText(list.get(i).getSellerName());

        cbGroup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int n = 3;
                list.get(i).setGroup(b);

                List<ShopBean.ListBean> list1 = list.get(i).getList();
                for (int j = 0; j < list1.size(); j++) {
                    list1.get(j).setChild(b);
                }
                for (int j = 0; j < list.get(i).getList().size(); j++) {
                    if (list.get(i).getList().get(j).getChild()) {
                        n = 0;
                    } else {
                        n = 1;
                        break;
                    }

                }

                changeAllCkecked(isAllChecked());
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int i, final int i1, boolean b, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.shopping_layout_child, null);
        final List<ShopBean.ListBean> listBeanlist = list.get(i).getList();
        final CheckBox cbChild = (CheckBox) view.findViewById(R.id.cb_child);
        ImageView ivHead = (ImageView) view.findViewById(R.id.iv_head);
        TextView childName = (TextView) view.findViewById(R.id.tv_child_name);
        TextView tvTitle = (TextView) view.findViewById(R.id.tv_child_title);
        TextView tvPrice = (TextView) view.findViewById(R.id.tv_child_price);
        TextView tvNum = (TextView) view.findViewById(R.id.tv_child_num);
        TextView tvDelete = (TextView) view.findViewById(R.id.tv_delete);
        TextView tvAdd = (TextView) view.findViewById(R.id.tv_add);
        TextView tvDeleteItem1 = view.findViewById(R.id.tv_delete_item1);
        tvDeleteItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDeleteItem.onclick(listBeanlist.get(i1).getPid() + "");
            }
    });
        if (getMyswitch()==0){
            tvDeleteItem1.setVisibility(View.VISIBLE);
        }else {
            tvDeleteItem1.setVisibility(View.GONE);
        }
        tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = 3;
                int num = listBeanlist.get(i1).getNum();
                listBeanlist.get(i1).setChild(true);
                num--;
                if (num < 0) {
                    num = 0;
                    Toast.makeText(context, "不能再少了", Toast.LENGTH_SHORT).show();
                }
                for (int j = 0; j < listBeanlist.size(); j++) {
                    if (listBeanlist.get(j).getChild()) {
                        n = 0;
                    } else {
                        n = 1;
                        break;
                    }
                }
                if (n == 0) {
                    list.get(i).setGroup(true);
                }
                listBeanlist.get(i1).setNum(num);
                changeAllCkecked(isAllChecked());
                notifyDataSetChanged();

            }
        });
        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int n = 3;
                int num = listBeanlist.get(i1).getNum();
                listBeanlist.get(i1).setChild(true);
                num++;
                if (num < 0) {
                    num = 0;
                }
                for (int j = 0; j < listBeanlist.size(); j++) {
                    if (listBeanlist.get(j).getChild()) {
                        n = 0;
                    } else {
                        n = 1;
                        break;
                    }
                }
                if (n == 0) {
                    list.get(i).setGroup(true);
                }
                listBeanlist.get(i1).setNum(num);
                changeAllCkecked(isAllChecked());
                notifyDataSetChanged();

            }
        });/* boolean checked = cbChild.isChecked();
                listBeanlist.get(i1).setChild(checked);
                notifyDataSetChanged();
            }*/
        cbChild.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                listBeanlist.get(i1).setChild(b);
                if (b) {
                    int n = 0;
                    for (int j = 0; j < listBeanlist.size(); j++) {
                        if (listBeanlist.get(j).getChild()) {
                            n = 0;
                        } else {
                            n = 1;
                            break;
                        }
                    }
                    if (n == 0) {
                        list.get(i).setGroup(b);
                        changeAllCkecked(isAllChecked());
                    }
                } else {
                    list.get(i).setGroup(b);
                    changeAllCkecked(isAllChecked());
                }
                notifyDataSetChanged();
            }
        });

        cbChild.setChecked(listBeanlist.get(i1).getChild());
        String images = listBeanlist.get(i1).getImages();
        String[] split = images.split("!");
        Glide.with(context).load(split[0]).into(ivHead);
        childName.setText(listBeanlist.get(i1).getSubhead());
        tvTitle.setText(listBeanlist.get(i1).getTitle());
        tvPrice.setText(listBeanlist.get(i1).getPrice() + "");
        tvNum.setText(listBeanlist.get(i1).getNum() + "");
        getNum();
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public interface OnClickGroupListener {
        void onclick(String num, boolean group, int i);
    }

    public interface OnClickChildListener {
        void onclick(String num, boolean child, int i, int i1);
    }

    public interface OnClickDeleteItem {
        void onclick(String id);
    }

    public double getNum() {
        double num = 0;
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            List<ShopBean.ListBean> listbea = list.get(i).getList();
            for (int j = 0; j < listbea.size(); j++) {
                if (listbea.get(j).getChild()) {
                    int num1 = listbea.get(j).getNum();
                    count+=num1;
                    double price = listbea.get(j).getPrice();
                    num += num1 * price;
                }

            }

        }
        Message message1 = new Message();
        message1.what = 2;
        message1.obj = count;
        handler.sendMessage(message1);
        Message message = new Message();
        message.what = 0;
        message.obj = num+"";
        handler.sendMessage(message);
        return num;
    }

    private boolean isAllChildSelected(int groupPosition) {
        List<ShopBean.ListBean> listBeen1 = list.get(groupPosition).getList();

        for (int i = 0; i < listBeen1.size(); i++) {
            if (listBeen1.get(i).getChild()) {
                return false;
            }
        }

        return true;
    }

    public void setAllChecked(Boolean isAllCheck) {
        for (int i = 0; i < list.size(); i++) {
            ShopBean shopBean = list.get(i);
            shopBean.setGroup(isAllCheck);
            for (int j = 0; j < list.get(i).getList().size(); j++) {
                list.get(i).getList().get(j).setChild(isAllCheck);
            }
        }
        notifyDataSetChanged();
        getNum();
    }

    private Boolean isAllChecked() {
        for (int i = 0; i < list.size(); i++) {

            if (!list.get(i).getGroup()) {
                return false;
            }
        }
        return true;
    }

    public void changeAllCkecked(Boolean ischeck) {
        Message msg = new Message();
        msg.what = 1;
        msg.obj = ischeck;
        handler.sendMessage(msg);
    }


}
