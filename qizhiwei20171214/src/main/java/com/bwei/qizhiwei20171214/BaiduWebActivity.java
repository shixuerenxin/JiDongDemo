package com.bwei.qizhiwei20171214;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class BaiduWebActivity extends AppCompatActivity {

    private WebView viewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidu_web);
        viewById = (WebView) findViewById(R.id.wv_bai);
        viewById.loadUrl("http://baidu.com");
    }
}
