package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.BannerData;
import com.bw.com.baweistore.bean.BannerJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName HomeBanner_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/20 14:56
 **/
public class HomeBanner_Model {

    public interface OnHomeBannerLisenter{
        void onHomeBanner(List<BannerData> result);
    }
    private OnHomeBannerLisenter homeBannerLisenter;

    public void setOnHomeBannerLisenter(OnHomeBannerLisenter homeBannerLisenter){
        this.homeBannerLisenter=homeBannerLisenter;
    }




    public void send() {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.HOME_BANNER_Url, null, null, ApiService.class);
        Flowable<BannerJson> bannerShow = apiService.getHomeBannerUrl();
        bannerShow.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<BannerJson>() {
                    @Override
                    public void onNext(BannerJson bannerJson) {
                       // String imageUrl = bannerJson.getResult().get(0).getImageUrl();
                       // Log.i("uuu",imageUrl);
                        List<BannerData> result = bannerJson.getResult();
                        //接口回调
                        if (homeBannerLisenter!=null){
                            homeBannerLisenter.onHomeBanner(result);
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
