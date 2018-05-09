package com.xingen.movie.net;

import com.xingen.commonlibrary.bean.MovieList;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public interface DouBanService{
    //这里返回一个Observable，用于RxJava结合使用
    @GET("{path}")
    Observable<MovieList> movieList(@Path("path") String path , @QueryMap Map<String,String> options);

}
