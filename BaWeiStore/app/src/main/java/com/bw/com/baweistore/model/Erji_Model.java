package com.bw.com.baweistore.model;

import android.util.Log;

import com.bw.com.baweistore.api.Api;
import com.bw.com.baweistore.api.ApiService;
import com.bw.com.baweistore.bean.ErjiData;
import com.bw.com.baweistore.bean.ErjiJson;
import com.bw.com.baweistore.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

/**
 * @author liuruiqi
 * @fileName Erji_Model
 * @package com.bw.com.baweistore.model
 * @date 2019/3/23 15:19
 **/
public class Erji_Model {

    public interface OnErji2Lisenter{
        void onErji(List<ErjiData> result);
    }
    private OnErji2Lisenter erji2Lisenter;

    public void setOnErji2Lisenter(OnErji2Lisenter erji2Lisenter){
        this.erji2Lisenter=erji2Lisenter;
    }


    public void send(String id) {
        ApiService apiService = RetrofitUtils.getInstance().ApiService(Api.Erji_Url, null, null, ApiService.class);
        Flowable<ErjiJson> erji = apiService.getErji(id);
        erji.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<ErjiJson>() {
                    @Override
                    public void onNext(ErjiJson erjiJson) {
                        List<ErjiData> result = erjiJson.getResult();
                        Log.i("rrrrr",result.get(0).getName());
                        if (erji2Lisenter!=null){
                            erji2Lisenter.onErji(result);
                        }
                    }

                    @Override
                    public void onError(Throwable t) {

                        Log.i("ttttt",t.toString());

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
