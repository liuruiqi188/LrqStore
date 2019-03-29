package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.bean.ShopCartJson;
import com.bw.com.baweistore.fragment.ShopCart_Fragment;
import com.bw.com.baweistore.model.Shopcart_Model;
import com.bw.com.baweistore.view.ShopCart_View;

/**
 * @author liuruiqi
 * @fileName Shopcart_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/27 18:56
 **/
public class Shopcart_Presenter {

    private final Shopcart_Model shopcart_model;
    private final ShopCart_View shopCart_view;

    public Shopcart_Presenter(ShopCart_View view) {
        //实例化M
        shopcart_model = new Shopcart_Model();
        shopCart_view = view;
    }

    public void send(String sessionId, String userId) {
        shopcart_model.send(sessionId,userId);
        shopcart_model.setOnShopCartLisenter(new Shopcart_Model.OnShopCartLisenter() {
            @Override
            public void onShopCart(ShopCartJson shopCartJson) {
                shopCart_view.shopcart(shopCartJson);
            }
        });
    }
}
