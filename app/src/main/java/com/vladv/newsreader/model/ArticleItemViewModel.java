package com.vladv.newsreader.model;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

public class ArticleItemViewModel extends ViewModel implements LifecycleObserver {
    public ObservableField<String> articleTitle;
    public  ObservableField<String> articleDescription;
    public  ObservableField<String> imageURL;
    public ObservableField<String> articleContent;

    public ArticleItemViewModel(){
        articleTitle = new ObservableField<>();
        articleDescription = new ObservableField<>();
        imageURL = new ObservableField<>();
        articleContent = new ObservableField<>();
    }

}

