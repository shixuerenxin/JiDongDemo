package com.qzw.jidongdemo.fragment;

import android.Manifest;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.acyivity.DingDanActivity;
import com.qzw.jidongdemo.acyivity.MainActivity;
import com.qzw.jidongdemo.bean.MyDataBean;
import com.qzw.jidongdemo.bean.UPNameBean;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.utils.OKhttpUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

/**
 * author:Created by QiZhiWei on 2017/12/12.
 */

public class MyFragment extends Fragment implements View.OnClickListener {

    private int uid;
    private TextView tvName;
    private ImageView myhead;
    private static final int TAKE_PHOTO_REQUEST=5;
    private static final int IMAGE_REQUEST_CODE=1;
    private String[]mCustomItems=new String[]{"本地相册","相机拍照"};
    private String path;
    private SharedPreferences sharedPreferences;
    private ImageView ivSet;
    private ImageView ivFu;
    private LinearLayout myItem5;
    private LinearLayout myItem3;
    private LinearLayout myItem2;
    private LinearLayout myItem1;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        uid = ((MainActivity) context).getUid();
        Log.e(TAG, "onAttach:aaaaaaa "+ uid);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.layout_my_fragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("" + uid, 5);

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                    1);}
        myhead = (ImageView)inflate.findViewById(R.id.iv_my_head);
        tvName = (TextView)inflate.findViewById(R.id.tv_username);
        ivSet = (ImageView)inflate.findViewById(R.id.iv_set);
        ivFu = (ImageView)inflate.findViewById(R.id.iv_fu);
        myItem1 = (LinearLayout)inflate.findViewById(R.id.ll_my_item1);
        myItem2 = (LinearLayout)inflate.findViewById(R.id.ll_my_item2);
        myItem3 = (LinearLayout)inflate.findViewById(R.id.ll_my_item3);
        myItem5 = (LinearLayout)inflate.findViewById(R.id.ll_my_item5);
        myItem1.setOnClickListener(this);
        myItem5.setOnClickListener(this);
        myItem2.setOnClickListener(this);
        myItem3.setOnClickListener(this);
        initData();
        getData();
        getphoto();
        floatAnim(ivFu,1000);
        ivSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText inputServer = new EditText(getActivity());
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("修改姓名").setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                        .setNegativeButton("Cancel", null);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        String string = inputServer.getText().toString();
                        if (string.trim()==""){
                            Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT).show();
                        }else {
                            upDataName(string);
                            initData(); 
                        }
                        
                    }
                });
                builder.show();
            }
        });
        return inflate;
    }

    private void getData() {
        myhead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, TAKE_PHOTO_REQUEST);*/
                showDialogCustom();

            }
        });
    }
    private void showDialogCustom(){
        //创建对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("请选择：");
        builder.setItems(mCustomItems, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0) {
                    //相册
//在这里跳转到手机系统相册里面
                    Intent intent = new Intent(
                            Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, IMAGE_REQUEST_CODE);
                }else if(which==1){
                    //照相机
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, TAKE_PHOTO_REQUEST);

                }
            }
        });
        builder.create().show();
    }


    private void initData() {
        Map<String,String> map=new HashMap<>();
        map.put("uid",""+uid);
        OKhttpUtils.post("http://120.27.23.105/user/getUserInfo", map, MyDataBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                MyDataBean myDataBean= (MyDataBean) o;

                String nickname = myDataBean.getData().getNickname();
                Log.e(TAG, "onSuccess: "+nickname);
                if (nickname==null){
                    tvName.setText(myDataBean.getData().getUsername()+"");
                }else {
                    tvName.setText(nickname);
                }

            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
    private void upDataName(String name) {
        //http://120.27.23.105/user/updateNickName
        Map<String,String> map=new HashMap<>();
        map.put("uid",""+uid);
        map.put("nickname",""+name);
        OKhttpUtils.post("http://120.27.23.105/user/updateNickName", map, UPNameBean.class, new IModel() {
            @Override
            public void onSuccess(Object o) {
                UPNameBean upNameBean= (UPNameBean) o;
                Toast.makeText(getActivity(), ""+upNameBean.getMsg(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
    public void getphoto(){
        int num = sharedPreferences.getInt("num", 6);
        String photo = sharedPreferences.getString("photo", "");
        if (num==2){
            byte[] decode = Base64.decode(photo, Base64.DEFAULT);
            Bitmap bitmap=BitmapFactory.decodeByteArray(decode, 0, decode.length);
            myhead.setImageBitmap(bitmap);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences.Editor edit = sharedPreferences.edit();

        switch (requestCode){
            case TAKE_PHOTO_REQUEST:

                if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getActivity(), "取消了拍照", Toast.LENGTH_LONG).show();
                    return;
                }
                Bitmap  photo = data.getParcelableExtra("data");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG,90,outputStream );

                byte[] bytes = outputStream.toByteArray();
                //得到图片的String
                String imageStr = Base64.encodeToString(bytes,0);
                edit.putString("photo", imageStr);
                edit.putInt("num",2);
                edit.commit();
                myhead.setImageBitmap(photo);
                break;
            case IMAGE_REQUEST_CODE://这里的requestCode是我自己设置的，就是确定返回到那个Activity的标志
                if (resultCode == RESULT_OK) {//resultcode是setResult里面设置的code值
                    try {
                        Uri selectedImage = data.getData(); //获取系统返回的照片的Uri
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContext().getContentResolver().query(selectedImage,//getContentResolver
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();

                        Bitmap bitmap = BitmapFactory.decodeFile(path);
                        edit.putInt("num",2);
                        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG,90,outputStream1 );

                        byte[] bytes1 = outputStream1.toByteArray();
                        //得到图片的String
                        String imageStr1 = Base64.encodeToString(bytes1,0);
                        edit.putString("photo", imageStr1);
                        edit.commit();
                        myhead.setImageBitmap(bitmap);
                    } catch (Exception e) {
                        // TODO Auto-generatedcatch block
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    private void floatAnim(View view,int delay){
        List<Animator> animators = new ArrayList<>();
        /*ObjectAnimator translationXAnim = ObjectAnimator.ofFloat(view, "translationX", -6.0f,6.0f,-6.0f);
        translationXAnim.setDuration(1500);
        translationXAnim.setRepeatCount(ValueAnimator.INFINITE);//无限循环
//        translationXAnim.setRepeatMode(ValueAnimator.INFINITE);//
        translationXAnim.start();
        animators.add(translationXAnim);*/
        ObjectAnimator translationYAnim = ObjectAnimator.ofFloat(view, "translationY", -3.0f,3.0f,-3.0f);
        translationYAnim.setDuration(1000);
        translationYAnim.setRepeatCount(ValueAnimator.INFINITE);
//        translationYAnim.setRepeatMode(ValueAnimator.INFINITE);
        translationYAnim.start();
        animators.add(translationYAnim);

        AnimatorSet btnSexAnimatorSet = new AnimatorSet();
        btnSexAnimatorSet.playTogether(animators);
        btnSexAnimatorSet.setStartDelay(delay);
        btnSexAnimatorSet.start();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_my_item1:

                break;
            case R.id.ll_my_item2:

                break;
            case R.id.ll_my_item3:

                break;
            case R.id.ll_my_item5:
                Intent intent5 = new Intent(getActivity(), DingDanActivity.class);
                intent5.putExtra("uid",uid+"");
                startActivity(intent5);
                break;
        }
    }
}
