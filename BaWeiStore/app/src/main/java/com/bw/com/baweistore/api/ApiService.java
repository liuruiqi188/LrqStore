package com.bw.com.baweistore.api;

import com.bw.com.baweistore.bean.BannerJson;
import com.bw.com.baweistore.bean.SearchJson;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author liuruiqi
 * @fileName ApiService
 * @package com.bw.com.baweistore.api
 * @date 2019/3/20 15:01
 **/
public interface ApiService {
    @GET("findCommodityByKeyword")
    Flowable<SearchJson>getHomeShowUrl(@Query("keyword") String keywordw,@Query("page")int page,@Query("count")int count);

    @GET("bannerShow")
    Flowable<BannerJson>getHomeBannerUrl();


}
