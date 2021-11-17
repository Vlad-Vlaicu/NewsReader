package com.vladv.newsreader.model;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;
import androidx.lifecycle.AndroidViewModel;

import com.vladv.data.NewsRepository;
import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.model.Article;
import com.vladv.newsreader.listener.ArticleHandler;
import com.vladv.newsreader.model.mapper.EntityToItemsMapper;
import com.vladv.newsreader.model.mapper.ItemsToEntityMapper;
import com.vladv.newsreader.model.mapper.ItemsToVmMapper;
import com.vladv.newsreader.reactive.SingleLiveEvent;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxjava3.subjects.PublishSubject;

public class FeedDisplayViewModel extends AndroidViewModel implements ArticleHandler {

    private static final String TAG = FeedDisplayViewModel.class.getName();
    private final static String LINK = "https://newsapi.org/";
    private final NewsRepository repo;
    public final ObservableBoolean isLoading;
    public final ObservableField<String> resultText;
    public final SingleLiveEvent<Throwable> error;
    public final SingleLiveEvent<String> openLink;
    public final ObservableBoolean isListEmpty;
    public final ObservableList<ArticleItemViewModel> items;
    private Disposable disposable;
    public PublishSubject<ArticleEventModel> events;

    public FeedDisplayViewModel(Application application, NewsRepository repo) {
        super(application);
        this.repo = repo;
        this.isLoading = new ObservableBoolean();
        this.resultText = new ObservableField<>();
        this.error = new SingleLiveEvent<>();
        this.openLink = new SingleLiveEvent<>();
        this.isListEmpty = new ObservableBoolean();
        isListEmpty.set(true);
        this.items = new ObservableArrayList<>();
        this.events =PublishSubject.create();


    }

    @SuppressLint("CheckResult")
    public void refreshData() {
        isLoading.set(true);
        repo.getNewsArticles()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onNewsArticlesReceived,
                        this::onNewsArticlesError
                );
    }

    private void onNewsArticlesReceived(@NonNull List<Article> articles) {
        isLoading.set(false);
        isListEmpty.set(false);
        for(Article a : articles) {
            ArticleItemViewModel articleVM = ItemsToVmMapper.ArticleToVM(a);
            items.add(articleVM);
            repo.saveArticleItem(ItemsToEntityMapper.apply(a));
        }


    }

    private void onNewsArticlesError(Throwable throwable) {
        isLoading.set(false);
        isListEmpty.set(true);


        disposable = repo.getLocalArticlesList()
                .subscribe(
                        this::localArticlesReceived,
                        this::localDatabaseEmpty
                );

    }

    private void localArticlesReceived(List<ArticleEntity> articles){
        isLoading.set(false);
        isListEmpty.set(false);
        for(ArticleEntity entity : articles){
            Article a = EntityToItemsMapper.apply(entity);
            ArticleItemViewModel articleVM = ItemsToVmMapper.ArticleToVM(a);

            items.add(articleVM);
        }
    }

    private void localDatabaseEmpty(Throwable throwable){
        isLoading.set(false);
        isListEmpty.set(true);
        error.setValue(throwable);

    }

    public void onPoweredBySelected() {
        openLink.setValue(LINK);
    }

    @Override
    public void onItemSelected(ArticleItemViewModel item) {
        events.onNext(new ArticleEventModel(ArticleEventModel.EventType.READ_ITEM,item));
    }

    @Override
    public void onDeleteItemSelected(ArticleItemViewModel item) {

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if(disposable != null) {
            disposable.dispose();
        }
    }
}