package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.SearchData;
import com.bw.com.baweistore.bean.SearchJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName HomeShow_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/20 14:42
 **/
public class HomeShow_Model {

    public interface OnHomeShowLisenter{
        void onShowShow(List<SearchData> result);
    }
    private OnHomeShowLisenter homeShowLisenter;
    public void setOnHomeShowLisenter(OnHomeShowLisenter homeShowLisenter){
        this.homeShowLisenter=homeShowLisenter;
    }


    public void relected(String goods,int page) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.HOME_SHOW_Url, null, null, ApiService.class);
        final Flowable<SearchJson> homeShowUrl = apiService.getHomeShowUrl(goods, page, 5);
        homeShowUrl.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<SearchJson>() {
                    @Override
                    public void onNext(SearchJson searchJson) {
                       // String commodityId = searchJson.getResult().get(0).getCommodityId();
                       // Log.i("iui",commodityId);
                        List<SearchData> result = searchJson.getResult();
                        if (homeShowLisenter!=null){
                            homeShowLisenter.onShowShow(result);
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
