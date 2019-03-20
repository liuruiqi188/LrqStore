package com.bw.com.baweistore.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.LinearLayout;

/**
 * @author liuruiqi
 * @fileName BaseFragment
 * @package com.bw.com.baweistore.base
 * @date 2019/3/20 14:00
 **/
public abstract class BaseFragment extends Fragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view=LinearLayout.inflate(getContext(),layoutResID(),null);
        initView(view);
        initData();


    }

    protected abstract int layoutResID();
    protected abstract void initView(View view);
    protected abstract void initData();
}
