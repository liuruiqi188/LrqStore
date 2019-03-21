package com.bw.com.baweistore.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.baweistore.R;

import com.bw.com.baweistore.adapter.HomeSearchAdapter1;
import com.bw.com.baweistore.adapter.HomeSearchAdapter2;
import com.bw.com.baweistore.adapter.HomeSearchAdapter3;
import com.bw.com.baweistore.adapter.HomeShowAdapter;
import com.bw.com.baweistore.base.BaseFragment;
import com.bw.com.baweistore.bean.BannerData;
import com.bw.com.baweistore.bean.SearchData;
import com.bw.com.baweistore.bean.ShowData;
import com.bw.com.baweistore.custom.Custom_Search;
import com.bw.com.baweistore.presenter.GoodsInfo_Presenter;
import com.bw.com.baweistore.presenter.HomeBanner_Presenter;
import com.bw.com.baweistore.presenter.HomeSearch_Presenter;
import com.bw.com.baweistore.presenter.HomeShow_Presenter;
import com.bw.com.baweistore.view.HomeBanner_View;
import com.bw.com.baweistore.view.HomeSearch_View;
import com.bw.com.baweistore.view.HomeShow_View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName Circle_Fragment
 * @package com.bw.com.baweistore.fragment
 * @date 2019/3/20 14:02
 **/
public class Home_Fragment extends BaseFragment implements HomeBanner_View ,HomeShow_View,HomeSearch_View {

    private Custom_Search cs;
    private XRecyclerView rlv1;
    private XRecyclerView rlv2;
    private XRecyclerView rlv3;
    private HomeShow_Presenter homeShow_presenter;
    private XBanner banner;
    private HomeBanner_Presenter homeBanner_presenter;
    private XRecyclerView xrlv;
    private HomeSearch_Presenter homeSearch_presenter;
    private TextView title3;
    private TextView title2;
    private TextView title1;


    @Override
    protected int layoutResID() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView(View view) {
        cs = view.findViewById(R.id.custom_search);
        rlv1 = view.findViewById(R.id.rlv1);
        rlv2 = view.findViewById(R.id.rlv2);
        rlv3 = view.findViewById(R.id.rlv3);
        banner = view.findViewById(R.id.ban);
        xrlv = view.findViewById(R.id.xrlv);
        title1 = view.findViewById(R.id.homeshow1_title);
        title2 = view.findViewById(R.id.homeshow2_title);
        title3 = view.findViewById(R.id.homeshow3_title);
    }

    @Override
    protected void initData() {

        //展示格式
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 3);
        rlv1.setLayoutManager(gridLayoutManager1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rlv2.setLayoutManager(layoutManager);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2);
        rlv3.setLayoutManager(gridLayoutManager2);


        //实例化P层
        homeShow_presenter = new HomeShow_Presenter(this);
        homeBanner_presenter = new HomeBanner_Presenter(this);
        homeSearch_presenter = new HomeSearch_Presenter(this);


        //轮播图发送
        homeBanner_presenter.send();
        //展示发送连接
        homeSearch_presenter.send();


        //设置布局格式
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        xrlv.setLayoutManager(gridLayoutManager);


        //接口回调
        cs.setOnSearchLisenter(new Custom_Search.OnSearchLisenter() {
            @Override
            public void onSearch(String goods) {
                banner.setVisibility(View.GONE);
                rlv1.setVisibility(View.GONE);
                rlv2.setVisibility(View.GONE);
                rlv3.setVisibility(View.GONE);
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
        HomeShowAdapter homeShowAdapter = new HomeShowAdapter(getActivity(),result);
       xrlv.setAdapter(homeShowAdapter);

    }

    @Override
    public void homeSearch(ShowData result) {
        //设置展示数据标题
        String name1 = result.getRxxp().getName();
        String name2 = result.getMlss().getName();
        String name3 = result.getPzsh().getName();

        title1.setText(name1);
        title2.setText(name2);
        title3.setText(name3);

        //适配器
        HomeSearchAdapter1 homeSearchAdapter1 = new HomeSearchAdapter1(getActivity(),result);
        rlv1.setAdapter(homeSearchAdapter1);
        HomeSearchAdapter2 homeSearchAdapter2 = new HomeSearchAdapter2(getActivity(),result);
        rlv2.setAdapter(homeSearchAdapter2);
        HomeSearchAdapter3 homeSearchAdapter3 = new HomeSearchAdapter3(getActivity(), result);
        rlv3.setAdapter(homeSearchAdapter3);
    }
}
