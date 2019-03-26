package com.bw.com.baweistore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.bean.AssData;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName PopAdapter
 * @package com.bw.com.baweistore.adapter
 * @date 2019/3/23 14:41
 **/
public class PopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<AssData> result;
    public PopAdapter(Context context, List<AssData> result) {
        this.context=context;
        this.result=result;
    }

    public interface OnErjiLisenter{
        void onErji(String id);
    }
    private OnErjiLisenter erjiLisenter;

    public void setOnErjiLisenter(OnErjiLisenter erjiLisenter){
        this.erjiLisenter=erjiLisenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.popadapter,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;

        AssData assData = result.get(i);
        final String id = assData.getId();
        String name = assData.getName();

        viewHolder1.title.setText(name);
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (erjiLisenter!=null){
                    erjiLisenter.onErji(id);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.popadapter_title);
        }
    }
}
