package com.bw.com.baweistore.api;

import com.bw.com.baweistore.bean.AddAddressJson;
import com.bw.com.baweistore.bean.AddShopCartData;
import com.bw.com.baweistore.bean.AddShopCartJson;
import com.bw.com.baweistore.bean.AddressJson;
import com.bw.com.baweistore.bean.AssJson;
import com.bw.com.baweistore.bean.BannerJson;
import com.bw.com.baweistore.bean.ErjiJson;
import com.bw.com.baweistore.bean.GoodsJson;
import com.bw.com.baweistore.bean.LoginJson;
import com.bw.com.baweistore.bean.RegistJson;
import com.bw.com.baweistore.bean.SearchJson;
import com.bw.com.baweistore.bean.ShopCartJson;
import com.bw.com.baweistore.bean.ShowJson;
import com.bw.com.baweistore.bean.ThirdJson;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    @GET("findShoppingCart")
    Flowable<ShopCartJson>getShopcart(@Header("userId")String userId,@Header("sessionId")String sessionId);

    @Multipart
    @PUT("syncShoppingCart")
    Flowable<AddShopCartData>getAddshopcart(@Part("data") RequestBody s, @Header("sessionId")String sessionId, @Header("userId")String userId);

    @POST("addReceiveAddress")
    Flowable<AddAddressJson>getAddAddress(@Query("realName") String shoujianren, @Query("phone") String phonenumber, @Query("address") String s, @Query("zipCode") String youbian, @Header("sessionId")String sessionId, @Header("userId") String userId);

    @GET("receiveAddressList")
    Flowable<AddressJson>getAddress(@Header("userId")String userId,@Header("sessionId")String sessionId);
}
