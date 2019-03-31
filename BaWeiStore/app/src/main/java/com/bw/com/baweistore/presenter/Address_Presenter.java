package com.bw.com.baweistore.presenter;

import android.util.Log;

import com.bw.com.baweistore.activity.AddressActivity;
import com.bw.com.baweistore.bean.AddressData;
import com.bw.com.baweistore.model.Address_Model;
import com.bw.com.baweistore.view.Address_View;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName Address_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/30 15:21
 **/
public class Address_Presenter {

    private final Address_Model address_model;
    private final Address_View address_view;

    public Address_Presenter(Address_View view) {
        address_model = new Address_Model();
        address_view = view;

    }

    public void send(String sessionId, String userId) {
        Log.i("cy1",sessionId);
        address_model.send(sessionId,userId);
        address_model.setOnAddressLisenter(new Address_Model.OnAddressLisenter() {
            @Override
            public void onAddress(List<AddressData> result) {
                address_view.address(result);
            }
        });
    }
}
