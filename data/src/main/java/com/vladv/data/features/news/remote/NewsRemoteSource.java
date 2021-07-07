package com.vladv.data.features.news.remote;

import com.vladv.data.features.news.remote.model.ArticleListDto;
import com.vladv.data.remote.NewsApi;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

public class NewsRemoteSource {

    //Don't copy this api key, use your own by registering here https://newsapi.org/register
    private static final String API_KEY = "e42ea2dd935b45d2beddfa4c1fe7080f";
    private static final String EN_LANGUAGE_FILTER = "en";
    @NonNull
    private final NewsApi newsApi;

    public NewsRemoteSource(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    public Single<ArticleListDto> getNewsArticles() {
        return newsApi.getNewsArticles(API_KEY, EN_LANGUAGE_FILTER)
                .subscribeOn(Schedulers.io());
    }

}