package com.bw.com.baweistore.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.adapter.ShopCartAdapter;
import com.bw.com.baweistore.base.BaseFragment;
import com.bw.com.baweistore.bean.ShopCartData;
import com.bw.com.baweistore.bean.ShopCartJson;
import com.bw.com.baweistore.presenter.Shopcart_Presenter;
import com.bw.com.baweistore.view.ShopCart_View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName Circle_Fragment
 * @package com.bw.com.baweistore.fragment
 * @date 2019/3/20 14:02
 **/
public class ShopCart_Fragment extends BaseFragment implements ShopCart_View {

    private XRecyclerView rlv;
    private Shopcart_Presenter shopcart_presenter;
    private SharedPreferences sp;

    @Override
    protected int layoutResID() {
        return R.layout.shopcart_fragment;
    }

    @Override
    protected void initView(View view) {
        //找控件
        rlv = view.findViewById(R.id.shopcart_rlv);



    }

    @Override
    protected void initData() {
        //实例化
        shopcart_presenter = new Shopcart_Presenter(this);

        sp = getContext().getSharedPreferences("first", Context.MODE_PRIVATE);

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rlv.setLayoutManager(layoutManager);

        //得到传值
        String sessionId = sp.getString("sessionId", null);
        String userId = sp.getString("userId", null);

        //联系
        shopcart_presenter.send(sessionId,userId);

    }

    @Override
    public void shopcart(ShopCartJson shopCartJson) {
        //得到状态码
        String status = shopCartJson.getStatus();
        String message = shopCartJson.getMessage();
        if (!status.equals("0000")){
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            return;
        }

        //适配器
        ShopCartAdapter shopCartAdapter = new ShopCartAdapter(getContext(),shopCartJson);
        rlv.setAdapter(shopCartAdapter);



    }
}
