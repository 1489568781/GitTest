package view;

import model.UserInfo;

/**
 * Created by Administrator on 2017/10/25.
 */
public interface LoginView  {
    String getUserName();
    String getPassWord();
    void showProgress();
    void hideProgress();
    void loginSuccess(UserInfo userInfo);
    void loginFail(String msg);


}
