package com.qzw.jidongdemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bwei.jidongdemo.R;
import com.qzw.jidongdemo.acyivity.LoginActivity;

/**
 * author:Created by QiZhiWei on 2018/1/9.
 */

public class Fragment4 extends Fragment {

    private Button butMain;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = View.inflate(getActivity(), R.layout.layout_fragment4, null);
        butMain = (Button)inflate.findViewById(R.id.but_main);
        butMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        return inflate;
    }
}
