package com.vladv.newsreader.model.mapper;

import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.model.Article;

public class ItemsToEntityMapper {
    public static ArticleEntity apply(Article a){
        ArticleEntity entity = new ArticleEntity();
        entity.content = a.getContent();
        entity.description = a.getDescription();
        entity.title = a.getTitle();
        return entity;
    }
}
