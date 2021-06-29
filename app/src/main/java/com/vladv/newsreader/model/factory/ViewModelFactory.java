package com.vladv.newsreader.model.factory;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.vladv.data.NewsRepository;
import com.vladv.newsreader.NewsFeedApplication;
import com.vladv.newsreader.model.ArticleReadViewModel;
import com.vladv.newsreader.model.MainViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ViewModelFactory(Application application) {
        this.application = application;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(ArticleReadViewModel.class)) {
            return (T) new ArticleReadViewModel();
        }

        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            NewsRepository repo = NewsFeedApplication.getRepoProvider().provideNewsRepository();
            return (T) new MainViewModel(application, repo);
        }



        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}