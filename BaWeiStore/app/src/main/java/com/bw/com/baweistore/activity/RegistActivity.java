package com.bw.com.baweistore.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.base.BaseActivity;
import com.bw.com.baweistore.bean.RegistJson;
import com.bw.com.baweistore.presenter.Regist_Presenter;
import com.bw.com.baweistore.view.Regist_View;

public class RegistActivity extends BaseActivity implements Regist_View {


    private Button regist;
    private TextView back;
    private EditText user_phone;
    private EditText user_pwd;
    private Regist_Presenter regist_presenter;

    @Override
    protected int layoutResID() {
        return R.layout.activity_regist;
    }

    @Override
    protected void initView() {
        user_phone = findViewById(R.id.username);
        user_pwd = findViewById(R.id.userpass);
        regist = findViewById(R.id.regist);
        back = findViewById(R.id.back);

    }

    @Override
    protected void initData() {

        //实例化P
        regist_presenter = new Regist_Presenter(this);

        regist_presenter.attachView(this);

        //返回登录界面
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //注册事件
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String phone = user_phone.getText().toString();
                String pwd = user_pwd.getText().toString();
                //判断格式
                if (phone.length()!=11||pwd.length()<3){
                    Toast.makeText(RegistActivity.this, "账号或密码格式不对，请重新填写！", Toast.LENGTH_SHORT).show();
                }
                //发送数据
                regist_presenter.send(phone,pwd);
            }
        });

    }

    @Override
    public void regist(RegistJson registJson) {
        String status = registJson.getStatus();
        if (status.equals("0000")){
            Toast.makeText(this, registJson.getMessage(), Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, registJson.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        regist_presenter.deachView();
    }
}
