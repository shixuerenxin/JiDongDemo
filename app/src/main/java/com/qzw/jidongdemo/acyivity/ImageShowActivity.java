package com.qzw.jidongdemo.acyivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwei.jidongdemo.R;
import com.dl7.player.media.IjkPlayerView;

public class ImageShowActivity extends AppCompatActivity {

    private ImageView ivBack;
//    private JCVideoPlayerStandard ivBigShow;
    private TextView addFoundShop;
    private IjkPlayerView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_found);
//        ivBigShow = (JCVideoPlayerStandard) findViewById(R.id.videocontroller1);
        ivBack = (ImageView) findViewById(R.id.iv_back_found);
        addFoundShop = (TextView) findViewById(R.id.add_found_shop);
        videoView = (IjkPlayerView) findViewById(R.id.player_view);

        Intent intent = getIntent();
        addFoundShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ImageShowActivity.this, "购买成功", Toast.LENGTH_SHORT).show();
            }
        });
        String position = intent.getStringExtra("position");
        String name = intent.getStringExtra("name");
        String v = intent.getStringExtra("view");
        //videoView.setVideoURI(Uri.parse(position));
        //videoView.start();
        /*ivBigShow.setUp(
                position,
                JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL,
                name);*/
        Uri mUri = Uri.parse(position);

        videoView.init()
                .setVideoPath(mUri)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                .enableDanmaku()
                .start();

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();
        videoView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        videoView.onPause();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        videoView.onDestroy();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        videoView.configurationChanged(newConfig);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (videoView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onBackPressed() {
        if (videoView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }

}
