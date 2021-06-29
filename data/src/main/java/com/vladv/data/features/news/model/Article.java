package com.vladv.data.features.news.model;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;

public class Article implements Serializable {
    @NonNull
    public  String imageUrl;
    @NonNull
    public  String title;
    @NonNull
    public  String content;
    @NonNull
    public  String description;

    public Article(@NonNull String imageUrl, @NonNull String title, @NonNull String content, @NonNull String description) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.content = content;
        this.description = description;
    }
    public Article(){};
}