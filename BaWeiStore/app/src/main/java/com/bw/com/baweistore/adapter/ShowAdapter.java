package com.bw.com.baweistore.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.bean.SearchData;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName ShowAdapter
 * @package com.bw.com.baweistore.adapter
 * @date 2019/3/20 20:08
 **/
public class ShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SearchData> result;
    public ShowAdapter(Context context, List<SearchData> result) {
        this.context=context;
        this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.homeshow_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;
        //解析数据
        SearchData searchData = result.get(i);
        String masterPic = searchData.getMasterPic();
        String commodityName = searchData.getCommodityName();
        String price = searchData.getPrice();

        //设置值
        viewHolder1.price.setText(price+"元");
        viewHolder1.title.setText(commodityName);
        viewHolder1.img.setImageURI(Uri.parse(masterPic));

    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;
        private final TextView price;
        private final SimpleDraweeView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.homeshow_title);
            price = itemView.findViewById(R.id.homeshow_price);
            img = itemView.findViewById(R.id.homeshow_img);
        }
    }
}
