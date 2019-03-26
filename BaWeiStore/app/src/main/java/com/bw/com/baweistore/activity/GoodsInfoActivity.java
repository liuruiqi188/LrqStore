package com.bw.com.baweistore.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bw.com.baweistore.R;
import com.bw.com.baweistore.bean.GoodsJson;
import com.bw.com.baweistore.presenter.GoodsInfo_Presenter;
import com.bw.com.baweistore.view.GoodsInfo_View;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class GoodsInfoActivity extends AppCompatActivity implements GoodsInfo_View {

    private GoodsInfo_Presenter goodsInfo_presenter;
    private XBanner ban;
    private TextView name;
    private TextView price;
    private WebView web;
    private ImageView back;


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

        Intent intent = getIntent();
        String commodityId = intent.getStringExtra("commodityId");
        goodsInfo_presenter = new GoodsInfo_Presenter(this);
        goodsInfo_presenter.send(commodityId);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void goodsInfo(GoodsJson.GoodsInfo result) {
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
}
