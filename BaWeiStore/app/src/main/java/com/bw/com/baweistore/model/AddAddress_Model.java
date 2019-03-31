package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.AddAddressJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName AddAddress_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/29 21:42
 **/
public class AddAddress_Model {

    public interface OnAddAddressLisenter{
        void onAddaddtrss(AddAddressJson addAddressJson);
    }
    private OnAddAddressLisenter addAddressLisenter;

    public void setOnAddAddressLisenter(OnAddAddressLisenter addAddressLisenter){
        this.addAddressLisenter=addAddressLisenter;
    }

    public void send(String sessionId, String userId, String shoujianren, String s, String phonenumber, String youbian) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.AddAddress_Url, null, null, ApiService.class);
        final Flowable<AddAddressJson> addAddress = apiService.getAddAddress(shoujianren, phonenumber, s, youbian, sessionId, userId);
        addAddress.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<AddAddressJson>() {
                    @Override
                    public void onNext(AddAddressJson addAddressJson) {
//                        String message = addAddressJson.getMessage();
//                        Log.i("lrqq",message);
                        if (addAddressLisenter!=null){
                            addAddressLisenter.onAddaddtrss(addAddressJson);
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
