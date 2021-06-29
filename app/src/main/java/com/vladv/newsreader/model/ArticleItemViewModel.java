package com.vladv.newsreader.model;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModel;

public class ArticleItemViewModel extends ViewModel implements LifecycleObserver {
    public ObservableField<String> articleTitle;
    public  ObservableField<String> articleContent;
    public  ObservableField<String> imageURL;

    public ArticleItemViewModel(){
        articleTitle = new ObservableField<>();
        articleContent = new ObservableField<>();
        imageURL = new ObservableField<>();
    }

}
