package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.activity.RegistActivity;
import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.RegistJson;
import com.bw.com.baweistore.model.Regist_Model;
import com.bw.com.baweistore.view.Regist_View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName Regist_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/22 19:48
 **/
public class Regist_Presenter<T> extends BasePresenter<Regist_View> {
    private Reference reference;
    private final Regist_Model regist_model;
    private final Regist_View regist_view;

    public Regist_Presenter(Regist_View view) {
        regist_model = new Regist_Model();
        regist_view = view;
    }
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }

    public void send(String phone, String pwd) {
        regist_model.send(phone,pwd);
        regist_model.setOnRegistLisenter(new Regist_Model.OnRegistLisenter() {
            @Override
            public void onRegist(RegistJson registJson) {
                regist_view.regist(registJson);
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
