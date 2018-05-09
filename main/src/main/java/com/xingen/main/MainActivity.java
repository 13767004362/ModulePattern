package com.xingen.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.launcher.ARouter;
import com.xingen.commonlibrary.BaseActivity;
import com.xingen.commonlibrary.router.CollectionMovieProvider;
import com.xingen.commonlibrary.router.MovieListProvider;

/**
 * Created by ${xinGen} on 2018/5/9 0009.
 * 博客：https://blog.csdn.net/hexingen
 */

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Autowired
    private MovieListProvider movieListProvider;

    @Autowired
    private CollectionMovieProvider collectionMovieProvider;

    @Override
    public void init(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        initView();
        Fragment fragment=movieListProvider.createFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.movielist_content_layout,fragment).commit();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        NavigationView navigationView = (NavigationView) this.findViewById(R.id.movielist_navigationview);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.activity_movielist_drawer_collect) {//转调收藏电影的界面.
              collectionMovieProvider.seeCollectionMovie(this);
        }else if (item.getItemId()==R.id.activity_movielist_drawer_movielist){

        }
        //关闭抽屉菜单
        DrawerLayout drawerLayout = (DrawerLayout) this.findViewById(R.id.movielist_drawer);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout) this.findViewById(R.id.movielist_drawer);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {//按Back键，关闭抽屉菜单。
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
