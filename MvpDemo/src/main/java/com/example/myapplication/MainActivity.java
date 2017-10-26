package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import model.UserInfo;
import presenter.LoginPresenter;
import view.LoginView;
@Route(path = "/main/MainActivity")
public class MainActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new LoginPresenter(this);
        presenter.isLoginSuccess();
    }

    @Override
    public String getUserName() {

        return "admin";
    }

    @Override
    public String getPassWord() {

        return "123";
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void loginSuccess(UserInfo userInfo) {
        Toast.makeText(getApplicationContext(),userInfo.getUserName()+","+userInfo.getPassWord(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail(String msg) {
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
    }
}
