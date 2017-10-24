package model;

/**
 * Created by Administrator on 2017/10/25.
 */
public class LoginDaoImpl implements LoginDao {
    @Override
    public void isLoginSuccess(UserInfo userInfo, CallBack callBack) {
        try {
            if (userInfo.getUserName().equals("admin")&&userInfo.getPassWord().equals("123")){
                callBack.onSuccess(userInfo);
            }else {
                callBack.onFail("登录失败！！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
