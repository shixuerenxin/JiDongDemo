package com.bwei.jidongdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bwei.jidongdemo.bean.RegisterBean;
import com.bwei.jidongdemo.model.IModel;
import com.bwei.jidongdemo.utils.OKhttpUtils;

import java.util.HashMap;
import java.util.Map;

public class RegActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName;
    private EditText etWord;
    private Button butReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        etName = (EditText) findViewById(R.id.et_name);
        etWord = (EditText) findViewById(R.id.et_word);
        butReg = (Button) findViewById(R.id.but_reg);
        butReg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        //http://120.27.23.105/user/reg
        switch (view.getId()){
            case R.id.but_reg:
                String name = etName.getText().toString();
                String password = etWord.getText().toString();
                Map<String,String> map = new HashMap<>();
                map.put("mobile",name);
                map.put("password",password);
                OKhttpUtils.post("http://120.27.23.105/user/reg", map, RegisterBean.class, new IModel() {
                    @Override
                    public void onSuccess(Object o) {
                        RegisterBean registerBean= (RegisterBean) o;
                        String code = registerBean.getCode();
                        int i = Integer.parseInt(code);
                        if (i==0){
                            Toast.makeText(RegActivity.this,"注册成功"+registerBean.getMsg(),Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(RegActivity.this,"注册失败"+registerBean.getMsg(),Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailed(Exception e) {
                        Toast.makeText(RegActivity.this,"登陆失败",Toast.LENGTH_SHORT).show();
                    }

                });
                break;
        }
    }

}
