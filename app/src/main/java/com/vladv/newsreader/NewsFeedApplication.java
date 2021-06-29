package com.vladv.newsreader;

import android.app.Application;

import com.vladv.data.di.RepoModule;

public class NewsFeedApplication extends Application {

    //move along, will be replaced with Dagger later
    private static RepoModule repoModule;


    @Override
    public void onCreate() {
        super.onCreate();
        this.repoModule = new RepoModule(this);


    }

    public static RepoModule getRepoProvider() {
        return repoModule;
    }
}
