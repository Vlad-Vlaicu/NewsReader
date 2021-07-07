package com.vladv.data;

import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.model.Article;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public interface NewsRepository {

    @NonNull
    Single<List<Article>> getNewsArticles();

    @NonNull
    void saveArticleItem(ArticleEntity articleEntity);

    Flowable<List<ArticleEntity>> getLocalArticlesList();

    void removeArticle(int id);

    void clearCache();

    Flowable<ArticleEntity> getArticleByTitle(String title);

}