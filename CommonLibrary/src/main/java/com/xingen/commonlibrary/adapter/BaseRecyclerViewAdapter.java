package com.xingen.commonlibrary.adapter;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ${xinGen} on 2018/5/3 0003.
 * 博客：https://blog.csdn.net/hexingen
 */

public abstract class BaseRecyclerViewAdapter<T ,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public abstract  void upData(T t);
}
