package com.vladv.data.features.news.model;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;

public class Article {
    private final String imageUrl;
    private final String title;
    private final String content;
    private final String description;

    private Article(Builder builder) {
        this.content = builder.content;
        this.description = builder.description;
        this.imageUrl = builder.imageUrl;
        this.title = builder.title;
    }

    public static class Builder {
        private String imageUrl;
        private String title;
        private String content;
        private String description;

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withImgUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Article build() {
            Article article = new Article(this);
            return article;

        }
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDescription() {
        return description;
    }
}