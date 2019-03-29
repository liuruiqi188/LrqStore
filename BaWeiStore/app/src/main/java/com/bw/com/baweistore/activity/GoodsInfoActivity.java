package com.bw.com.baweistore.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bw.com.baweistore.R;
import com.bw.com.baweistore.bean.AddShopCartJson;
import com.bw.com.baweistore.bean.GoodsJson;
import com.bw.com.baweistore.bean.ShopCartData;
import com.bw.com.baweistore.bean.ShopCartJson;
import com.bw.com.baweistore.presenter.AddShopCart_Presenter;
import com.bw.com.baweistore.presenter.GoodsInfo_Presenter;
import com.bw.com.baweistore.presenter.Shopcart_Presenter;
import com.bw.com.baweistore.view.AddShopCart_View;
import com.bw.com.baweistore.view.GoodsInfo_View;
import com.bw.com.baweistore.view.ShopCart_View;
import com.stx.xhb.xbanner.XBanner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsInfoActivity extends AppCompatActivity implements GoodsInfo_View,AddShopCart_View ,ShopCart_View{

    private GoodsInfo_Presenter goodsInfo_presenter;
    private XBanner ban;
    private TextView name;
    private TextView price;
    private WebView web;
    private ImageView back;
    private ImageView buy;
    private AddShopCart_Presenter addShopCart_presenter;
    private Shopcart_Presenter shopcart_presenter;
    private String id;
    private String sessionId;
    private String userId;
    private JSONObject jsonObject1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        //找控件
        ban = findViewById(R.id.goodsinfo_ban);
        name = findViewById(R.id.goodsinfo_name);
        price = findViewById(R.id.goodsinfo_price);
        web = findViewById(R.id.web);
        back= findViewById(R.id.back);
        buy = findViewById(R.id.goodsinfo_buy);

        Intent intent = getIntent();
        final String commodityId = intent.getStringExtra("commodityId");
        goodsInfo_presenter = new GoodsInfo_Presenter(this);
        goodsInfo_presenter.send(commodityId);

        //实例化P层
        addShopCart_presenter = new AddShopCart_Presenter(this);
        //实例化购物车fragment 在展示一边购物车
        shopcart_presenter = new Shopcart_Presenter(this);

        //返回点击事件
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //加入购物车事件
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在查询一遍购物车
                SharedPreferences sp = getSharedPreferences("first", Context.MODE_PRIVATE);
                sessionId = sp.getString("sessionId", null);
                userId = sp.getString("userId", null);

                shopcart_presenter.send(sessionId, userId);
                //原生
                JSONArray jsonArray = new JSONArray();
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("commodityId",commodityId);
                    jsonObject.put("count",1);
                    jsonArray.put(jsonObject);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                String s = jsonArray.toString();
//                SharedPreferences sp = getSharedPreferences("first", Context.MODE_PRIVATE);
//                String sessionId = sp.getString("sessionId", null);
//                String userId = sp.getString("userId", null);
                //判断是否登录
                if (!TextUtils.isEmpty(sessionId)||!TextUtils.isEmpty(userId)){
                    //发送联系
                    addShopCart_presenter.send(s, sessionId, userId);
                }else {
                    Toast.makeText(GoodsInfoActivity.this, "请先登录您的账号，再添加物品！", Toast.LENGTH_SHORT).show();
                    return;
                }



            }
        });

    }

    @Override
    public void goodsInfo(GoodsJson.GoodsInfo result) {
        id = result.getCommodityId();
        //取出照片
        final List<String> list=new ArrayList<>();
        String[] split = result.getPicture().split("\\,");
        for (int i=0;i<split.length;i++){
            list.add(split[i]);
        }
        list.add(result.getPicture());
        //轮播图
        ban.setData(list,null);
        ban.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(GoodsInfoActivity.this).load(list.get(position)).into((ImageView) view);
                banner.setPageChangeDuration(1000);
            }
        });
        //获取价钱和名称
        String commodityName = result.getCommodityName();
        String price1 = result.getPrice();
        //设置文本
        name.setText(commodityName);
        price.setText("¥"+price1);
         //设置webView----------------------------------------------------------------------------
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);//支持JS
        String js = "<script type=\"text/javascript\">" +
                "var imgs = document.getElementsByTagName('img');" + // 找到img标签
                "for(var i = 0; i<imgs.length; i++){" +  // 逐个改变
                "imgs[i].style.width = '100%';" +  // 宽度改为100%
                "imgs[i].style.height = 'auto';" +
                "}" +
                "</script>";
        web.loadDataWithBaseURL(null, result.getDetails() + js, "text/html", "utf-8", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Retrofit内存泄漏
        goodsInfo_presenter.RetrofitOOM();
    }

    @Override
    public void addshopcart(String message) {
        //吐司
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void shopcart(ShopCartJson shopCartJson) {

        if (TextUtils.isEmpty(sessionId)||TextUtils.isEmpty(userId)){
            Toast.makeText(this, "请先登录！", Toast.LENGTH_SHORT).show();
            return;
        }



       //先判断shopcartjson里有没有数据
        List<ShopCartData> result = shopCartJson.getResult();
        //新建原生
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        //判断是否为空
        if (result.size()!=0){
            for (int i=0;i<result.size();i++){
                ShopCartData shopCartData = result.get(i);
                String commodityId = shopCartData.getCommodityId();
                String count = shopCartData.getCount();
                jsonObject1 = new JSONObject();
                try {
                    jsonObject1.put("commodityId",commodityId);
                    jsonObject1.put("count",count);
                    jsonArray.put(jsonObject1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            try {
                jsonObject.put("commodityId",id);
                jsonObject.put("count",1);
                jsonArray.put(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //添加
            addShopCart_presenter.send(jsonArray.toString(),sessionId,userId);
        }else {
            try {
                jsonObject1.put("commodityId",id);
                jsonObject1.put("count",1);
                jsonArray.put(jsonObject1);
                addShopCart_presenter.send(jsonArray.toString(),sessionId,userId);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
