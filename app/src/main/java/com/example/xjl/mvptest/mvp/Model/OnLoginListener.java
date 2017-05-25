package com.example.xjl.mvptest.mvp.Model;

import com.example.xjl.mvptest.mvp.bean.User;

/**
 * Created by xjl on 17-5-25.
 */

public interface OnLoginListener {
    //登录成功
    void loginSuccess(User user);

    //登录失败
    void loginFailed();
}
