package com.example.xjl.mvptest.mvp.Model;

import com.example.xjl.mvptest.mvp.bean.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xjl on 17-5-25.
 */

public class UserModel implements IUserModel {
    @Override
    public void login(final String username, final String password, final OnLoginListener onLoginListener) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                OkHttpClient okHttpClient=new OkHttpClient();

                FormBody formBody=new FormBody.Builder()
                        .add("telephone",username)
                        .add("pwd",password)
                        .build();

                Request request=new Request.Builder()
                        .post(formBody)
                        .url("url")
                        .build();

                okHttpClient.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        onLoginListener.loginFailed();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        User user=new User();
                        user.setPassword(password);
                        user.setUsername(username);
                        onLoginListener.loginSuccess(user);
                    }
                });

            }
        }).start();
    }
}
