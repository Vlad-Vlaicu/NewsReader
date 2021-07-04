package com.vladv.data.features.news.remote.mapper;

import com.vladv.data.features.news.model.Article;
import com.vladv.data.features.news.remote.model.ArticleDto;
import com.vladv.data.features.news.remote.model.ArticleListDto;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Function;

public class NewsDtoToNewsMapper implements Function<ArticleListDto, List<Article>> {

    @Override
    public List<Article> apply(ArticleListDto articleDtos) {
        List<Article> articles = new ArrayList<>();

        for (ArticleDto dto : articleDtos.articles) {
            Article article = new Article.Builder()
                    .withTitle(dto.title != null ? dto.title : "")
                    .withImgUrl(dto.urlToImage != null ? dto.urlToImage : "")
                    .withDescription(dto.description != null ? dto.description : "")
                    .withContent(dto.content != null ? dto.content : "")
                    .build();

            articles.add(article);
        }

        return articles;
    }

}
