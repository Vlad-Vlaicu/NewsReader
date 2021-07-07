package com.vladv.newsreader.model.mapper;

import com.vladv.data.features.news.model.Article;
import com.vladv.newsreader.model.ArticleItemViewModel;

public class ItemsToVmMapper {
    public static ArticleItemViewModel ArticleToVM(Article article){

        ArticleItemViewModel vm = new ArticleItemViewModel();
        vm.articleTitle.set(article.getTitle());
        vm.articleContent.set(article.getContent());
        vm.articleDescription.set(article.getDescription());
        vm.imageURL.set(article.getImageUrl());

        return  vm;
    }
}
