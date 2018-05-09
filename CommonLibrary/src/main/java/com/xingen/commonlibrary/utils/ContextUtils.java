package com.xingen.commonlibrary.utils;

import com.xingen.commonlibrary.BaseApplication;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class ContextUtils {
    public static BaseApplication getAppContext(){
        return BaseApplication.getInstance();
    }
}
