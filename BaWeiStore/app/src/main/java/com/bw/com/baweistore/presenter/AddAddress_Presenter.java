package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.activity.AddAddressActivity;
import com.bw.com.baweistore.bean.AddAddressJson;
import com.bw.com.baweistore.model.AddAddress_Model;
import com.bw.com.baweistore.view.AddAddress_View;

/**
 * @author liuruiqi
 * @fileName AddAddress_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/29 21:38
 **/
public class AddAddress_Presenter {

    private final AddAddress_Model addAddress_model;
    private final AddAddress_View addAddress_view;

    public AddAddress_Presenter(AddAddress_View view) {
        addAddress_model = new AddAddress_Model();
        addAddress_view = view;
    }

    public void send(String sessionId, String userId, String shoujianren, String s, String phonenumber, String youbian) {
        addAddress_model.send( sessionId,  userId,  shoujianren,  s,  phonenumber,  youbian);
        addAddress_model.setOnAddAddressLisenter(new AddAddress_Model.OnAddAddressLisenter() {
            @Override
            public void onAddaddtrss(AddAddressJson addAddressJson) {
                addAddress_view.addaddress(addAddressJson);
            }
        });
    }
}
