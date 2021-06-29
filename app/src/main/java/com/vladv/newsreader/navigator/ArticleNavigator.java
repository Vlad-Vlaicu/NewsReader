package com.vladv.newsreader.navigator;

import android.os.Bundle;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.vladv.data.features.news.model.Article;
import com.vladv.newsreader.R;
import com.vladv.newsreader.fragments.ArticleReadFragment;
import com.vladv.newsreader.fragments.MainFragment;
import com.vladv.newsreader.model.ArticleEventModel;
import com.vladv.newsreader.model.ArticleItemViewModel;

import org.jetbrains.annotations.NotNull;

public class ArticleNavigator {
    private FragmentManager fragmentManager;

    public ArticleNavigator(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    public void onArticleEvent(ArticleEventModel eventModel){
        switch (eventModel.eventType){
            case ArticleEventModel.EventType.READ_ITEM:
                openReadArticleSceen(eventModel.item);
                break;
            case ArticleEventModel.EventType.SHOW_FEED:
                openNewsFeedScreen();
                break;
        }
    }

    private void openNewsFeedScreen() {
        MainFragment fragment = new MainFragment();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container,fragment, ArticleReadFragment.class.getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());

        transaction.commit();
    }

    private void openReadArticleSceen(@NotNull ArticleItemViewModel item) {
        ArticleReadFragment fragment = ArticleReadFragment.newInstance();
        Bundle bundle = new Bundle();
        Article article = new Article();
        article.title = item.articleTitle.get();
        article.content = item.articleContent.get();

        bundle.putSerializable(ArticleReadFragment.ARTICLE,article);
        fragment.setArguments(bundle);

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.container,fragment,ArticleReadFragment.class.getSimpleName());
        transaction.addToBackStack(fragment.getClass().getSimpleName());

        transaction.commit();

    }

}