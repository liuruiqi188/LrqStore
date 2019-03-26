package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.activity.AssActivity;
import com.bw.com.baweistore.bean.ThirdData;
import com.bw.com.baweistore.model.Third_Model;
import com.bw.com.baweistore.view.Third_View;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName Third_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/23 16:46
 **/
public class Third_Presenter {

    private final Third_Model third_model;
    private final Third_View third_view;

    public Third_Presenter(Third_View view) {
        third_model = new Third_Model();
        third_view = view;
    }

    public void send(String id) {
        third_model.send(id);
        third_model.setOnThirdLisenter(new Third_Model.OnThirdLisenter() {
            @Override
            public void onThird(List<ThirdData> result) {
                third_view.third(result);
            }
        });
    }
}
