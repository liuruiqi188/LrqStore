package com.bw.com.baweistore.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
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
    private Button buy;
    private CheckBox ck;
    private TextView price;

    @Override
    protected int layoutResID() {
        return R.layout.shopcart_fragment;
    }

    @Override
    protected void initView(View view) {
        //找控件
        rlv = view.findViewById(R.id.shopcart_rlv);
        buy = view.findViewById(R.id.shopcart_buy);
        ck = view.findViewById(R.id.shopcart_ck);
        price = view.findViewById(R.id.shopcart_price);




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
    public void shopcart(final ShopCartJson shopCartJson) {
        //得到状态码
        String status = shopCartJson.getStatus();
        String message = shopCartJson.getMessage();
        final List<ShopCartData> result = shopCartJson.getResult();
        if (!status.equals("0000")){
            Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
            return;
        }

        //适配器
        final ShopCartAdapter shopCartAdapter = new ShopCartAdapter(getContext(),shopCartJson);
        rlv.setAdapter(shopCartAdapter);

        //适配器会掉借口
        shopCartAdapter.setOnShopcartLisenter(new ShopCartAdapter.OnShopcartLisenter() {
            @Override
            public void onShopcart(List<ShopCartData> result) {
                Log.i("liu",result.toString());
                double sum=0;
                int ischeck=0;
                for (int i=0;i<result.size();i++){
                    boolean ck = result.get(i).isCk();
                    if (ck){
                        //选中就计算价格
                        int count = result.get(i).getCount();
                        double price = result.get(i).getPrice();
                        //总价
                        sum+=((double) count*price);
                        //用着个判断选中的数量
                        ischeck++;
                    }
                    //设置总价
                    price.setText("总价为："+sum);
                }

                //判断ischeck的数量是不是等于集合长度 如果等于把购物车页面的全选框选中
                if (ischeck==result.size()){
                    ck.setChecked(true);
                }else {
                    ck.setChecked(false);
                }



            }
        });




        //点击ck全选
        ck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ck.isChecked();
               // Log.i("fy",checked+"");
                    for (int i=0;i<result.size();i++){
                        result.get(i).setCk(checked);
                }
                shopCartAdapter.notifyDataSetChanged();
            }
        });



    }
}
