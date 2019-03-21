package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.SearchData;
import com.bw.com.baweistore.fragment.Home_Fragment;
import com.bw.com.baweistore.model.HomeShow_Model;
import com.bw.com.baweistore.view.HomeShow_View;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName HomeShow_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/20 14:41
 **/
public class HomeShow_Presenter extends BasePresenter<HomeShow_View> {

    private final HomeShow_Model homeShow_model;
    private final HomeShow_View homeShow_view;

    public HomeShow_Presenter(HomeShow_View view) {
        //实例化M层
        homeShow_model = new HomeShow_Model();
        homeShow_view = view;
    }

    public void relected(String goods) {
        //联系M层
        homeShow_model.relected(goods);
        homeShow_model.setOnHomeShowLisenter(new HomeShow_Model.OnHomeShowLisenter() {
            @Override
            public void onShowShow(List<SearchData> result) {
                homeShow_view.homeShow(result);
            }
        });
    }
}
