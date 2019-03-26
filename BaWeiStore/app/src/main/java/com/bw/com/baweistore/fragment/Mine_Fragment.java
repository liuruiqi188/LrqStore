package com.bw.com.baweistore.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.activity.LoginActivity;
import com.bw.com.baweistore.base.BaseFragment;
import com.bw.com.baweistore.bean.LoginJson;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author liuruiqi
 * @fileName Circle_Fragment
 * @package com.bw.com.baweistore.fragment
 * @date 2019/3/20 14:02
 **/
public class Mine_Fragment extends BaseFragment {


    private TextView name;
    private SimpleDraweeView head_image;

    @Override
    protected int layoutResID() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView(View view) {
        name = view.findViewById(R.id.my_name);
        head_image = view.findViewById(R.id.my_head);

    }

    @Override
    protected void initData() {
        //注册
        EventBus.getDefault().register(this);
        //点击用户姓名点击事件
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转登录界面
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });




    }

    //得到eventbus传值
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(LoginJson.LoginData result){
        String headPic = result.getHeadPic();
        String nickName = result.getNickName();
        //设置值
        name.setText(nickName);
        head_image.setImageURI(Uri.parse(headPic));
    }


}
