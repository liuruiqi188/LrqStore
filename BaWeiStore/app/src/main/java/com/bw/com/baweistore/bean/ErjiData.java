package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName ErjiData
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/23 15:23
 **/
public class ErjiData {
    private String id;
    private String name;

    public ErjiData(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ErjiData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
