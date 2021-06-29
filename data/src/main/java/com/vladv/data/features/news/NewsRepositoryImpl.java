package com.vladv.data.features.news;

import android.content.Context;

import com.vladv.data.NewsRepository;
import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.local.model.ArticleLocalDataStore;
import com.vladv.data.features.news.model.Article;
import com.vladv.data.features.news.remote.NewsRemoteSource;
import com.vladv.data.features.news.remote.mapper.NewsDtoToNewsMapper;
import com.vladv.data.local.ArticleDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;

public class NewsRepositoryImpl implements NewsRepository {

    private final NewsRemoteSource remoteSource;
    private ArticleLocalDataStore localDataStore;


    private ArticleDatabase articleDatabase;

    public NewsRepositoryImpl(NewsRemoteSource remoteSource,ArticleLocalDataStore localDataStore,
                              Context context) {
        this.remoteSource = remoteSource;
        this.localDataStore = localDataStore;
        articleDatabase = ArticleDatabase.getInstance(context);

    }

    @Override
    @NonNull
    public Single<List<Article>> getNewsArticles() {
        return remoteSource.getNewsArticles()
                .map(new NewsDtoToNewsMapper());
    }



    public Flowable<List<ArticleEntity>> getLocalArticlesList() {
        return localDataStore.getLocalArticlesList();
    }

    @Override
    public void deleteArticle(int id) {
        localDataStore.deleteArticle(id);
    }

    @NotNull
    public void saveArticleItem(final ArticleEntity articleEntity) {
        localDataStore.saveArticleItem(articleEntity);
    }

    public void freeDatabase(){
        localDataStore.freeDatabase();
    }
}