package com.bw.com.baweistore.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.adapter.ThirdAdapter;
import com.bw.com.baweistore.base.BaseActivity;
import com.bw.com.baweistore.bean.ThirdData;
import com.bw.com.baweistore.presenter.GoodsInfo_Presenter;
import com.bw.com.baweistore.presenter.Third_Presenter;
import com.bw.com.baweistore.view.Third_View;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class AssActivity extends BaseActivity implements Third_View {


    private XRecyclerView rlv;
    private Third_Presenter third_presenter;

    @Override
    protected int layoutResID() {
        return R.layout.activity_ass;
    }

    @Override
    protected void initView() {
        rlv = findViewById(R.id.xrlv);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        third_presenter = new Third_Presenter(this);

        third_presenter.send(id);

    }

    @Override
    public void third(List<ThirdData> result) {
        ThirdAdapter thirdAdapter = new ThirdAdapter(this,result);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(AssActivity.this, 2);
        rlv.setLayoutManager(gridLayoutManager);
        rlv.setAdapter(thirdAdapter);


    }
}
