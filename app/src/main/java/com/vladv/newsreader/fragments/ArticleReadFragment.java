package com.vladv.newsreader.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladv.data.NewsRepository;
import com.vladv.data.features.news.NewsRepositoryImpl;
import com.vladv.data.features.news.local.model.ArticleEntity;
import com.vladv.data.features.news.model.Article;
import com.vladv.newsreader.NewsFeedApplication;
import com.vladv.newsreader.R;
import com.vladv.newsreader.databinding.ArticleReadFragmentBinding;
import com.vladv.newsreader.model.ArticleReadViewModel;
import com.vladv.newsreader.model.factory.ViewModelFactory;
import com.vladv.newsreader.navigator.ArticleNavigator;

import io.reactivex.rxjava3.disposables.Disposable;

public class ArticleReadFragment extends Fragment {

    private final static String TAG = ArticleReadFragment.class.getSimpleName();
    private ArticleReadViewModel articleViewModel;
    public final static String ARTICLE = "ARTICLE";
    private Disposable disposable;

    private ArticleNavigator navigator;

    public static ArticleReadFragment newInstance() {
        return new ArticleReadFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelFactory factory = new ViewModelFactory(requireActivity().getApplication());
        articleViewModel = ViewModelProviders.of(this, factory).get(ArticleReadViewModel.class);

        if (getArguments() != null && getArguments().containsKey(ARTICLE)) {
            String articleTitle = getArguments().getString(ARTICLE);
            articleViewModel.initArticleItem(articleTitle);
        }

        navigator = new ArticleNavigator(requireActivity().getSupportFragmentManager());

        disposable = articleViewModel.events.subscribe(
                ArticleEventModel -> navigator.onArticleEvent(ArticleEventModel),
                throwable -> Log.d(TAG, "")
        );


        getLifecycle().addObserver(articleViewModel);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        ArticleReadFragmentBinding binding =ArticleReadFragmentBinding.inflate(inflater,
                container,false);

        binding.setViewModel(articleViewModel);
        return binding.getRoot();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (disposable != null) {
            disposable.dispose();
        }
    }



}