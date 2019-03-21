package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName SearchData
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/20 19:41
 **/
public class SearchData {
    private String masterPic;
    private String price;
    private String commodityId;
    private String commodityName;
    private String saleNum;

    @Override
    public String toString() {
        return "SearchData{" +
                "masterPic='" + masterPic + '\'' +
                ", price='" + price + '\'' +
                ", commodityId='" + commodityId + '\'' +
                ", commodityName='" + commodityName + '\'' +
                ", saleNum='" + saleNum + '\'' +
                '}';
    }

    public String getMasterPic() {
        return masterPic;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

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

    public String getSaleNum() {
        return saleNum;
    }

    public void setSaleNum(String saleNum) {
        this.saleNum = saleNum;
    }

    public SearchData(String masterPic, String price, String commodityId, String commodityName, String saleNum) {

        this.masterPic = masterPic;
        this.price = price;
        this.commodityId = commodityId;
        this.commodityName = commodityName;
        this.saleNum = saleNum;
    }
}
