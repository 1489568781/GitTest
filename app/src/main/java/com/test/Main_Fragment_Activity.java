package com.test;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.gittest.R;

/**
 * Created by Administrator on 2017/11/21.
 */
public class Main_Fragment_Activity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_activity);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        RightFragment rightFragment = (RightFragment) getFragmentManager().findFragmentById(R.id.right_fragment);

        TextView textView = (TextView) rightFragment.getView().findViewById(R.id.tv_right);
        textView.setText("改变了！！！！！");
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button:
                AnotherRightFragment fragment = new AnotherRightFragment();
                FragmentManager manager = getFragmentManager();//获取fragment管理者
                FragmentTransaction transaction = manager.beginTransaction();//开启一个事务
                transaction.replace(R.id.right_layout, fragment);//替换布局（动态添加碎片）
                transaction.addToBackStack(null);//把事务放进回退栈中（点击返回键可以返回到上一个页面状态）
                transaction.commit();//提交事务
                break;

            default:
                break;
        }
    }
}
