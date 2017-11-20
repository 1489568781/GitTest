package com.wxw.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.gittest.R;

/**
 * 自定义控件测试的 Activity
 * Created by Administrator on 2017/11/1.
 */
public class MyViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_activity);
//        CheckView checkView = (CheckView) findViewById(R.id.cv);
//        checkView.setAnimDuration(5);
//        checkView.check();
    }
}
