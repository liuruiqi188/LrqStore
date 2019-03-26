package com.bw.com.baweistore.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bw.com.baweistore.R;

/**
 * @author liuruiqi
 * @fileName Custom_Search
 * @package com.bw.com.baweistore.custom
 * @date 2019/3/20 14:12
 **/
public class Custom_Search extends LinearLayout {

    //查找接口
    public interface OnSearchLisenter{
        void onSearch(String goods);
    }
    private OnSearchLisenter searchLisenter;
    public void setOnSearchLisenter(OnSearchLisenter searchLisenter){
        this.searchLisenter=searchLisenter;
    }

    //菜单接口
    public interface OnMenuLisenter{
        void onMenu();
    }
    private OnMenuLisenter menuLisenter;

    public void setOnMenuLisenter(OnMenuLisenter menuLisenter){
        this.menuLisenter=menuLisenter;
    }


    private EditText goods1;
    private ImageView menu;
    private ImageView search;

    public Custom_Search(Context context) {
        super(context);
    }

    public Custom_Search(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }



    public Custom_Search(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private void initView() {
        View view=LinearLayout.inflate(getContext(), R.layout.custom_search,null);

        addView(view);

        //找控件
        goods1 = view.findViewById(R.id.et_goods);
        menu = view.findViewById(R.id.menu);
        search = view.findViewById(R.id.search);

        //搜索点击事件
        search.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到输入框内的值
                String goods = goods1.getText().toString();
                //非空判断
                if (TextUtils.isEmpty(goods)){
                    Toast.makeText(getContext(), "输入框内不能输入空值呦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                searchLisenter.onSearch(goods);
            }
        });

        //菜单按钮
        menu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                menuLisenter.onMenu();
            }
        });

    }
}
