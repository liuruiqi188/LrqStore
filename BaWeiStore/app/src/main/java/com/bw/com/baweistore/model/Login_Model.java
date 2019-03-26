package com.bw.com.baweistore.model;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.LoginJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName Login_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/22 16:04
 **/
public class Login_Model {

    //会掉借口
    public interface OnLoginLisenter{
        void onLisenter(LoginJson.LoginData result,String status);
    }
    private OnLoginLisenter loginLisenter;

    public void setOnLoginLisenter(OnLoginLisenter loginLisenter){
        this.loginLisenter=loginLisenter;
    }



    public void send(String phone, String pwd) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.Login_Url, null, null, ApiService.class);
        final Flowable<LoginJson> login = apiService.getLogin(phone, pwd);
        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<LoginJson>() {
                    @Override
                    public void onNext(LoginJson loginJson) {
                        LoginJson.LoginData result = loginJson.getResult();
                        String status = loginJson.getStatus();

                        //接口回调
                        if (loginLisenter!=null){
                            loginLisenter.onLisenter(result,status);
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
