package com.xingen.commonlibrary.net;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class OkHttpUtils {
    /**
     * 自定义配置OkHttpClient
     * @return
     */
    public static OkHttpClient createOkHttpClient(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        //打印一次请求的全部信息
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(loggingInterceptor);
        return builder.build();
    }
}
