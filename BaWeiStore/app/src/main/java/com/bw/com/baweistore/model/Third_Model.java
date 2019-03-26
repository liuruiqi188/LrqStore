package com.bw.com.baweistore.model;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.ThirdData;
import com.bw.com.baweistore.bean.ThirdJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName Third_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/23 16:47
 **/
public class Third_Model {
    public interface OnThirdLisenter{
        void onThird(List<ThirdData> result);
    }
    private OnThirdLisenter thirdLisenter;

    public void setOnThirdLisenter (OnThirdLisenter thirdLisenter){
        this.thirdLisenter=thirdLisenter;
    }

    public void send(String id) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.Third_Url, null, null, ApiService.class);
        Flowable<ThirdJson> third = apiService.getThird(id, 1, 10);
        third.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ThirdJson>() {
                    @Override
                    public void onNext(ThirdJson thirdJson) {
                        List<ThirdData> result = thirdJson.getResult();
                        if (thirdLisenter!=null){
                            thirdLisenter.onThird(result);
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
