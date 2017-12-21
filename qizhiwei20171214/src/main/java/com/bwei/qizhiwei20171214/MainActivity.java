package com.bwei.qizhiwei20171214;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button butJs;
    private WebView wvJava;
    private Button butBai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butJs = (Button) findViewById(R.id.but_js);
        wvJava = (WebView) findViewById(R.id.wv_java);
        butBai = (Button) findViewById(R.id.but_bai);
        butBai.setOnClickListener(this);
        butJs.setOnClickListener(this);
        // 启用javascript
        wvJava.getSettings().setJavaScriptEnabled(true);
        /** 为webview添加注册js 接口回调监听.
         *
         * 参数一: 接口对象
         * 参数二: 接口别名(别名让js代码使用)
         */

        wvJava.addJavascriptInterface(this, "android");
        //file:///android_asset/XX.html
        wvJava.loadUrl("file:///android_asset/js_android.html");


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.but_bai:
                Intent intent = new Intent(MainActivity.this, BaiduWebActivity.class);
                startActivity(intent);
                break;
            case R.id.but_js:

                break;
        }

    }
    @android.webkit.JavascriptInterface
    public void actionFromJs() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "js调用了Android函数", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
