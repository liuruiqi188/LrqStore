package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.AssData;
import com.bw.com.baweistore.fragment.Home_Fragment;
import com.bw.com.baweistore.model.Ass_Model;
import com.bw.com.baweistore.view.Ass_View;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName Ass_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/23 14:15
 **/
public class Ass_Presenter extends BasePresenter<Ass_View> {

    private final Ass_Model ass_model;
    private final Ass_View ass_view;

    public Ass_Presenter(Ass_View view) {
        ass_model = new Ass_Model();
        ass_view = view;
    }

    public void send() {
        ass_model.send();
        ass_model.setOnAssLisenter(new Ass_Model.OnAssLisenter() {
            @Override
            public void onAss(List<AssData> result) {
                ass_view.ass(result);
            }
        });
    }
}
