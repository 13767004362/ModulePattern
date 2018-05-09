package com.xingen.commonlibrary.bean;

/**
 * Created by ${xinGen} on 2018/5/3 0003.
 * 博客：https://blog.csdn.net/hexingen
 */

public class Movie {
    public String year;
    private String title;
    private String id;
    private Images images;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static class Images{
        private String small;

        public String getSmall() {
            return small;
        }

        public void setSmall(String small) {
            this.small = small;
        }

        public String getLarge() {
            return large;
        }

        public void setLarge(String large) {
            this.large = large;
        }

        private String large;
    }
}
