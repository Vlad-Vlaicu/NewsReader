package com.vladv.newsreader.model;

import android.app.Application;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

import com.vladv.data.NewsRepository;
import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.model.Article;

import java.io.Serializable;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.schedulers.Schedulers;

public class ArticleReadViewModel extends ViewModel implements LifecycleObserver {

    public final ObservableField<String> articleTitle;
    public final ObservableField<String> articleContent;
    public PublishSubject<ArticleEventModel> events;
    private final NewsRepository repo;
    private Disposable disposable;

    public ArticleReadViewModel(NewsRepository repo) {
        super();
        this.repo = repo;
        articleTitle = new ObservableField<>();
        articleContent = new ObservableField<>();
    }

    public void initArticleItem(String title) {
        disposable = repo.getArticleByTitle(title).observeOn(Schedulers.io()).subscribe(
                this::onArticleReceived,
                this::onErrorReceived
        );


        this.events = PublishSubject.create();

    }

    private void onErrorReceived(Throwable throwable) {
        onReturnButtonClicked();
    }

    private void onArticleReceived(ArticleEntity article) {
        articleTitle.set(article.title);
        articleContent.set(article.content);
    }

    public void onReturnButtonClicked() {
        events.onNext(new ArticleEventModel(ArticleEventModel.EventType.SHOW_FEED));

    }


}