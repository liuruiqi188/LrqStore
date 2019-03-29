package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.ShopCartJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName Shopcart_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/27 19:03
 **/
public class Shopcart_Model {
    public interface OnShopCartLisenter{
        void onShopCart(ShopCartJson shopCartJson);
    }
    private OnShopCartLisenter shopCartLisenter;

    public void setOnShopCartLisenter(OnShopCartLisenter shopCartLisenter){
        this.shopCartLisenter=shopCartLisenter;
    }

    public void send(String sessionId, String userId) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.ShopCart_Url, null, null, ApiService.class);
        Flowable<ShopCartJson> shopcart = apiService.getShopcart(userId,sessionId);
        shopcart.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShopCartJson>() {
                    @Override
                    public void onNext(ShopCartJson shopCartJson) {
                        Log.i("qwe",shopCartJson.getMessage());
                        if (shopCartLisenter!=null){
                            shopCartLisenter.onShopCart(shopCartJson);
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
