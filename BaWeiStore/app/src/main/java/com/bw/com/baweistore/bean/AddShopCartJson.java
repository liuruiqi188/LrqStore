package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName AddShopCartJson
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/28 11:16
 **/
public class AddShopCartJson {
    private String commodityId;
    private int count;

    @Override
    public String toString() {
        return "AddShopCartJson{" +
                "commodityId='" + commodityId + '\'' +
                ", count=" + count +
                '}';
    }

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public AddShopCartJson(String commodityId, int count) {

        this.commodityId = commodityId;
        this.count = count;
    }
}
