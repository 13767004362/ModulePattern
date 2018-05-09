package com.xingen.collection.collectionmovie;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.xingen.collection.R;
import com.xingen.commonlibrary.BaseFragment;
import com.xingen.commonlibrary.bean.MovieData;
import com.xingen.commonlibrary.image.ImageLoader;
import com.xingen.commonlibrary.utils.ContextUtils;

import java.util.List;

/**
 * Created by ${xinGen} on 2018/5/9 0009.
 * 博客：https://blog.csdn.net/hexingen
 */

public class CollectionMovieFragment extends BaseFragment implements CollectionMovieConstract.View,View.OnClickListener {
    private CollectionMovieConstract.Presenter presenter;
    private CollectionMovieAdapter adapter;
    private RecyclerView recyclerView;
    public static final String TAG=CollectionMovieFragment.class.getSimpleName();
    public  static CollectionMovieFragment newInstance(){
        return  new CollectionMovieFragment();
    }
    @Override
    public void init(Bundle savedInstanceState) {
        this.presenter.start();
        initView();
    }
    private void initView() {
        this.recyclerView=(RecyclerView) this.rootView.findViewById(R.id.collectionmovie_recyclerView);
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        this.adapter=new CollectionMovieAdapter(new ImageLoader(getActivity(),R.mipmap.ic_launcher));
        this.recyclerView.setAdapter(this.adapter);

        this.rootView.findViewById(R.id.collectionmovie_return_btn).setOnClickListener(this);
    }
    @Override
    public int getLayout() {
        return R.layout.fragment_collectionmovie;
    }
    @Override
    public void onClick(View v) {
        getActivity().finish();
    }

    @Override
    public void setPresenter(CollectionMovieConstract.Presenter presenter) {
        this.presenter=presenter;
    }

    @Override
    public void loadCollectionMovie(List<MovieData> list) {
        this.adapter.upData(list);
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(ContextUtils.getAppContext(),s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        this.presenter.unbindView();
    }
}
