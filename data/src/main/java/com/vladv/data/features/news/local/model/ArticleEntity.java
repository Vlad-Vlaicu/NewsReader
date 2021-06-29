package com.vladv.data.features.news.local.model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "articles", indices = {@Index(value = {"title","description"},unique = true)})
public class ArticleEntity {
    @PrimaryKey(autoGenerate = true)
    @Nullable
    public Integer id;

    public String title;

    public String content;

    public String description;

}