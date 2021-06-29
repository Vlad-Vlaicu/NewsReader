package com.vladv.data.di;

import android.app.Application;
import android.content.Context;

import com.vladv.data.NewsRepository;
import com.vladv.data.features.news.NewsRepositoryImpl;
import com.vladv.data.features.news.local.model.ArticleLocalDataStore;
import com.vladv.data.features.news.remote.NewsRemoteSource;
import com.vladv.data.local.ArticleDatabase;
import com.vladv.data.remote.HttpClientFactory;

import io.reactivex.annotations.NonNull;

public class RepoModule {
    @NonNull
    private Context context;
    @NonNull
    private HttpClientFactory httpClientFactory;

    private volatile ArticleDatabase database;

    public RepoModule(@NonNull Application application) {
        this.context = application.getApplicationContext();
        this.httpClientFactory = new HttpClientFactory();
        database = ArticleDatabase.getInstance(application);
    }

    ArticleLocalDataStore provideLocalDataStore(){
        return new ArticleLocalDataStore(database);
    }

    public NewsRepository provideNewsRepository() {
        return new NewsRepositoryImpl(provideNewsRemoteSource(),provideLocalDataStore(),
                this.context);
    }

    private NewsRemoteSource provideNewsRemoteSource() {
        return new NewsRemoteSource(httpClientFactory.getNewsApi());
    }
}
