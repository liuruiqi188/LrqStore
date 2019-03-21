package com.bw.com.baweistore.bean;

/**
 * @author liuruiqi
 * @fileName BannerData
 * @package com.bw.com.baweistore.bean
 * @date 2019/3/20 15:07
 **/
public class BannerData {
    private String imageUrl;

    @Override
    public String toString() {
        return "BannerData{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BannerData(String imageUrl) {

        this.imageUrl = imageUrl;
    }
}
