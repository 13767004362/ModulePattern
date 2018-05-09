package com.xingen.commonlibrary.utils;

import android.content.ContentValues;
import android.database.Cursor;

import com.xingen.commonlibrary.bean.Movie;
import com.xingen.commonlibrary.bean.MovieData;
import com.xingen.commonlibrary.sqlbrite.db.MovieConstract;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class TransformUtils {
    /**
     *  将Cursor 生成MovieData对象
     * @param cursor
     * @return
     */
    public static MovieData transformMovieData(Cursor cursor) {
        MovieData movieData = new MovieData();
        movieData.setId(cursor.getString(cursor.getColumnIndex(MovieConstract.COLUMN_ID)));
        movieData.setTitle(cursor.getString(cursor.getColumnIndex(MovieConstract.COLUMN_TITLE)));
        movieData.setYear(cursor.getString(cursor.getColumnIndex(MovieConstract.COLUMN_YEAR)));
        movieData.setImages(cursor.getString(cursor.getColumnIndex(MovieConstract.COLUMN_IMAGES)));
        return movieData;
    }
    public static MovieData transformMovies(Movie movie){
        MovieData movieData=new MovieData();
        movieData.setId(movie.getId());
        movieData.setYear(movie.getYear());
        movieData.setTitle(movie.getTitle());
        movieData.setImages(movie.getImages().getLarge());
        return  movieData;
    }
    /**
     * 将Movie生成Cursor.
     * @param movie
     * @return
     */
    public static ContentValues transformMovieData(MovieData movie){
        ContentValues contentValues=new ContentValues();
        contentValues.put(MovieConstract.COLUMN_ID,movie.getId());
        contentValues.put(MovieConstract.COLUMN_TITLE,movie.getTitle());
        contentValues.put(MovieConstract.COLUMN_YEAR,movie.getYear());
        contentValues.put(MovieConstract.COLUMN_IMAGES,movie.getImages());
        return contentValues;
    }
    /**
     * 将Movie生成Cursor.
     * @param movie
     * @return
     */
    public static ContentValues transformMovie(Movie movie){
        ContentValues contentValues=new ContentValues();
        contentValues.put(MovieConstract.COLUMN_ID,movie.getId());
        contentValues.put(MovieConstract.COLUMN_TITLE,movie.getTitle());
        contentValues.put(MovieConstract.COLUMN_YEAR,movie.getYear());
        contentValues.put(MovieConstract.COLUMN_IMAGES,movie.getImages().getLarge());
        return contentValues;
    }
}
