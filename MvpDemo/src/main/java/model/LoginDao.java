package model;

/**
 * Created by Administrator on 2017/10/25.
 */
public interface LoginDao {
    void isLoginSuccess(UserInfo userInfo,CallBack callBack);
}
