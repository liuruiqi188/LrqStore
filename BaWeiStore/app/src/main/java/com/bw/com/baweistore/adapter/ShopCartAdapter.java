package com.bw.com.baweistore.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.bean.ShopCartData;
import com.bw.com.baweistore.bean.ShopCartJson;
import com.bw.com.baweistore.custom.Custom_jiajian;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName ShopCartAdapter
 * @package com.bw.com.baweistore.adapter
 * @date 2019/3/27 19:17
 **/
public class ShopCartAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ShopCartJson shopCartJson;
    public ShopCartAdapter(Context context, ShopCartJson shopCartJson) {
      this.context=context;
      this.shopCartJson=shopCartJson;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.shopcartadapter,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
          ViewHolder viewHolder1= (ViewHolder) viewHolder;

        List<ShopCartData> result = shopCartJson.getResult();
        ShopCartData shopCartData = result.get(i);

        String commodityName = shopCartData.getCommodityName();
        String price = shopCartData.getPrice();
        String pic = shopCartData.getPic();

        //设置
        viewHolder1.img.setImageURI(Uri.parse(pic));
        viewHolder1.name.setText(commodityName);
        viewHolder1.price.setText(price);
        viewHolder1.jiajian.setOnJiaLisenter(new Custom_jiajian.OnJiaLisenter() {
            @Override
            public void onJia(int a) {

            }
        });
        viewHolder1.jiajian.setOnJianLisenter(new Custom_jiajian.OnJianLisenter() {
            @Override
            public void onJian(int a) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return shopCartJson.getResult().size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView price;
        private final Custom_jiajian jiajian;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            jiajian = itemView.findViewById(R.id.jiajian);
        }
    }
}
