package com.xingen.movie.movielist;

import com.xingen.commonlibrary.bean.Movie;
import com.xingen.commonlibrary.bean.MovieData;
import com.xingen.commonlibrary.sqlbrite.SQLClient;
import com.xingen.commonlibrary.utils.TransformUtils;
import com.xingen.movie.net.NetClient;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class MovieListPresenter implements MovieListContract.Presenter {
    private MovieListContract.View view;
    private CompositeSubscription compositeSubscription;
    private NetClient remoteDataSource;
    private SQLClient dataLocalDataSource;

    public MovieListPresenter(MovieListContract.View view, NetClient remoteDataSource, SQLClient sqlClient) {
        this.compositeSubscription = new CompositeSubscription();
        this.remoteDataSource = remoteDataSource;
        this.dataLocalDataSource=sqlClient;
        this.view = view;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {
        loadRemoteTask();
    }

    /**
     * 开始加载远程的数据
     */
    private void loadRemoteTask() {
        Subscription subscription = remoteDataSource.movieList(new Subscriber<List<Movie>>() {
            @Override
            public void onCompleted() {
                if (isViewBind()) {
                    view.showToast("获取列表成功");
                }
            }
            @Override
            public void onError(Throwable e) {
                if (isViewBind()) {
                    view.showToast("加载失败");
                }
            }

            @Override
            public void onNext(List<Movie> list) {
                view.loadMovieList(list);
            }
        });
        this.compositeSubscription.add(subscription);
    }

    @Override
    public void unbindView() {
        this.view = null;
        this.compositeSubscription.clear();
    }

    @Override
    public void collectionMovie(final List<Movie> list) {
        Subscription subscription = Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                List<MovieData> movieDataList = new ArrayList<>();
                for (Movie movie : list) {
                    movieDataList.add(TransformUtils.transformMovies(movie));
                }
                int size = dataLocalDataSource.bulkInsert(movieDataList);
                subscriber.onNext(size > 0 ? true : false);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        if (isViewBind()) {
                            String msg = "收藏失败";
                            view.showToast(msg);
                        }
                    }
                    @Override
                    public void onNext(Boolean aBoolean) {
                        if (isViewBind()) {
                            String msg = aBoolean == false ? "收藏失败" : "收藏成功，可在收藏页面查看";
                            view.showToast(msg);
                        }
                    }
                });
        this.compositeSubscription.add(subscription);
    }

    /**
     * 检查View是否被绑定
     *
     * @return
     */
    private boolean isViewBind() {
        return this.view == null ? false : true;
    }
}
