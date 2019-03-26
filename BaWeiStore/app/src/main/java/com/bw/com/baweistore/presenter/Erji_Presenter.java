package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.ErjiData;
import com.bw.com.baweistore.fragment.Home_Fragment;
import com.bw.com.baweistore.model.Erji_Model;
import com.bw.com.baweistore.view.Erji_View;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName Erji_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/23 15:18
 **/
public class Erji_Presenter extends BasePresenter<Erji_View> {

    private final Erji_Model erji_model;
    private final Erji_View erji_view;

    public Erji_Presenter(Erji_View view) {
        erji_model = new Erji_Model();
        erji_view = view;

    }

    public void send(String id) {
        erji_model.send(id);
        erji_model.setOnErji2Lisenter(new Erji_Model.OnErji2Lisenter() {
            @Override
            public void onErji(List<ErjiData> result) {
                erji_view.erji(result);
            }
        });
    }
}
