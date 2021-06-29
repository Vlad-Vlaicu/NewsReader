package com.vladv.newsreader.model.mapper;

import com.vladv.data.features.news.model.Article;
import com.vladv.newsreader.model.ArticleItemViewModel;

public class ItemsToVmMapper {
    public static ArticleItemViewModel ArticleToVM(Article article){

        ArticleItemViewModel vm = new ArticleItemViewModel();
        vm.articleTitle.set(article.title);
        vm.articleContent.set(article.content);
        vm.imageURL.set(article.imageUrl);

        return  vm;
    }
}
