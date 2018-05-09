package com.xingen.commonlibrary.mvp;

/**
 * Created by ${xinGen} on 2018/5/3 0003.
 * 博客：https://blog.csdn.net/hexingen
 */

public interface BaseView<T> {
    /**
     * 设置Presenter
     */
    void setPresenter(T t);
}
