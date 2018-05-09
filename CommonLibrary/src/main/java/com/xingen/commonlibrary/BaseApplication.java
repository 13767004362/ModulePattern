package com.xingen.commonlibrary;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by Administrator on 2018/4/30 0030.
 */

public class BaseApplication  extends Application {
    private static  BaseApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
        initRouter();

    }



    public static BaseApplication getInstance(){
        return instance;
    }
    private void initRouter(){
        if (BuildConfig.DEBUG) {
            //一定要在ARouter.init之前调用openDebug
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(this);
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
    }
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
