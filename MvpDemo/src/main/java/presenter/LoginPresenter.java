package presenter;


import model.CallBack;
import model.LoginDao;
import model.LoginDaoImpl;
import model.UserInfo;
import view.LoginView;

/**
 * Created by Administrator on 2017/10/25.
 */
public class LoginPresenter  {
     private LoginView loginView;
     private LoginDao loginDao;
     private android.os.Handler handler = new android.os.Handler();

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        loginDao = new LoginDaoImpl();
    }
     public void isLoginSuccess(){
         loginView.showProgress();
         loginDao.isLoginSuccess(new UserInfo(loginView.getUserName(), loginView.getPassWord()), new CallBack() {
             @Override
             public void onSuccess(final UserInfo userInfo) {
                 handler.post(new Runnable() {
                     @Override
                     public void run() {
                         loginView.hideProgress();
                         loginView.loginSuccess(userInfo);
                     }
                 });
             }

             @Override
             public void onFail(String msg) {
                 loginView.hideProgress();
                 loginView.loginFail(msg);
             }
         });

    }
}
