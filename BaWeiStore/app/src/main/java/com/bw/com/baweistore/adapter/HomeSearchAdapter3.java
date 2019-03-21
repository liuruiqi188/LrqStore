package com.bw.com.baweistore.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.activity.GoodsInfoActivity;
import com.bw.com.baweistore.bean.ShowData;
import com.bw.com.baweistore.bean.ShowDataaa;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * @author liuruiqi
 * @fileName HomeSearchAdapter3
 * @package com.bw.com.baweistore.adapter
 * @date 2019/3/21 11:56
 **/
public class HomeSearchAdapter3 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ShowData result;
    public HomeSearchAdapter3(Context context, ShowData result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.homesearch_3,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
             ViewHolder viewHolder1= (ViewHolder) viewHolder;
        ShowDataaa showDataaa = result.getPzsh().getCommodityList().get(i);
        String masterPic = showDataaa.getMasterPic();
        String commodityName = showDataaa.getCommodityName();
        String price = showDataaa.getPrice();
       final String commodityId = showDataaa.getCommodityId();

        //绑定
        viewHolder1.name.setText(commodityName);
        viewHolder1.price.setText(price+"元");
        viewHolder1.img.setImageURI(Uri.parse(masterPic));

        //点击跳转详情界面
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
        return result.getPzsh().getCommodityList().size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView price;
        private final TextView name;
        private final SimpleDraweeView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.homesearch_price);
            name = itemView.findViewById(R.id.homesearch_name);
            img = itemView.findViewById(R.id.homesearch_img);
        }
    }
}
