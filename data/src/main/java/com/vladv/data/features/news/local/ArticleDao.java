package com.vladv.data.features.news.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.vladv.data.features.news.local.model.ArticleEntity;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM articles")
    Flowable<List<ArticleEntity>> queryArticles();

    @Query("SELECT * FROM articles where id= :id")
    Single<ArticleEntity> findArticleById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertArticle(ArticleEntity article);

    @Query("DELETE FROM articles where id= :id")
    void deleteArticle(int id);

    @Query("SELECT * FROM articles where title= :title")
    Flowable<ArticleEntity> findArticleByTitle(String title);
}