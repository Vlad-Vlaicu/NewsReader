package com.vladv.newsreader.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vladv.newsreader.databinding.ArticleItemBinding;
import com.vladv.newsreader.listener.ArticleHandler;
import com.vladv.newsreader.model.ArticleItemViewModel;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private List<ArticleItemViewModel> articleModelList;
    private ArticleHandler handler;

    public ArticleAdapter() {
        this.articleModelList = new ArrayList<>();
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ArticleItemBinding binder = ArticleItemBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false);

        return new ArticleViewHolder(binder);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {

        holder.binding.setViewModel(articleModelList.get(position));
        //point all clicks to a single interface for all items
        holder.binding.setHandler(handler);
    }

    @Override
    public int getItemCount() {
        return articleModelList.size();
    }

    public void setItems(List<ArticleItemViewModel> items, ArticleHandler handler) {
        this.handler = handler;
        this.articleModelList = items;
        notifyDataSetChanged();
    }


    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        final ArticleItemBinding binding;

        public ArticleViewHolder(ArticleItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}