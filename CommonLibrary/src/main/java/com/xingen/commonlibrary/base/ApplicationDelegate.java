package com.xingen.commonlibrary.base;

/**
 * Created by xinGen on 2018/5/3 0003.
 *
 * 一个组件的代理类 ：与Application的生命周期捆绑
 */

public interface ApplicationDelegate {
    void onCreate();
    void  onTerminate();
    void onLowMemory();
    void  onTrimMemory(int level);
}
