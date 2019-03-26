package com.bw.com.baweistore.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.activity.AssActivity;
import com.bw.com.baweistore.activity.GoodsInfoActivity;
import com.bw.com.baweistore.bean.ThirdData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName ThirdAdapter
 * @package com.bw.com.baweistore.adapter
 * @date 2019/3/23 17:01
 **/
public class ThirdAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {




    Context context;
    List<ThirdData> result;
    public ThirdAdapter(Context context, List<ThirdData> result) {
       this.context=context;
       this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.third,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;

        ThirdData thirdData = result.get(i);
        String masterPic = thirdData.getMasterPic();
        String commodityName = thirdData.getCommodityName();
        String price = thirdData.getPrice();
        final String commodityId = thirdData.getCommodityId();

        viewHolder1.img.setImageURI(Uri.parse(masterPic));
        viewHolder1.name.setText(commodityName);
        viewHolder1.price.setText(price);
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsInfoActivity.class);
                intent.putExtra("commodityId",commodityId);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView img;
        private final TextView name;
        private final TextView price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.third_img);
            name = itemView.findViewById(R.id.third_name);
            price = itemView.findViewById(R.id.third_price);
        }
    }
}
