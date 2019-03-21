package com.bw.com.baweistore.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author liuruiqi
 * @fileName BaseFragment
 * @package com.bw.com.baweistore.base
 * @date 2019/3/20 14:00
 **/
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=LinearLayout.inflate(getContext(),layoutResID(),null);
        initView(view);
        initData();
        return view;
    }



    protected abstract int layoutResID();
    protected abstract void initView(View view);
    protected abstract void initData();
}
