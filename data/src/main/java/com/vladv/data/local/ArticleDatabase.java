package com.vladv.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vladv.data.features.news.local.ArticleDao;
import com.vladv.data.features.news.local.model.ArticleEntity;

@Database(entities = {ArticleEntity.class},version = 3)
public abstract class ArticleDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
    private static final String DB_NAME = "articles_db";
    private static ArticleDatabase instance;

    public static synchronized ArticleDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ArticleDatabase.class,DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return  instance;
    }
}