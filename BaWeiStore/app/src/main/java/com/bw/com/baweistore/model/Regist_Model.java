package com.bw.com.baweistore.model;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.RegistJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName Regist_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/22 19:48
 **/
public class Regist_Model {

    public interface OnRegistLisenter{
        void onRegist(RegistJson registJson);
    }
    private OnRegistLisenter registLisenter;

    public void setOnRegistLisenter (OnRegistLisenter registLisenter){
        this.registLisenter=registLisenter;
    }

    public void send(String phone, String pwd) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.Regist_Url, null, null, ApiService.class);
        Flowable<RegistJson> regist = apiService.getRegist(phone, pwd);
        regist.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<RegistJson>() {
                    @Override
                    public void onNext(RegistJson registJson) {
                        if (registLisenter!=null){
                            registLisenter.onRegist(registJson);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
