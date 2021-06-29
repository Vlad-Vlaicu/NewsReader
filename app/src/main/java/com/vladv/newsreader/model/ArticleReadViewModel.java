package com.vladv.newsreader.model;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

import com.vladv.data.features.news.model.Article;

import java.io.Serializable;

import io.reactivex.rxjava3.subjects.PublishSubject;

public class ArticleReadViewModel extends ViewModel implements LifecycleObserver {

    public ObservableField<String> articleTitle;
    public  ObservableField<String> articleContent;
    public PublishSubject<ArticleEventModel> events;

    public void initArticleItem(Serializable serializable) {
        Article article = (Article) serializable;
        articleTitle = new ObservableField<>();
        articleContent = new ObservableField<>();
        articleTitle.set(article.title);
        articleContent.set(article.content);
        this.events = PublishSubject.create();

    }

    public void onReturnButtonClicked(){
        events.onNext(new ArticleEventModel(ArticleEventModel.EventType.SHOW_FEED));

    }

}