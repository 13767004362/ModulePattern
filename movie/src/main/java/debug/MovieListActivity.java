package debug;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xingen.commonlibrary.BaseActivity;
import com.xingen.commonlibrary.router.MovieListProvider;
import com.xingen.commonlibrary.router.RouterPath;
import com.xingen.movie.R;

public class MovieListActivity extends BaseActivity {

      private MovieListProvider provider;

    @Override
    public void init(Bundle savedInstanceState) {
       provider=(MovieListProvider) ARouter.getInstance().build(RouterPath.PATH_MOVIE_LIST).navigation();
       Fragment fragment= provider.createFragment();
       getSupportFragmentManager().beginTransaction().add(R.id.main_movie_list_layout,fragment).commit();
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }
}
