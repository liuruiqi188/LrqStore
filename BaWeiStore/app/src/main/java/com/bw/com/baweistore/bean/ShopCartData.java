package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName ShopCartData
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/27 18:54
 **/
public class ShopCartData {
    private String commodityId;
    private String commodityName;
    private String pic;
    private String price;
    private String count;

    public String getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(String commodityId) {
        this.commodityId = commodityId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
