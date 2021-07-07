package com.vladv.data.features.news.local.model;

import android.os.AsyncTask;

import com.vladv.data.local.ArticleDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class ArticleLocalDataStore {

    private final ArticleDatabase articleDatabase;

    public ArticleLocalDataStore(ArticleDatabase database) {
        this.articleDatabase = database;
    }

    public Flowable<List<ArticleEntity>> getLocalArticlesList() {
        return articleDatabase.articleDao().queryArticles();
    }

    @NotNull
    public void saveArticleItem(final ArticleEntity articleEntity) {

        System.out.println("I am saving article with title " + articleEntity.title);
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... voids) {
                articleDatabase.articleDao().insertArticle(articleEntity);
                return null;
            }
        }.execute();

        System.out.println("I am done saving " + articleEntity.title);
    }

    public void deleteArticle(int id) {
        System.out.println("Delete item");
        new AsyncTask<Void, Void, Void>() {
            protected Void doInBackground(Void... voids) {
                articleDatabase.articleDao().deleteArticle(id);
                return null;
            }
        }.execute();
    }

    public void clearCache() {
        getLocalArticlesList().observeOn(Schedulers.io())
                .subscribe(
                        this::onArticlesReceived,
                        this::onArticlesError
                );
    }

    private void onArticlesError(Throwable throwable) {
        System.out.println("Error: " + throwable.getMessage());
    }

    private void onArticlesReceived(List<ArticleEntity> articleEntities) {
        for (ArticleEntity a : articleEntities) {
            deleteArticle(a.id);
        }
    }

    public Flowable<ArticleEntity> getArticleByTitle(String title) {

        return articleDatabase.articleDao().findArticleByTitle(title);
    }

}