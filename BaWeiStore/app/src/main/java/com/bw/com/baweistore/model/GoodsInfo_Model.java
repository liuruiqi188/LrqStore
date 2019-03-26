package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.GoodsJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName GoodsInfo_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/21 15:08
 **/
public class GoodsInfo_Model {

    public interface OnGoodInfoLisenter{
        void onGoodsInfo(GoodsJson.GoodsInfo result);
    }
    private OnGoodInfoLisenter goodInfoLisenter;

    public void setOnGoodInfoLisenter(OnGoodInfoLisenter goodInfoLisenter){
        this.goodInfoLisenter=goodInfoLisenter;
    }

    CompositeDisposable disposable=new CompositeDisposable();


    public void send(String commodityId) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.GoodsSearch_Url, null, null, ApiService.class);
        Flowable<GoodsJson> goodsInfo = apiService.getGoodsInfo(commodityId);
        DisposableSubscriber<GoodsJson> disposableSubscriber = goodsInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<GoodsJson>() {
                    @Override
                    public void onNext(GoodsJson goodsJson) {
                        GoodsJson.GoodsInfo result = goodsJson.getResult();
                        if (goodInfoLisenter != null) {
                            goodInfoLisenter.onGoodsInfo(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        disposable.add(disposableSubscriber);

    }
    public void remove(){
        boolean disposed = disposable.isDisposed();
        if (!disposed){

            disposable.clear();
            disposable.dispose();
            Log.i("gg","解绑了");

        }
    }
}
