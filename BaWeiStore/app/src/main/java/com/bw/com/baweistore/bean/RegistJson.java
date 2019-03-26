package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName RegistJson
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/22 19:54
 **/
public class RegistJson {
    private String message;
    private String status;

    @Override
    public String toString() {
        return "RegistJson{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RegistJson(String message, String status) {

        this.message = message;
        this.status = status;
    }
}
