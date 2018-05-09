package com.xingen.commonlibrary.mvp;

/**
 * Created by ${xinGen} on 2018/5/3 0003.
 * 博客：https://blog.csdn.net/hexingen
 */

public interface BasePresenter {
    /**
     * 开启任务
     */
    void start();

    /**
     * 解除对View的引用
     */
    void unbindView();
}
