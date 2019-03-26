package com.bw.com.baweistore.api;

import com.bw.com.baweistore.bean.AssJson;
import com.bw.com.baweistore.bean.BannerJson;
import com.bw.com.baweistore.bean.ErjiJson;
import com.bw.com.baweistore.bean.GoodsJson;
import com.bw.com.baweistore.bean.LoginJson;
import com.bw.com.baweistore.bean.RegistJson;
import com.bw.com.baweistore.bean.SearchJson;
import com.bw.com.baweistore.bean.ShowJson;
import com.bw.com.baweistore.bean.ThirdJson;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    @GET("commodityList")
    Flowable<ShowJson>getHomeSearchUrl();

    @GET("findCommodityDetailsById")
    Flowable<GoodsJson>getGoodsInfo(@Query("commodityId") String commodityId);

    @POST("login")
    Flowable<LoginJson>getLogin(@Query("phone") String phone, @Query("pwd") String pwd);

    @POST("register")
    Flowable<RegistJson>getRegist(@Query("phone") String phone, @Query("pwd") String pwd);

    @GET("findFirstCategory")
    Flowable<AssJson>getAss();

    @GET("findSecondCategory")
    Flowable<ErjiJson>getErji(@Query("firstCategoryId") String firstCategoryId);

    @GET("findCommodityByCategory")
    Flowable<ThirdJson>getThird(@Query("categoryId") String categoryId, @Query("page") int page, @Query("count") int count );

}
