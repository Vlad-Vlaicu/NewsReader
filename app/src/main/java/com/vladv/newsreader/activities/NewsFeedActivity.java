package com.vladv.newsreader.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vladv.newsreader.fragments.FeedDisplayFragment;
import com.vladv.newsreader.R;

public class NewsFeedActivity extends AppCompatActivity {

    private final static String TAG = NewsFeedActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed_activity);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, FeedDisplayFragment.newInstance())
                    .commitNow();
        }



    }

}