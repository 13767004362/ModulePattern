package com.xingen.collection.collectionmovie;

import com.xingen.commonlibrary.bean.MovieData;
import com.xingen.commonlibrary.mvp.BasePresenter;
import com.xingen.commonlibrary.mvp.BaseView;

import java.util.List;

/**
 * Created by ${xinGen} on 2018/5/9 0009.
 * 博客：https://blog.csdn.net/hexingen
 */

public interface CollectionMovieConstract {
    interface View extends BaseView<Presenter> {
        void loadCollectionMovie(List<MovieData> list);
        void showToast(String s);
    }
    interface Presenter extends BasePresenter {
    }
}