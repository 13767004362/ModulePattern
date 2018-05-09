package com.xingen.movie.net;

import com.xingen.commonlibrary.bean.Movie;
import com.xingen.commonlibrary.bean.MovieList;
import com.xingen.commonlibrary.net.RetrofitUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class NetClient {
    private static NetClient instance;
    private DouBanService douBanService;

    static {
        instance=new NetClient();
    }
    private NetClient(){
     this.douBanService= RetrofitUtils.create().create(DouBanService.class);
    }
    public static NetClient getInstance(){
        return instance;
    }
    /*
    * 在Presenter调用，在subscriber订阅者中响应
    */
    public Subscription movieList(Subscriber<List<Movie>> subscriber) {
        String url = "/v2/movie/search";
        Map<String,String> map=new HashMap<>();
        map.put("q","张艺谋");
        Observable<MovieList> observable = this.douBanService.movieList(url, map);
        //floatMap操作符转换
        Observable<List<Movie>> observable1= observable.flatMap(new Func1<MovieList, Observable<List<Movie>>>() {
            @Override
            public Observable<List<Movie>> call(MovieList movieList) {
                return  Observable.just(movieList.getSubjects());
            }
        });
        return observable1
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
