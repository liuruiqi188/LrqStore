package com.bw.com.baweistore.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
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

    //接口
    public interface OnShopcartLisenter{
        void onShopcart(List<ShopCartData> result);
    }
    private OnShopcartLisenter shopcartLisenter;

    public void setOnShopcartLisenter(OnShopcartLisenter shopcartLisenter){
        this.shopcartLisenter=shopcartLisenter;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.shopcartadapter,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
          final ViewHolder viewHolder1= (ViewHolder) viewHolder;

        final List<ShopCartData> result = shopCartJson.getResult();
        final ShopCartData shopCartData = result.get(i);

        String commodityName = shopCartData.getCommodityName();
        double price = shopCartData.getPrice();
        String pic = shopCartData.getPic();


        //设置
        viewHolder1.img.setImageURI(Uri.parse(pic));
        viewHolder1.name.setText(commodityName);
        viewHolder1.price.setText(price+"");
        //选中状态
        viewHolder1.ck.setChecked(shopCartData.isCk());

        //加减新建方法
        viewHolder1.jiajian.send(result,i,this);


        //加减法回调接口
        viewHolder1.jiajian.setOnJiajianLisenter(new Custom_jiajian.OnJiajianLisenter() {
            @Override
            public void onJiajian() {
                if (shopcartLisenter!=null){
                    shopcartLisenter.onShopcart(result);
                }

            }
        });
        //

        //选中回调接口
        viewHolder1.ck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               result.get(i).setCk(isChecked);
                if (shopcartLisenter!=null){
                    shopcartLisenter.onShopcart(result);
                }
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
        private final CheckBox ck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            jiajian = itemView.findViewById(R.id.jiajian);
            ck = itemView.findViewById(R.id.ck);
        }
    }
}
