package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.activity.GoodsInfoActivity;
import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.GoodsJson;
import com.bw.com.baweistore.model.GoodsInfo_Model;
import com.bw.com.baweistore.view.GoodsInfo_View;

/**
 * @author liuruiqi
 * @fileName GoodsInfo_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/21 15:06
 **/
public class GoodsInfo_Presenter extends BasePresenter<GoodsInfo_View> {

    private final GoodsInfo_Model goodsInfo_model;
    private final GoodsInfo_View goodsInfo_view;

    public GoodsInfo_Presenter(GoodsInfo_View view) {
        //实例化M层
        goodsInfo_model = new GoodsInfo_Model();
        goodsInfo_view = view;
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
}
