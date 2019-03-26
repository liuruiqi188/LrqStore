package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.activity.LoginActivity;
import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.LoginJson;
import com.bw.com.baweistore.model.Login_Model;
import com.bw.com.baweistore.view.Login_View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName Login_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/22 16:04
 **/
public class Login_Presenter<T> extends BasePresenter<Login_View> {
    private Reference reference;
    private final Login_Model login_model;
    private final Login_View login_view;

    public Login_Presenter(Login_View view) {
        //实例化M层
        login_model = new Login_Model();
        login_view = view;
    }
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }

    public void send(String phone, String pwd) {
        login_model.send(phone,pwd);
        login_model.setOnLoginLisenter(new Login_Model.OnLoginLisenter() {
            @Override
            public void onLisenter(LoginJson.LoginData result, String status) {
                login_view.login(result,status);
            }
        });
    }
    public void deachView(){
        if (reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }
}
