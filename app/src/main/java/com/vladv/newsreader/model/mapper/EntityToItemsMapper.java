package com.vladv.newsreader.model.mapper;

import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.model.Article;

public class EntityToItemsMapper {
    public static Article apply(ArticleEntity entity){
        Article article = new Article.Builder()
                .withContent(entity.content)
                .withDescription(entity.description)
                .withTitle(entity.title)
                .build();
        return article;
    }
}
