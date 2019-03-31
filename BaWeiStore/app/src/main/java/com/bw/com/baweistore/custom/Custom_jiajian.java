package com.bw.com.baweistore.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.adapter.ShopCartAdapter;
import com.bw.com.baweistore.bean.ShopCartData;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName Custom_jiajian
 * @package com.bw.com.baweistore.custom
 * @date 2019/3/28 15:02
 **/
public class Custom_jiajian extends LinearLayout {
    List<ShopCartData> result;
    int i=0;
    ShopCartAdapter shopCartAdapter;
    int count;

//接口
    public interface OnJiajianLisenter{
        void onJiajian();
}
    private OnJiajianLisenter jiajianLisenter;

    public void setOnJiajianLisenter(OnJiajianLisenter jiajianLisenter){
        this.jiajianLisenter=jiajianLisenter;
    }


    private Button jia;
    private Button jian;
    private EditText num;

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
                String n = num.getText().toString().trim();
                int j = Integer.parseInt(n);
                j++;
                num.setText(j+"");
                //获取当前数量
                ShopCartData shopCartData = result.get(i);
                shopCartData.setCount(j);
                //刷新适配器
                shopCartAdapter.notifyDataSetChanged();
                //回调
                if (jiajianLisenter!=null){
                    jiajianLisenter.onJiajian();
                }

            }
        });

        //减法点击事件
        jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String trim = num.getText().toString().trim();
                int j = Integer.parseInt(trim);
                if (trim.equals("0")){
                    Toast.makeText(getContext(), "不能在减了", Toast.LENGTH_SHORT).show();
                }else {
                    j--;
                    num.setText(""+j);
                    ShopCartData shopCartData = result.get(i);
                    shopCartData.setCount(j);
                    //刷新适配器
                    shopCartAdapter.notifyDataSetChanged();
                    //回调
                    if (jiajianLisenter!=null){
                        jiajianLisenter.onJiajian();
                    }
                }

            }
        });

    }

    public Custom_jiajian(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void send(List<ShopCartData> result, int i, ShopCartAdapter shopCartAdapter) {
        this.result=result;
        this.i=i;
        this.shopCartAdapter=shopCartAdapter;

        this.count=result.get(i).getCount();
        this.num.setText(""+this.count);
    }
}
