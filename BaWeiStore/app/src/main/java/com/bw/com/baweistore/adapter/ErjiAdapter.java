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
import com.bw.com.baweistore.bean.ErjiData;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName ErjiAdapter
 * @package com.bw.com.baweistore.adapter
 * @date 2019/3/23 15:35
 **/
public class ErjiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ErjiData> result;
    public ErjiAdapter(Context context, List<ErjiData> result) {
        this.context=context;
        this.result=result;
    }
    public interface OnThirdLisenter{
        void onThird(String id);
    }
    private OnThirdLisenter thirdLisenter;

    public void setOnThirdLisenter(OnThirdLisenter thirdLisenter){
        this.thirdLisenter=thirdLisenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.pop_erji,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;

        ErjiData erjiData = result.get(i);
        final String id = erjiData.getId();
        String name = erjiData.getName();

        viewHolder1.title.setText(name);
        viewHolder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (thirdLisenter!=null){
                    thirdLisenter.onThird(id);
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
            title = itemView.findViewById(R.id.pop_erji_title);
        }
    }
}
