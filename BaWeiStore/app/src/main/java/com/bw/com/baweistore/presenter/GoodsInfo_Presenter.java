package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.activity.GoodsInfoActivity;
import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.GoodsJson;
import com.bw.com.baweistore.model.GoodsInfo_Model;
import com.bw.com.baweistore.view.GoodsInfo_View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName GoodsInfo_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/21 15:06
 **/
public class GoodsInfo_Presenter<T> extends BasePresenter<GoodsInfo_View> {

    private final GoodsInfo_Model goodsInfo_model;
    private final GoodsInfo_View goodsInfo_view;
    private Reference reference;

    public GoodsInfo_Presenter(GoodsInfo_View view) {
        //实例化M层
        goodsInfo_model = new GoodsInfo_Model();
        goodsInfo_view = view;
    }
  public void attachView(T t){
      reference = new WeakReference<>(t);
  }
    public void send(String commodityId) {
        goodsInfo_model.send(commodityId);
        goodsInfo_model.setOnGoodInfoLisenter(new GoodsInfo_Model.OnGoodInfoLisenter() {
            @Override
            public void onGoodsInfo(GoodsJson.GoodsInfo result) {
                goodsInfo_view.goodsInfo(result);
            }
        });
    }
    public void deachView(){
        if (reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }
    public void RetrofitOOM(){
        goodsInfo_model.remove();
    }
}
