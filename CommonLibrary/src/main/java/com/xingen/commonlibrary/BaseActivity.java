package com.xingen.commonlibrary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2018/4/30 0030.
 */

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        init(savedInstanceState);
    }

    public abstract void init(Bundle savedInstanceState);

    public abstract int getLayoutId();
}
