package com.xingen.movie.movielist;

import com.xingen.commonlibrary.bean.Movie;
import com.xingen.commonlibrary.mvp.BasePresenter;
import com.xingen.commonlibrary.mvp.BaseView;

import java.util.List;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public interface MovieListContract {
    interface  Presenter extends BasePresenter {
        /**
         *  收藏的数据
         */
        void collectionMovie(List<Movie> list);
    }
    interface  View extends BaseView<Presenter> {
        /**
         *  加载从数据源中获取的数据
         */
        void loadMovieList(List<Movie> list);
        //最新结果响应在UI上
        void showToast(String s);
    }
}
