package com.example.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Administrator on 2017/10/26.
 */
@Route(path = "/hello/HelloActivity",extras = 2)
public class HelloActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_activity);

    }
    public void toMain(View view){


        ARouter.getInstance().build("/chat/ChatActivity")
                .withString("key","hello123")
                .navigation(this, new NavCallback() {
                    @Override
                    public void onArrival(Postcard postcard) {

                        System.out.println("*************************arrival************");
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        super.onInterrupt(postcard);
                        System.out.println("*************************拦截器************");

                    }
                });

    }
}
