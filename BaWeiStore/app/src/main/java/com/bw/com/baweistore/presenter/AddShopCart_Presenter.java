package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.activity.GoodsInfoActivity;
import com.bw.com.baweistore.bean.AddShopCartJson;
import com.bw.com.baweistore.model.AddShopCart_Model;
import com.bw.com.baweistore.view.AddShopCart_View;

/**
 * @author liuruiqi
 * @fileName AddShopCart_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/28 11:42
 **/
public class AddShopCart_Presenter {

    private final AddShopCart_Model addShopCart_model;
    private final AddShopCart_View addShopCart_view;

    public AddShopCart_Presenter(AddShopCart_View view) {
        //实例化M层
        addShopCart_model = new AddShopCart_Model();
        addShopCart_view = view;
    }

    public void send(String s,String sessionId,String userId) {
        //联系
        addShopCart_model.send(s,sessionId,userId);
        addShopCart_model.setOnAddLisenter(new AddShopCart_Model.OnAddLisenter() {
            @Override
            public void onAdd(String message) {
                addShopCart_view.addshopcart(message);
            }
        });
    }
}
