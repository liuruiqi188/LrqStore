package com.bw.com.baweistore.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.com.baweistore.R;

/**
 * @author liuruiqi
 * @fileName Custom_jiajian
 * @package com.bw.com.baweistore.custom
 * @date 2019/3/28 15:02
 **/
public class Custom_jiajian extends LinearLayout {

    public interface OnJiaLisenter{
        void onJia(int a);
    }
    private OnJiaLisenter jiaLisenter;

    public void setOnJiaLisenter(OnJiaLisenter jiaLisenter){
        this.jiaLisenter=jiaLisenter;
    }

    public interface OnJianLisenter{
        void onJian(int a);
    }
    private OnJianLisenter jianLisenter;

    public void setOnJianLisenter(OnJianLisenter jianLisenter){
        this.jianLisenter=jianLisenter;
    }


    private Button jia;
    private Button jian;
    private EditText num;
    private int i=1;

    public Custom_jiajian(Context context) {
        super(context);
    }

    public Custom_jiajian(Context context,  AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        View view=LinearLayout.inflate(getContext(), R.layout.custom_jiajian,null);
        addView(view);


        //找控件
        jia = view.findViewById(R.id.jia);
        jian = view.findViewById(R.id.jian);
        num = view.findViewById(R.id.et_num);


        //加法点击事件
        jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                num.setText(i+"");
                //接口回调
                if (jiaLisenter!=null){
                    jiaLisenter.onJia(i);
                }
            }
        });

        //减法点击事件
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i<=1){
                    Toast.makeText(getContext(), "物品数量不能小于1", Toast.LENGTH_SHORT).show();
                    return;
                }
                i--;
                num.setText(i+"");
                if (jianLisenter!=null){
                    jianLisenter.onJian(i);
                }


            }
        });

    }

    public Custom_jiajian(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
