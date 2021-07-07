package com.vladv.newsreader.fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vladv.newsreader.databinding.FeedDisplayFragmentBinding;
import com.vladv.newsreader.model.FeedDisplayViewModel;
import com.vladv.newsreader.model.factory.ViewModelFactory;
import com.vladv.newsreader.navigator.AlertNavigator;
import com.vladv.newsreader.navigator.ArticleNavigator;

import io.reactivex.rxjava3.disposables.Disposable;

public class FeedDisplayFragment extends Fragment {

    private final static String TAG = FeedDisplayFragment.class.getSimpleName();
    private FeedDisplayViewModel viewModel;
    private AlertNavigator alertNavigator;
    private Disposable disposable;
    private ArticleNavigator navigator;


    public static FeedDisplayFragment newInstance() {
        return new FeedDisplayFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        alertNavigator = new AlertNavigator(requireContext());

        viewModel = new ViewModelProvider(this, new ViewModelFactory(requireActivity().getApplication())).get(FeedDisplayViewModel.class);
        viewModel.error.observe(this, throwable -> alertNavigator.showErrorFor(throwable));
        viewModel.openLink.observe(this, link -> openLink(link));

        navigator = new ArticleNavigator(requireActivity().getSupportFragmentManager());

        disposable = viewModel.events.subscribe(
                ArticleEventModel -> navigator.onArticleEvent(ArticleEventModel),
                throwable -> Log.d(TAG, "")
        );
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        FeedDisplayFragmentBinding binding = FeedDisplayFragmentBinding.inflate(inflater, container, false);
        binding.setViewModel(viewModel);
        return binding.getRoot();
    }

    private void openLink(@NonNull String link) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(link));
        startActivity(i);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (disposable != null) {
            disposable.dispose();
        }
    }


}