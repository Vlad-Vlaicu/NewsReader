package com.vladv.newsreader.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladv.newsreader.R;
import com.vladv.newsreader.databinding.ArticleReadFragmentBinding;
import com.vladv.newsreader.model.ArticleReadViewModel;
import com.vladv.newsreader.model.factory.ViewModelFactory;

public class ArticleReadFragment extends Fragment {

    private ArticleReadViewModel articleViewModel;
    public final static String ARTICLE = "ARTICLE";

    public static ArticleReadFragment newInstance() {
        return new ArticleReadFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelFactory factory = new ViewModelFactory(requireActivity().getApplication());
        articleViewModel = ViewModelProviders.of(this, factory).get(ArticleReadViewModel.class);

        if (getArguments() != null && getArguments().containsKey(ARTICLE)) {
            articleViewModel.initArticleItem(getArguments().getSerializable(ARTICLE));
        }


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



}