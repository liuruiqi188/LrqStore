package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.BannerData;
import com.bw.com.baweistore.fragment.Home_Fragment;
import com.bw.com.baweistore.model.HomeBanner_Model;
import com.bw.com.baweistore.view.HomeBanner_View;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName HomeBanner_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/20 14:56
 **/
public class HomeBanner_Presenter extends BasePresenter<HomeBanner_View> {

    private final HomeBanner_Model homeBanner_model;
    private final HomeBanner_View homeBanner_view;

    public HomeBanner_Presenter(HomeBanner_View view) {
        //实例化M层
        homeBanner_model = new HomeBanner_Model();
        homeBanner_view = view;
    }

    public void send() {
        //联系M层
        homeBanner_model.send();
        homeBanner_model.setOnHomeBannerLisenter(new HomeBanner_Model.OnHomeBannerLisenter() {
            @Override
            public void onHomeBanner(List<BannerData> result) {
                homeBanner_view.HomeBanner(result);
            }
        });
    }
}
