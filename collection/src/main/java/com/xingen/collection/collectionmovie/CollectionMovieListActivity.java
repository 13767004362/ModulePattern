package com.xingen.collection.collectionmovie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.xingen.collection.R;
import com.xingen.commonlibrary.BaseActivity;
import com.xingen.commonlibrary.sqlbrite.SQLClient;

public class CollectionMovieListActivity extends BaseActivity {
    @Override
    public void init(Bundle savedInstanceState) {
        CollectionMovieFragment fragment=CollectionMovieFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.collectionmovie_content_layout,fragment,CollectionMovieFragment.TAG).commit();
        CollectionMovieConstract.Presenter presenter=new CollectionMoviePresenter(fragment, SQLClient.getInstance());
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_collectionmovie;
    }
    public static void openActivity(Context context){
        Intent intent=new Intent(context,CollectionMovieListActivity.class);
        context.startActivity(intent);
    }
}
