package com.xingen.movie.router;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xingen.commonlibrary.router.MovieListProvider;
import com.xingen.commonlibrary.router.RouterPath;
import com.xingen.commonlibrary.sqlbrite.SQLClient;
import com.xingen.movie.movielist.MovieListFragment;
import com.xingen.movie.movielist.MovieListPresenter;
import com.xingen.movie.net.NetClient;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */
@Route(path = RouterPath.PATH_MOVIE_LIST)
public class MovieListProviderImpl implements MovieListProvider {

    @Override
    public Fragment createFragment() {
        MovieListFragment fragment=MovieListFragment.newInstance();
        MovieListPresenter presenter=new MovieListPresenter(fragment, NetClient.getInstance(), SQLClient.getInstance());
        return fragment;
    }

    @Override
    public void init(Context context) {

    }
}
