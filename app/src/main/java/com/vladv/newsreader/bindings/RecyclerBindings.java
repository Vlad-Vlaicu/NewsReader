package com.vladv.newsreader.bindings;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vladv.newsreader.adapter.ArticleAdapter;
import com.vladv.newsreader.listener.ArticleHandler;
import com.vladv.newsreader.model.ArticleItemViewModel;

import java.util.List;

public class RecyclerBindings {

    @BindingAdapter({"items", "handler"})
    public static void addFeedItems(RecyclerView recyclerView, List<ArticleItemViewModel> articles, ArticleHandler handler) {
        ArticleAdapter taskAdapter = (ArticleAdapter) recyclerView.getAdapter();

        if (taskAdapter == null) {
            taskAdapter = new ArticleAdapter();
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(taskAdapter);
        }

        taskAdapter.setItems(articles, handler);
    }
}