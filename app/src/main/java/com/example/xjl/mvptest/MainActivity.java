package com.example.xjl.mvptest;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xjl.mvptest.mvp.bean.User;
import com.example.xjl.mvptest.mvp.presenter.UserLoginPresenter;
import com.example.xjl.mvptest.mvp.view.IUserLoginView;

public class MainActivity extends AppCompatActivity implements IUserLoginView{

    private EditText et_username;
    private EditText et_password;
    private Button btn_login;
    private Button btn_clear;
    private ProgressDialog progressDialog;
    private UserLoginPresenter userLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userLoginPresenter = new UserLoginPresenter(this);

        initView();
    }

    private void initView() {
        et_username = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_clear = (Button) findViewById(R.id.btn_clear);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.login();
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return et_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public void clearUserName() {
        et_username.setText("");
    }

    @Override
    public void clearPassword() {
        et_password.setText("");
    }

    @Override
    public void showingLoading() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("正在登录......");
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    @Override
    public void toMainActivity(User user) {
        Log.d("user",user.getUsername()+user.getPassword());
    }

    @Override
    public void showFailedError() {
        Toast.makeText(MainActivity.this,"失败",Toast.LENGTH_SHORT).show();
    }
}
