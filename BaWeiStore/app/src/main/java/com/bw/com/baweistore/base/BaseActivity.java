package com.bw.com.baweistore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author liuruiqi
 * @fileName BaseActivity
 * @package com.bw.com.baweistore.base
 * @date 2019/3/20 13:57
 **/
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResID());

        initView();
        initData();
    }





    protected abstract int layoutResID();

    protected abstract void initView();

    protected abstract void initData();
}
