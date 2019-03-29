package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.AddShopCartData;
import com.bw.com.baweistore.bean.AddShopCartJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import org.greenrobot.eventbus.Subscribe;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * @author liuruiqi
 * @fileName AddShopCart_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/28 11:44
 **/
public class AddShopCart_Model {

    //接口回调
    public interface OnAddLisenter{
        void onAdd(String message);
    }
    private OnAddLisenter addLisenter;

    public void setOnAddLisenter (OnAddLisenter addLisenter){
        this.addLisenter=addLisenter;
    }



    public void send(String  s,String sessionId,String userId) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.AddShopCart_Url, null, null, ApiService.class);
        Flowable<AddShopCartData> addshopcart = apiService.getAddshopcart(RequestBody.create(MediaType.parse("text/plain"), s), sessionId, userId);
        addshopcart.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<AddShopCartData>() {
                    @Override
                    public void onNext(AddShopCartData addShopCartData) {
                        String message = addShopCartData.getMessage();
//                        Log.i("lxy",message);
                        if (addLisenter!=null){
                            addLisenter.onAdd(message);
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
