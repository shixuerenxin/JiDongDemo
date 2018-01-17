package com.qzw.jidongdemo.acyivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.bean.LoginBean;
import com.qzw.jidongdemo.model.IModel;
import com.qzw.jidongdemo.utils.OKhttpUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etUserName;
    private EditText etPassWord;
    private Button butLogin;
    private Button butRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (isNetworkAvailable(LoginActivity.this))
        {
            Toast.makeText(getApplicationContext(), "当前有可用网络！", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(), "当前没有可用网络！", Toast.LENGTH_SHORT).show();
        }
        etUserName = (EditText) findViewById(R.id.et_username);
        butLogin = (Button) findViewById(R.id.but_login);
        etPassWord = (EditText) findViewById(R.id.et_password);
        etUserName.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        butRegister = (Button) findViewById(R.id.but_register);
        butLogin.setOnClickListener(this);
        butRegister.setOnClickListener(this);
        etUserName.setFocusable(true);
        etPassWord.setFocusable(true);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_login:
                final String username = etUserName.getText().toString().trim();
                String password = etPassWord.getText().toString().trim();
                //select * from student where sex='男' and age>20;
                if (username==null||password==null||username==""||password==""||username.isEmpty()||password.isEmpty()){
                    Toast.makeText(LoginActivity.this,"请输入用户名/密码",Toast.LENGTH_SHORT).show();
                }else {
                   /* String sql= "select * from User where UserName='"+username+"' AND "+"PassWord="+password;
                    Cursor cursor = db.rawQuery(sql, null);*/
                    Map<String, String> map = new HashMap<>();
                    map.put("mobile", username);
                    map.put("password", password);
                    OKhttpUtils.post("http://120.27.23.105/user/login", map, LoginBean.class, new IModel() {
                        @Override
                        public void onSuccess(Object o) {
                            LoginBean loginBean = (LoginBean) o;
                            Toast.makeText(LoginActivity.this, "" + loginBean.getMsg(), Toast.LENGTH_SHORT).show();
                            String msg = loginBean.getCode();
                            int i = Integer.valueOf(msg);
                            if (i == 0) {
                                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                                int uid = loginBean.getData().getUid();
                                Log.e("--**--", "onSuccess: "+uid );
                                EventBus.getDefault().post(uid);
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                intent.putExtra("uid",uid);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailed(Exception e) {
                            Toast.makeText(LoginActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }


                break;
            case R.id.but_register:
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
        }
    }
    /**
     * 检查当前网络是否可用
     *
     * @param
     * @return
     */
    public boolean isNetworkAvailable(Activity activity)
    {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager == null)
        {
            return false;
        }
        else
        {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();

            if (networkInfo != null && networkInfo.length > 0)
            {
                for (int i = 0; i < networkInfo.length; i++)
                {
                    System.out.println(i + "===状态===" + networkInfo[i].getState());
                    System.out.println(i + "===类型===" + networkInfo[i].getTypeName());
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
