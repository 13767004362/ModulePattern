package com.xingen.collection.collectionmovie;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xingen.collection.R;
import com.xingen.commonlibrary.adapter.BaseRecyclerViewAdapter;
import com.xingen.commonlibrary.bean.MovieData;
import com.xingen.commonlibrary.image.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${xinGen} on 2018/5/9 0009.
 * 博客：https://blog.csdn.net/hexingen
 */

public class CollectionMovieAdapter extends BaseRecyclerViewAdapter<List<MovieData>, CollectionMovieAdapter.ViewHolder> {
    private List<MovieData> list;
    private ImageLoader imageLoader;
    public CollectionMovieAdapter(ImageLoader imageLoader) {
        this.imageLoader=imageLoader;
        this.list = new ArrayList<>();
    }

    @Override
    public void upData(List<MovieData> list) {
        this.list.clear();
        this.list.addAll(list);
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_collectionlist, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieData movieData = list.get(position);
        holder.getTitle().setText(movieData.getTitle());
        imageLoader.loadImage(movieData.getImages(),holder.getNetworkImageView());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView networkImageView;
        private TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            this.networkImageView = (ImageView) itemView.findViewById(R.id.collectionmovie_iv);
            this.title = (TextView) itemView.findViewById(R.id.collectionmovie_title_tv);
        }

        public ImageView getNetworkImageView() {
            return networkImageView;
        }

        public TextView getTitle() {
            return title;
        }
    }
}
