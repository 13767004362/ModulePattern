package com.xingen.commonlibrary.sqlbrite;

import android.database.Cursor;

import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.QueryObservable;
import com.xingen.commonlibrary.BaseApplication;
import com.xingen.commonlibrary.bean.MovieData;
import com.xingen.commonlibrary.sqlbrite.db.LocalDataSource;
import com.xingen.commonlibrary.sqlbrite.db.MovieConstract;
import com.xingen.commonlibrary.utils.TransformUtils;

import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class SQLClient implements LocalDataSource<MovieData>{
    private BriteDatabase briteDatabase;
    private static SQLClient  instance;
    private Func1<Cursor,MovieData> cursorListFunc1;
    private SQLClient(){
        this.briteDatabase=SQLBriteProvider.getInstance(BaseApplication.getInstance()).getBriteDatabase();
        //查询的数据返回Cursor将在这里被回调。
        this.cursorListFunc1=new Func1<Cursor, MovieData>() {
            @Override
            public MovieData call(Cursor cursor) {
                return TransformUtils.transformMovieData(cursor);
            }
        };
    }
    public static  synchronized SQLClient getInstance(){
        if (instance==null){
            instance=new SQLClient();
        }
        return instance;
    }
    /**
     *
     *返回一个Observable对象，可以结合RxJava使用
     */
    @Override
    public Observable< List<MovieData>> queryAll() {
        return queryAction(MovieConstract.SQL_QUERY_MOVIE,null);
    }
    @Override
    public Observable< List<MovieData>> queryAction(String select, String[] selectArg) {
        QueryObservable observable= selectArg==null?this.briteDatabase.createQuery(MovieConstract.TABLE_NAME_MOVI,select):this.briteDatabase.createQuery(MovieConstract.TABLE_NAME_MOVI,select,selectArg);
        return observable.mapToList(this.cursorListFunc1);
    }
    @Override
    public long insert(MovieData movieData) {
        return this.briteDatabase.insert(MovieConstract.TABLE_NAME_MOVI,TransformUtils.transformMovieData(movieData));
    }
    /**
     *批量插入
     */
    @Override
    public int bulkInsert(List<MovieData> list) {
        int size=0;
        //开启事物
        BriteDatabase.Transaction transaction= this.briteDatabase.newTransaction();
        try {
            for (MovieData movieData:list){
                this.briteDatabase.insert(MovieConstract.TABLE_NAME_MOVI,TransformUtils.transformMovieData(movieData));
            }
            transaction.markSuccessful();
            size=list.size();
        }catch (Exception e){
            size=0;
            e.printStackTrace();
        }finally {
            transaction.end();
        }
        return size;
    }
    //项目中没有删，改操作，未编写相关代码
    @Override
    public int update(MovieData movieData, String select, String[] selectArg) {
        return 0;
    }

    @Override
    public int delite(MovieData movieData, String select, String[] selectArg) {
        return 0;
    }

    @Override
    public void deleteAll() {

    }
}
