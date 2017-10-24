package model;

/**
 * Created by Administrator on 2017/10/25.
 */
public interface CallBack {
    void onSuccess(UserInfo userInfo);
    void onFail(String msg);

}
