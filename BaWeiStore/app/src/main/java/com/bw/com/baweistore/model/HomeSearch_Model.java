package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.ShowData;
import com.bw.com.baweistore.bean.ShowJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName HomeSearch_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/21 10:40
 **/
public class HomeSearch_Model {

    public interface OnHomeSearchLisenter{
        void onHomeSearch(ShowData result);
    }
    private OnHomeSearchLisenter homeSearchLisenter;

    public void setOnHomeSearchLisenter(OnHomeSearchLisenter homeSearchLisenter){
        this.homeSearchLisenter=homeSearchLisenter;
    }


    public void send() {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.HOME_SEARCH_Url, null, null, ApiService.class);
        Flowable<ShowJson> homeSearchUrl = apiService.getHomeSearchUrl();
        homeSearchUrl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ShowJson>() {
                    @Override
                    public void onNext(ShowJson showJson) {
                      //  String commodityName = showJson.getResult().getPzsh().getCommodityList().get(0).getCommodityName();
                        //Log.i("pop",commodityName);
                        ShowData result = showJson.getResult();
                        if (homeSearchLisenter!=null){
                            homeSearchLisenter.onHomeSearch(result);
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
