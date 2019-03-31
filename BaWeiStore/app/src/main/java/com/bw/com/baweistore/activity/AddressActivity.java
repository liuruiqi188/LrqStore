package com.bw.com.baweistore.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.adapter.AddressAdapter;
import com.bw.com.baweistore.bean.AddressData;
import com.bw.com.baweistore.presenter.Address_Presenter;
import com.bw.com.baweistore.view.Address_View;

import java.util.List;

public class AddressActivity extends AppCompatActivity implements Address_View {

    private Button add;
    private RecyclerView rlv;
    private Address_Presenter address_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        //找控件
        add = findViewById(R.id.add_address);
        rlv = findViewById(R.id.address_rlv);

        //布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rlv.setLayoutManager(layoutManager);


        //得到sp
        SharedPreferences sp = getSharedPreferences("first", Context.MODE_PRIVATE);
        String sessionId = sp.getString("sessionId", null);
        String userId = sp.getString("userId", null);

        address_presenter = new Address_Presenter(this);


        //发送
        address_presenter.send(sessionId,userId);


        //添加事件
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转页面
                startActivity(new Intent(AddressActivity.this,AddAddressActivity.class));
            }
        });

    }

    @Override
    public void address(List<AddressData> result) {
        //适配器
        AddressAdapter addressAdapter = new AddressAdapter(this,result);
        rlv.setAdapter(addressAdapter);

    }
}
