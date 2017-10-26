package com.example.myapplication;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * 登录过滤器
 * Created by Administrator on 2017/10/26.
 */
@Interceptor(priority = 1,name = "拦截未登录")
public class LoginInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {

        System.out.println("*************************111************");
        if (postcard.getExtra() == 1){
            System.out.println("*************************222************");
            callback.onInterrupt(new RuntimeException("账户未登录"));
            ARouter.getInstance().build("/login_register/loginActivity").navigation();
        }else {
            System.out.println("*************************333************");
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
