package com.bw.com.baweistore.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.baweistore.R;

import com.bw.com.baweistore.activity.AssActivity;
import com.bw.com.baweistore.adapter.ErjiAdapter;
import com.bw.com.baweistore.adapter.HomeSearchAdapter1;
import com.bw.com.baweistore.adapter.HomeSearchAdapter2;
import com.bw.com.baweistore.adapter.HomeSearchAdapter3;
import com.bw.com.baweistore.adapter.HomeShowAdapter;
import com.bw.com.baweistore.adapter.PopAdapter;
import com.bw.com.baweistore.base.BaseFragment;
import com.bw.com.baweistore.bean.AssData;
import com.bw.com.baweistore.bean.BannerData;
import com.bw.com.baweistore.bean.ErjiData;
import com.bw.com.baweistore.bean.SearchData;
import com.bw.com.baweistore.bean.ShowData;
import com.bw.com.baweistore.custom.Custom_Search;
import com.bw.com.baweistore.presenter.Ass_Presenter;
import com.bw.com.baweistore.presenter.Erji_Presenter;
import com.bw.com.baweistore.presenter.GoodsInfo_Presenter;
import com.bw.com.baweistore.presenter.HomeBanner_Presenter;
import com.bw.com.baweistore.presenter.HomeSearch_Presenter;
import com.bw.com.baweistore.presenter.HomeShow_Presenter;
import com.bw.com.baweistore.view.Ass_View;
import com.bw.com.baweistore.view.Erji_View;
import com.bw.com.baweistore.view.HomeBanner_View;
import com.bw.com.baweistore.view.HomeSearch_View;
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
public class Home_Fragment extends BaseFragment implements HomeBanner_View ,HomeShow_View,HomeSearch_View ,Ass_View,Erji_View {

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
    private int page=1;
    private List<SearchData> list;
    private Handler handler=new Handler();
    private String good=null;
    private Ass_Presenter ass_presenter;
    private Erji_Presenter erji_presenter;
    private RecyclerView rlv5;


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
        ass_presenter = new Ass_Presenter(this);
        erji_presenter = new Erji_Presenter(this);


        homeShow_presenter.attachView(this);
        homeBanner_presenter.attachView(this);
        homeSearch_presenter.attachView(this);
        //轮播图发送
        homeBanner_presenter.send();
        //展示发送连接
        homeSearch_presenter.send();



        //设置布局格式
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        xrlv.setLayoutManager(gridLayoutManager);

        //上落下啦
        xrlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page=1;
                        homeShow_presenter.relected(good,page);
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        homeShow_presenter.relected(good,page);
                    }
                },2000);

            }
        });


        //接口回调
        cs.setOnSearchLisenter(new Custom_Search.OnSearchLisenter() {
            @Override
            public void onSearch(String goods) {
                good=goods;
                banner.setVisibility(View.GONE);
                rlv1.setVisibility(View.GONE);
                rlv2.setVisibility(View.GONE);
                rlv3.setVisibility(View.GONE);
                xrlv.setVisibility(View.VISIBLE);
               homeShow_presenter.relected(goods,page);
            }
        });
        //分类菜单
        cs.setOnMenuLisenter(new Custom_Search.OnMenuLisenter() {
            @Override
            public void onMenu() {
                //分类展示
                ass_presenter.send();
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
        if (page==1){
            list = new ArrayList<>();
        }
        list.addAll(result);

        //适配器
        HomeShowAdapter homeShowAdapter = new HomeShowAdapter(getActivity(),list);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        homeShow_presenter.deachView();
        homeBanner_presenter.deachView();
        homeSearch_presenter.deachView();
    }

    @Override
    public void ass(List<AssData> result) {
        //创建pop
        View view=View.inflate(getContext(),R.layout.popwindows,null);
        RecyclerView rlv=view.findViewById(R.id.pop_rlv);
        rlv5 = view.findViewById(R.id.pop_rlv5);
        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlv.setLayoutManager(layoutManager);

        //适配器
        PopAdapter popAdapter = new PopAdapter(getActivity(),result);
        rlv.setAdapter(popAdapter);

        //实例化pop
        PopupWindow popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, 200);

        //点击pop外部 pop消失
        popupWindow.setOutsideTouchable(true);

        //设置背景se
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.RED));

        //给pop设置焦点
        popupWindow.setFocusable(true);

        ImageView menu=cs.findViewById(R.id.menu);
        //poo出现的位置
        popupWindow.showAsDropDown(menu);

        //二级展示
        popAdapter.setOnErjiLisenter(new PopAdapter.OnErjiLisenter() {
            @Override
            public void onErji(String id) {

                erji_presenter.send(id);
            }
        });


    }

    @Override
    public void erji(List<ErjiData> result) {

//布局管理器
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rlv5.setLayoutManager(layoutManager3);
        //适配器
        ErjiAdapter erjiAdapter = new ErjiAdapter(getActivity(),result);
        rlv5.setAdapter(erjiAdapter);

        //得到id
        erjiAdapter.setOnThirdLisenter(new ErjiAdapter.OnThirdLisenter() {
            @Override
            public void onThird(String id) {
                Intent intent = new Intent(getActivity(), AssActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}
