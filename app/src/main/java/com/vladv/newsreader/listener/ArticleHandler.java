package com.vladv.newsreader.listener;

import com.vladv.newsreader.model.ArticleItemViewModel;

public interface ArticleHandler {
    void onItemSelected(ArticleItemViewModel item);

    void onDeleteItemSelected(ArticleItemViewModel item);
}
