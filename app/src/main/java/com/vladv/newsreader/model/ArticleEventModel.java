package com.vladv.newsreader.model;

import androidx.annotation.IntDef;

public class ArticleEventModel {
    @EventType
    public final int eventType;

    public final ArticleItemViewModel item;

    ArticleEventModel(@EventType int eventType, ArticleItemViewModel item) {
        this.eventType = eventType;
        this.item = item;
    }

    ArticleEventModel(@EventType int eventType) {
        this.eventType = eventType;
        this.item = null;
    }

    @IntDef({
            EventType.READ_ITEM,
            EventType.SHOW_FEED

    })
    public @interface EventType {
        int READ_ITEM = 1;
        int SHOW_FEED = 2;

    }

}