package com.example.xjl.mvptest.mvp.view;

import com.example.xjl.mvptest.mvp.bean.User;

/**
 * Created by xjl on 17-5-25.
 */

public interface IUserLoginView {

    String getUserName();

    String getPassword();

    void clearUserName();

    void clearPassword();

    void showingLoading();

    void hideLoading();

    void toMainActivity(User user);

    void showFailedError();
}
