package com.xingen.collection.collectionmovie;

import com.xingen.commonlibrary.bean.MovieData;
import com.xingen.commonlibrary.sqlbrite.SQLClient;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ${xinGen} on 2018/5/9 0009.
 * 博客：https://blog.csdn.net/hexingen
 */

public class CollectionMoviePresenter implements CollectionMovieConstract.Presenter {
    private CollectionMovieConstract.View view;
    private SQLClient localDataSource;
    private CompositeSubscription compositeSubscription;
    public CollectionMoviePresenter(CollectionMovieConstract.View view, SQLClient localDataSource) {
        this.compositeSubscription = new CompositeSubscription();
        this.localDataSource = localDataSource;
        this.view = view;
        this.view.setPresenter(this);
    }
    @Override
    public void start() {
        queryCollection();
    }

    @Override
    public void unbindView() {
        view=null;
        this.compositeSubscription.clear();
    }

    /**
     * 查询收藏的电影
     */
    public void queryCollection() {
        Subscription subscription = this.localDataSource.queryAll()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MovieData>>() {
                    @Override
                    public void onCompleted() {
                    }
                    @Override
                    public void onError(Throwable e) {
                        view.showToast("查询异常");
                    }

                    @Override
                    public void onNext(List<MovieData> list) {
                        if (list != null) {
                            if (list.size() > 0) {
                                view.loadCollectionMovie(list);
                            } else {
                                view.showToast("无收藏的电影");
                            }
                        }
                    }
                });
        this.compositeSubscription.add(subscription);
    }


}
