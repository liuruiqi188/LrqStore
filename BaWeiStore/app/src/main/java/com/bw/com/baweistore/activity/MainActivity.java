package com.bw.com.baweistore.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.base.BaseActivity;
import com.bw.com.baweistore.fragment.Circle_Fragment;
import com.bw.com.baweistore.fragment.Home_Fragment;
import com.bw.com.baweistore.fragment.Mine_Fragment;
import com.bw.com.baweistore.fragment.Order_Fragment;
import com.bw.com.baweistore.fragment.ShopCart_Fragment;

public class MainActivity extends BaseActivity {

    private FrameLayout fl;
    private RadioGroup rg;
    private Mine_Fragment mine_fragment;
    private Order_Fragment order_fragment;
    private ShopCart_Fragment shopcart_fragment;
    private Circle_Fragment circle_fragment;
    private Home_Fragment home_fragment;
    private FragmentManager manager;

    @Override
    protected int layoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fl = findViewById(R.id.fl);
        rg = findViewById(R.id.rg);
    }

    @Override
    protected void initData() {
        //得到管理
        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        //实例化fragment
        mine_fragment=new Mine_Fragment();
        order_fragment=new Order_Fragment();
        shopcart_fragment=new ShopCart_Fragment();
        circle_fragment=new Circle_Fragment();
        home_fragment=new Home_Fragment();
        transaction.add(R.id.fl,home_fragment);
        transaction.add(R.id.fl,circle_fragment);
        transaction.add(R.id.fl,shopcart_fragment);
        transaction.add(R.id.fl,order_fragment);
        transaction.add(R.id.fl,mine_fragment);
        //默认第一个radiobutton是点钟状态
        rg.check(R.id.rb1);

        //默认显示第一个界面
        transaction.show(home_fragment).hide(circle_fragment).hide(shopcart_fragment).hide(order_fragment).hide(mine_fragment);
        transaction.commit();

        //判断隐藏和显示
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //在得到一下管理者
                FragmentTransaction transaction1 = manager.beginTransaction();
                switch (checkedId){
                    case R.id.rb1:
                        transaction1.show(home_fragment).hide(circle_fragment).hide(shopcart_fragment).hide(order_fragment).hide(mine_fragment).commit();
                        break;
                    case R.id.rb2:
                        transaction1.show(circle_fragment).hide(home_fragment).hide(shopcart_fragment).hide(order_fragment).hide(mine_fragment).commit();
                        break;
                    case R.id.rb3:
                        transaction1.show(shopcart_fragment).hide(circle_fragment).hide(home_fragment).hide(order_fragment).hide(mine_fragment).commit();
                        break;
                    case R.id.rb4:
                        transaction1.show(order_fragment).hide(circle_fragment).hide(shopcart_fragment).hide(home_fragment).hide(mine_fragment).commit();
                        break;
                    case R.id.rb5:
                        transaction1.show(mine_fragment).hide(circle_fragment).hide(shopcart_fragment).hide(order_fragment).hide(home_fragment).commit();
                        break;
                }
            }
        });

    }
}
