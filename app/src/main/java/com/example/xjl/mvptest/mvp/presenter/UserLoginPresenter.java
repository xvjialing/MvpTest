package com.example.xjl.mvptest.mvp.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.xjl.mvptest.mvp.bean.User;
import com.example.xjl.mvptest.mvp.Model.IUserModel;
import com.example.xjl.mvptest.mvp.Model.OnLoginListener;
import com.example.xjl.mvptest.mvp.Model.UserModel;
import com.example.xjl.mvptest.mvp.view.IUserLoginView;

/**
 * Created by xjl on 17-5-25.
 */

public class UserLoginPresenter {
    private IUserModel iUserBiz;
    private IUserLoginView iUserLoginView;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    User user= (User) msg.obj;
                    iUserLoginView.toMainActivity(user);
                    iUserLoginView.hideLoading();
                    break;
                case 2:
                    iUserLoginView.showFailedError();
                    iUserLoginView.hideLoading();
                    break;
            }
        }
    };

    public UserLoginPresenter(IUserLoginView iUserLoginView) {
        this.iUserLoginView = iUserLoginView;
        this.iUserBiz=new UserModel();
    }

    public void login(){
        iUserLoginView.showingLoading();
        iUserBiz.login(iUserLoginView.getUserName(), iUserLoginView.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(User user) {
                mHandler.obtainMessage(1,user).sendToTarget();
            }

            @Override
            public void loginFailed() {

                mHandler.obtainMessage(2).sendToTarget();
            }
        });
    }

    public void clear(){
        iUserLoginView.clearUserName();
        iUserLoginView.clearPassword();
    }
}
