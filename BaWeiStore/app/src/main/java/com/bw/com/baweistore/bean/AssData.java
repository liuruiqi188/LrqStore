package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName AssData
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/23 14:21
 **/
public class AssData {
    private String id;
    private String name;

    @Override
    public String toString() {
        return "AssData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
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

    public AssData(String id, String name) {

        this.id = id;
        this.name = name;
    }
}
