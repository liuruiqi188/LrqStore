package com.bw.com.baweistore.model;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.AssData;
import com.bw.com.baweistore.bean.AssJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName Ass_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/23 14:16
 **/
public class Ass_Model {
    public interface OnAssLisenter{
        void onAss(List<AssData> result);
    }
    private OnAssLisenter assLisenter;

    public void setOnAssLisenter(OnAssLisenter assLisenter){
        this.assLisenter=assLisenter;
    }


    public void send() {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.Ass_Url, null, null, ApiService.class);
        Flowable<AssJson> ass = apiService.getAss();
        ass.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<AssJson>() {
                    @Override
                    public void onNext(AssJson assJson) {
                        List<AssData> result = assJson.getResult();
                        if (assLisenter!=null){
                            assLisenter.onAss(result);
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
