package com.bw.com.baweistore.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author liuruiqi
 * @fileName RetrofitUtils
 * @package com.bw.com.baweistore.utils
 * @date 2019/3/20 14:04
 **/
public class RetrofitUtils {
    //单例
    private static RetrofitUtils retrofitUtils;
    //私有构造
    private RetrofitUtils() {
    }
    public static RetrofitUtils getInstance(){
        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils==null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }

    //--------------------------------------------------------------------有参数头的Retrofit的网络请求-------------------------------------------
    public static Retrofit getRetrofit(final String url, final String sessionId, final String userId){
        //加入应用拦截器
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("zzz", message);
            }
        });
        //设置模式
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Okhttp
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        //判断userid和sessionid
                        if (!TextUtils.isEmpty(sessionId)&&!TextUtils.isEmpty(userId)){
                            Request request=chain.request().newBuilder()
                                    .addHeader("sessionId",sessionId)
                                    .addHeader("userId",userId)
                                    .build();
                            return chain.proceed(request);
                        }else {
                            Request request=chain.request();
                            return chain.proceed(request);
                        }
                    }
                }).build();
        //Retrofit实例化
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit;
    }


    //--------------------------------------------------------------------------------------------------------------------------------------------
    public <T> T ApiService(String url,String sessionId,String userId,Class<T> service){
        Retrofit retrofit = getRetrofit(url, sessionId, userId);
        T t = retrofit.create(service);
        return t;
    }

}
