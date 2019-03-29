package com.bw.com.baweistore.bean;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName ShopCartJson
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/27 18:53
 **/
public class ShopCartJson {
    private String status;
    private String message;
    private List<ShopCartData> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ShopCartData> getResult() {
        return result;
    }

    public void setResult(List<ShopCartData> result) {
        this.result = result;
    }
}
