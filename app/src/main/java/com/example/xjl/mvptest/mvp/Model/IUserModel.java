package com.example.xjl.mvptest.mvp.Model;

/**
 * Created by xjl on 17-5-25.
 */

public interface IUserModel {

    void login(String username,String password,OnLoginListener onLoginListener);

}
