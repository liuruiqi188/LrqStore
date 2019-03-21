package com.bw.com.baweistore.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bw.com.baweistore.R;
import com.bw.com.baweistore.adapter.ShowAdapter;
import com.bw.com.baweistore.base.BaseFragment;
import com.bw.com.baweistore.bean.BannerData;
import com.bw.com.baweistore.bean.SearchData;
import com.bw.com.baweistore.custom.Custom_Search;
import com.bw.com.baweistore.presenter.HomeBanner_Presenter;
import com.bw.com.baweistore.presenter.HomeShow_Presenter;
import com.bw.com.baweistore.view.HomeBanner_View;
import com.bw.com.baweistore.view.HomeShow_View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuruiqi
 * @fileName Circle_Fragment
 * @package com.bw.com.baweistore.fragment
 * @date 2019/3/20 14:02
 **/
public class Home_Fragment extends BaseFragment implements HomeBanner_View ,HomeShow_View {

    private Custom_Search cs;
    private XRecyclerView rlv;
    private HomeShow_Presenter homeShow_presenter;
    private XBanner banner;
    private HomeBanner_Presenter homeBanner_presenter;
    private XRecyclerView xrlv;

    @Override
    protected int layoutResID() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {
        cs = view.findViewById(R.id.custom_search);
        rlv = view.findViewById(R.id.rlv);
        banner = view.findViewById(R.id.ban);
        xrlv = view.findViewById(R.id.xrlv);
    }

    @Override
    protected void initData() {
       //实例化P层
        homeShow_presenter = new HomeShow_Presenter(this);
        homeBanner_presenter = new HomeBanner_Presenter(this);

        //轮播图发送
        homeBanner_presenter.send();


        //设置布局格式
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        xrlv.setLayoutManager(gridLayoutManager);


        //接口回调
        cs.setOnSearchLisenter(new Custom_Search.OnSearchLisenter() {
            @Override
            public void onSearch(String goods) {
                banner.setVisibility(View.GONE);
                rlv.setVisibility(View.GONE);
                xrlv.setVisibility(View.VISIBLE);
               homeShow_presenter.relected(goods);
            }
        });

    }


    @Override
    public void HomeBanner(final List<BannerData> result) {

        //xbanner轮播图
        banner.setData(result,null);
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(getContext()).load(result.get(position).getImageUrl()).into((ImageView) view);
                banner.setPageChangeDuration(1000);
            }
        });
    }

    @Override
    public void homeShow(List<SearchData> result) {
        //适配器
        ShowAdapter showAdapter = new ShowAdapter(getActivity(),result);
       xrlv.setAdapter(showAdapter);

    }
}
