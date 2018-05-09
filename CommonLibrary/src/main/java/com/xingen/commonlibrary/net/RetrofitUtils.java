package com.xingen.commonlibrary.net;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class RetrofitUtils {
    private  static Retrofit  retrofit;
    private static final String BASE_URL = "https://api.douban.com";
    public static Retrofit create() {
        if (retrofit==null){
            retrofit=create(BASE_URL);
        }
        return  retrofit;
    }
    public static Retrofit create(String baseUrl) {
        return create(baseUrl, OkHttpUtils.createOkHttpClient());
    }

    public static Retrofit create(String baseUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)//传输层
                .addConverterFactory(GsonConverterFactory.create())  //Gson解析
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())   //Rxjava适配器
                .build();
    }
}
