package com.bw.com.baweistore.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.base.BaseActivity;
import com.bw.com.baweistore.bean.LoginJson;
import com.bw.com.baweistore.presenter.Login_Presenter;
import com.bw.com.baweistore.view.Login_View;

import org.greenrobot.eventbus.EventBus;

public class LoginActivity extends BaseActivity implements Login_View {


    private EditText user_phone;
    private TextView user_pass;
    private Button login;
    private TextView regist;
    private Login_Presenter login_presenter;
    private SharedPreferences sp;
    private CheckBox jizhu;

    @Override
    protected int layoutResID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        user_phone = findViewById(R.id.username);
        user_pass = findViewById(R.id.userpass);
        login = findViewById(R.id.login);
        regist = findViewById(R.id.regist);
        jizhu = findViewById(R.id.jizhu);

    }

    @Override
    protected void initData() {

        sp = getSharedPreferences("first", Context.MODE_PRIVATE);

        //记住登陆
//        boolean jizhu = sp.getBoolean("jizhu", false);
//        if (jizhu){
//
//        }

        //实例化P层
        login_presenter = new Login_Presenter(this);

        login_presenter.attachView(this);

        //注册事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistActivity.class));
            }
        });

        //点击登录事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String phone = user_phone.getText().toString();
                String pwd = user_pass.getText().toString();
                //非空判断
                if (TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //联系P层
                login_presenter.send(phone,pwd);

            }
        });

    }

    @Override
    public void login(LoginJson.LoginData result, String status) {
        if (status.equals("0000")){
           EventBus.getDefault().post(result);
            String sessionId = result.getSessionId();
            String userId = result.getUserId();
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("sessionId",sessionId);
            edit.putString("userId",userId);
            edit.putBoolean("jizhu",true);
            edit.commit();
            finish();
        }else {
            Toast.makeText(this, "账号或密码错误，请重新登录！", Toast.LENGTH_SHORT).show();
            return;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        login_presenter.deachView();
    }

}
