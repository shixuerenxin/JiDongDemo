package com.bwei.jidongdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.jidongdemo.bean.LoginBean;
import com.bwei.jidongdemo.model.IModel;
import com.bwei.jidongdemo.utils.OKhttpUtils;

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
        etUserName = (EditText) findViewById(R.id.et_username);
        etPassWord = (EditText) findViewById(R.id.et_password);
        butLogin = (Button) findViewById(R.id.but_login);
        butRegister = (Button) findViewById(R.id.but_register);
        butLogin.setOnClickListener(this);
        butRegister.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_login:
                String username = etUserName.getText().toString().trim();
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
                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailed(Exception e) {
                            Toast.makeText(LoginActivity.this, "" + e, Toast.LENGTH_SHORT).show();
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

}
