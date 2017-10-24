package com.example.administrator.gittest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Administrator on 2017/8/4.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        final LauncherView launcherView = (LauncherView) findViewById(R.id.load_view);
//        new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//
//                }
//            },500);

        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcherView.start();
            }
        });

    }
}
