package com.xingen.collection.router;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xingen.collection.collectionmovie.CollectionMovieListActivity;
import com.xingen.commonlibrary.router.CollectionMovieProvider;
import com.xingen.commonlibrary.router.RouterPath;

/**
 * Created by ${xinGen} on 2018/5/9 0009.
 * 博客：https://blog.csdn.net/hexingen
 */
@Route(path = RouterPath.PATH_COLLECTION_MOVIE)
public class CollectionMovieProviderImpl implements CollectionMovieProvider{
    @Override
    public void seeCollectionMovie(Context context) {
        CollectionMovieListActivity.openActivity(context);
    }

    @Override
    public void init(Context context) {

    }
}
