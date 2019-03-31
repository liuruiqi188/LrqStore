package com.bw.com.baweistore.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.activity.AddressActivity;
import com.bw.com.baweistore.bean.AddressData;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName AddressAdapter
 * @package com.bw.com.baweistore.adapter
 * @date 2019/3/30 16:07
 **/
public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<AddressData> result;
    public AddressAdapter(Context context, List<AddressData> result) {
       this.context=context;
       this.result=result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LinearLayout.inflate(context, R.layout.address,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ViewHolder viewHolder1= (ViewHolder) viewHolder;

        AddressData addressData = result.get(i);
        String phone = addressData.getPhone();
        String address = addressData.getAddress();
        String realName = addressData.getRealName();

        viewHolder1.name.setText(realName);
        viewHolder1.dizhi.setText(address);
        viewHolder1.num.setText(phone);

    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView dizhi;
        private final TextView num;
        private final TextView name;
        private final Button delete;
        private final Button update;
        private final CheckBox ck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ck = itemView.findViewById(R.id.address_ck);
            update = itemView.findViewById(R.id.address_update);
            delete = itemView.findViewById(R.id.address_delete);
            name = itemView.findViewById(R.id.address_name);
            num = itemView.findViewById(R.id.address_num);
            dizhi = itemView.findViewById(R.id.address_dizhi);
        }
    }
}
