package com.vladv.newsreader.model.mapper;

import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.model.Article;

public class ItemsToEntityMapper {
    public static ArticleEntity apply(Article a){
        ArticleEntity entity = new ArticleEntity();
        entity.content = a.content;
        entity.description = a.description;
        entity.title = a.title;
        return entity;
    }
}
