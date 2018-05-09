package com.xingen.commonlibrary.router;

import android.support.v4.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

/**
 * Created by ${xinGen} on 2018/5/3 0003.
 * 博客：https://blog.csdn.net/hexingen
 */

public interface MovieListProvider extends IProvider {
    Fragment createFragment();
}
