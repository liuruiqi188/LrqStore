package com.bw.com.baweistore.bean;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName AddressJson
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/30 15:25
 **/
public class AddressJson {
    private String status;
    private String message;
    private List<AddressData> result;

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

    public List<AddressData> getResult() {
        return result;
    }

    public void setResult(List<AddressData> result) {
        this.result = result;
    }
}
