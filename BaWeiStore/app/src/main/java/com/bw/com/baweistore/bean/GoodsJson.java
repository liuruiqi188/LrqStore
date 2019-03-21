package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName GoodsJson
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/21 15:33
 **/
public class GoodsJson {
    public GoodsInfo result;

    public GoodsInfo getResult() {
        return result;
    }

    public void setResult(GoodsInfo result) {
        this.result = result;
    }
    public class GoodsInfo{
        private String categoryId;
        private String categoryName;
        private String commentNum;
        private String commodityId;
        private String commodityName;
        private String describe;
        private String details;
        private String picture;
        private String price;
        private String saleNum;
        private String stock;

        @Override
        public String toString() {
            return "GoodsInfo{" +
                    "categoryId='" + categoryId + '\'' +
                    ", categoryName='" + categoryName + '\'' +
                    ", commentNum='" + commentNum + '\'' +
                    ", commodityId='" + commodityId + '\'' +
                    ", commodityName='" + commodityName + '\'' +
                    ", describe='" + describe + '\'' +
                    ", details='" + details + '\'' +
                    ", picture='" + picture + '\'' +
                    ", price='" + price + '\'' +
                    ", saleNum='" + saleNum + '\'' +
                    ", stock='" + stock + '\'' +
                    ", weight='" + weight + '\'' +
                    '}';
        }

        public String getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
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

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getSaleNum() {
            return saleNum;
        }

        public void setSaleNum(String saleNum) {
            this.saleNum = saleNum;
        }

        public String getStock() {
            return stock;
        }

        public void setStock(String stock) {
            this.stock = stock;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public GoodsInfo(String categoryId, String categoryName, String commentNum, String commodityId, String commodityName, String describe, String details, String picture, String price, String saleNum, String stock, String weight) {

            this.categoryId = categoryId;
            this.categoryName = categoryName;
            this.commentNum = commentNum;
            this.commodityId = commodityId;
            this.commodityName = commodityName;
            this.describe = describe;
            this.details = details;
            this.picture = picture;
            this.price = price;
            this.saleNum = saleNum;
            this.stock = stock;
            this.weight = weight;
        }

        private String weight;


    }
}
