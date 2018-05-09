package com.xingen.commonlibrary.image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.BitmapRequestBuilder;
import com.bumptech.glide.BitmapTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.ModelCache;
import com.bumptech.glide.load.model.stream.BaseGlideUrlLoader;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ${xinGen} on 2018/5/4 0004.
 * 博客：https://blog.csdn.net/hexingen
 */

public class ImageLoader {
    private final CenterCrop mCenterCrop;
    private final BitmapTypeRequest<String> glideModelRequest;
    private static final ModelCache<String ,GlideUrl> urlCache=new ModelCache<>(150);
    private int mPlaceHolderResId=-1;

    public ImageLoader(Context context){
        /**
         * 转换网址的操作类
         */
        CustomVariableWithImageLoader variableWithImageLoader=new CustomVariableWithImageLoader(context);
        /*
         * 总是将资源加载成一个Bitmap
         */
        this.glideModelRequest= Glide.with(context).using(variableWithImageLoader).from(String.class).asBitmap();

        BitmapPool bitmapPool=Glide.get(context).getBitmapPool();

        this.mCenterCrop=new CenterCrop(bitmapPool);
    }

    /**
     * 设置一个默认的 placehodler drawable
     * @param context
     * @param placeholdrResId
     */
    public ImageLoader(Context context,int placeholdrResId){
        this(context);
        this.mPlaceHolderResId=placeholdrResId;
    }
    public void loadImage(String url, ImageView imageView){
        loadImage(url,imageView,null);
    }
    public void loadImage(String url, ImageView imageView, RequestListener<String,Bitmap> requestListener){
        loadImage(url,imageView,requestListener,null,false);
    }
    public void loadImage(String url, ImageView imageView, RequestListener<String,Bitmap> requestListener, Drawable placeholderOverride, boolean crop){
        BitmapRequestBuilder request=beginImageLoad(url,requestListener,crop);
        if(placeholderOverride!=null){
            request.placeholder(placeholderOverride);
        }else if(mPlaceHolderResId!=-1){
            request.placeholder(mPlaceHolderResId);
        }
        request.into(imageView);

    }
    public BitmapRequestBuilder beginImageLoad(String url, RequestListener<String,Bitmap> requestListener,boolean crop){
        return  crop==true?this.glideModelRequest.load(url).listener(requestListener).transform(this.mCenterCrop):this.glideModelRequest.load(url).listener(requestListener);
    }

    /**
     * 加载Resouces下图片资源.
     * @param context
     * @param drawableResId
     * @param imageView
     */
    public  void loadImage(Context context, int drawableResId, ImageView imageView){
        Glide.with(context).load(drawableResId).into(imageView);
    }
    private static  class  CustomVariableWithImageLoader extends BaseGlideUrlLoader<String> {
        /**
         * 解析格式
         */
        private static final Pattern PATTERN = Pattern.compile("__w-((?:-?\\d+)+)__");

        public CustomVariableWithImageLoader(Context context) {
            super(context,urlCache);
        }
        @Override

        protected String getUrl(String model, int width, int height) {
            Matcher matcher=PATTERN.matcher(model);
            int bestBucket=0;
            if(matcher.find()){
                String[] found=matcher.group(1).split("-");
                for (String bucketStr : found) {
                    bestBucket = Integer.parseInt(bucketStr);
                    if (bestBucket >= width) {
                        // the best bucket is the first immediately bigger than the requested width
                        break;
                    }
                }
                if (bestBucket > 0) {
                    model =matcher.replaceFirst("w"+bestBucket);
                }
            }
            return model;
        }
    }

}
