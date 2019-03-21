package com.bw.com.baweistore.bean;

import java.util.List;

/**
 * @author liuruiqi
 * @fileName ShowDataa
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/21 10:51
 **/
public class ShowDataa {
    private String id;
    private String name;
    private List<ShowDataaa>commodityList;

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

    public List<ShowDataaa> getCommodityList() {
        return commodityList;
    }

    public void setCommodityList(List<ShowDataaa> commodityList) {
        this.commodityList = commodityList;
    }
}
