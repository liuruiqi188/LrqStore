package com.bw.com.baweistore.presenter;

import com.bw.com.baweistore.base.BasePresenter;
import com.bw.com.baweistore.bean.ShowData;
import com.bw.com.baweistore.fragment.Home_Fragment;
import com.bw.com.baweistore.model.HomeSearch_Model;
import com.bw.com.baweistore.view.HomeSearch_View;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * @author liuruiqi
 * @fileName HomeSearch_Presenter
 * @package com.bw.com.baweistore.presenter
 * @date 2019/3/21 10:37
 **/
public class HomeSearch_Presenter<T> extends BasePresenter<HomeSearch_View> {
    private Reference reference;
    private final HomeSearch_Model homeSearch_model;
    private final HomeSearch_View homeSearch_view;

    public HomeSearch_Presenter(HomeSearch_View view) {
        //实例化M层
        homeSearch_model = new HomeSearch_Model();
        homeSearch_view = view;
    }
    public void attachView(T t){
        reference = new WeakReference<>(t);
    }

    public void send() {
        homeSearch_model.send();
        homeSearch_model.setOnHomeSearchLisenter(new HomeSearch_Model.OnHomeSearchLisenter() {
            @Override
            public void onHomeSearch(ShowData result) {
                homeSearch_view.homeSearch(result);
            }
        });
    }
    public void deachView(){
        if (reference.get()!=null){
            reference.clear();
            reference=null;
        }
    }
}
