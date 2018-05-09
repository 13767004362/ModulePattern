package com.xingen.movie.movielist;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xingen.commonlibrary.BaseFragment;
import com.xingen.commonlibrary.adapter.BaseItemDecoration;
import com.xingen.commonlibrary.bean.Movie;
import com.xingen.commonlibrary.image.ImageLoader;
import com.xingen.commonlibrary.utils.ContextUtils;
import com.xingen.commonlibrary.view.ScrollChildSwipeRefreshLayout;
import com.xingen.movie.R;

import java.util.List;

/**
 * Created by ${xinGen} on 2018/5/3 0003.
 * 博客：https://blog.csdn.net/hexingen
 */

public class MovieListFragment extends BaseFragment implements MovieListContract.View , View.OnClickListener, SwipeRefreshLayout.OnRefreshListener{
    private RecyclerView recyclerView;
    private MovieListAdapter adapter;
    private ScrollChildSwipeRefreshLayout swipeRefreshLayout;
    public static MovieListFragment newInstance(){
        return new MovieListFragment();
    }
    @Override
    public void init(Bundle savedInstanceState) {
        this.presenter.start();
        this.recyclerView = (RecyclerView) this.rootView.findViewById(R.id.movielist_recyclerView);
        this.adapter = new MovieListAdapter(new ImageLoader(getContext(),R.mipmap.ic_launcher));
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addItemDecoration(new BaseItemDecoration(getActivity()));
        this.rootView.findViewById(R.id.movielist_collection_btn).setOnClickListener(this);
        swipeRefreshLayout = (ScrollChildSwipeRefreshLayout) rootView.findViewById(R.id.movielist_refreshLayout);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#263238"), Color.parseColor("#ffffff"), Color.parseColor("#455A64"));
        swipeRefreshLayout.setScrollUpChild(recyclerView);
        swipeRefreshLayout.setOnRefreshListener(this);
        //自动加载下拉提示框
        setLoadingIndicator(true);
        //以上代码不响应onRefresh()，需要手动响应onReFresh()。
        this.onRefresh();
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_movielist;
    }

    private MovieListContract.Presenter presenter;

    @Override
    public void setPresenter(MovieListContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除对View的引用
        this.presenter.unbindView();
    }

    @Override
    public void loadMovieList(List<Movie> list) {
        this.adapter.upData(list);
        this.setLoadingIndicator(false);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(ContextUtils.getAppContext(), s, Toast.LENGTH_SHORT).show();
    }
    /**
     * 控制SwipeRefreshLayout的显示与隐藏
     *
     * @param active
     */
    public void setLoadingIndicator(final boolean active) {
        if (swipeRefreshLayout == null) {
            return;
        }
        /**
         *     通过swipeRefreshLayout.post来调用swipeRefreshLayout.setRefreshing（）来实现，一进入页面就自动下拉提示窗。
         */
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                //确保布局加载完成后，调用
                swipeRefreshLayout.setRefreshing(active);
            }
        });
    }
    @Override
    public void onRefresh() {
        swipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                setLoadingIndicator(false);
            }
        }, 1000 * 2);
    }

    @Override
    public void onClick(View v) {
        if (this.adapter.getMoviesCollection().size() == 0) {
            showToast("请勾选中电影");
        } else {
            this.presenter.collectionMovie(this.adapter.getMoviesCollection());
        }
    }
}
