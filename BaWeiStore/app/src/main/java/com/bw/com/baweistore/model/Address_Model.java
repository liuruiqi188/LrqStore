package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.AddressData;
import com.bw.com.baweistore.bean.AddressJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName Address_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/30 15:24
 **/
public class Address_Model {

    public interface OnAddressLisenter{
        void onAddress(List<AddressData> result);
    }
    private OnAddressLisenter addressLisenter;

    public void setOnAddressLisenter(OnAddressLisenter addressLisenter){
        this.addressLisenter=addressLisenter;
    }



    public void send(String sessionId, String userId) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.Address_Url, null, null, ApiService.class);
        Flowable<AddressJson> address = apiService.getAddress(userId, sessionId);
        address.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<AddressJson>() {
                    @Override
                    public void onNext(AddressJson addressJson) {
                        String message = addressJson.getResult().get(0).getAddress();
                        Log.i("cycycy",message);
                        List<AddressData> result = addressJson.getResult();

                        if (addressLisenter!=null){
                            addressLisenter.onAddress(result);
                        }

                    }

                    @Override
                    public void onError(Throwable t) {
                        Log.i("cycycy",t.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
