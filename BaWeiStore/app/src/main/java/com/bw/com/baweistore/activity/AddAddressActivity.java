package com.bw.com.baweistore.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bw.com.baweistore.R;
import com.bw.com.baweistore.bean.AddAddressJson;
import com.bw.com.baweistore.presenter.AddAddress_Presenter;
import com.bw.com.baweistore.view.AddAddress_View;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

public class AddAddressActivity extends AppCompatActivity implements CityPickerListener,AddAddress_View {
    private CityPicker cityPicker;
    @BindView(R.id.shoujianren)
    EditText shoujianren;
    @BindView(R.id.phonenumber)
    EditText phonenumber;
    @BindView(R.id.diqu)
    EditText diqu;
    @BindView(R.id.home)
    EditText home;
    @BindView(R.id.youbian)
    EditText youbian;
    @BindView(R.id.commit)
    Button commit;
    @BindView(R.id.choose)
    Button choose;
    private AddAddress_Presenter addAddress_presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);


        cityPicker = new CityPicker(AddAddressActivity.this,this);

        //三级联动
        choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               cityPicker.show();
            }
        });

        //实例化P层
        addAddress_presenter = new AddAddress_Presenter(this);


        //提交点击事件
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到收货人
                String shoujianren = AddAddressActivity.this.shoujianren.getText().toString();
                //地区
                String diqu = AddAddressActivity.this.diqu.getText().toString();
                //地址
                String address = AddAddressActivity.this.home.getText().toString();
                //手机号码
                String phonenumber = AddAddressActivity.this.phonenumber.getText().toString();
                //邮政编码
                String youbian = AddAddressActivity.this.youbian.getText().toString();

                //得到userid和sessionid
                SharedPreferences sp = getSharedPreferences("first", Context.MODE_PRIVATE);
                String sessionId = sp.getString("sessionId", null);
                String userId = sp.getString("userId", null);


                //发送
                addAddress_presenter.send(sessionId,userId,shoujianren,diqu+""+address,phonenumber,youbian);
                finish();

            }
        });

    }

    @Override
    public void getCity(String s) {
        diqu.setText(s);
    }

    @Override
    public void onBackPressed() {
        if (cityPicker.isShow()) {
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void addaddress(AddAddressJson addAddressJson) {
        String status = addAddressJson.getStatus();
        String message = addAddressJson.getMessage();
        Log.i("cycy",message);

            Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
            if (status.equals("0000")){
               // startActivity(new Intent(AddAddressActivity.this,AddressActivity.class));

            }

    }
}
