package com.bw.com.baweistore.view;

import com.bw.com.baweistore.bean.LoginJson;

/**
 * @author liuruiqi
 * @fileName Login_View
 * @package com.bw.com.baweistore.view
 * @date 2019/3/22 16:05
 **/
public interface Login_View {
    void login(LoginJson.LoginData result, String status);
}
