package com.vladv.newsreader.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.vladv.newsreader.fragments.MainFragment;
import com.vladv.newsreader.R;
import com.vladv.newsreader.model.MainViewModel;
import com.vladv.newsreader.model.factory.ViewModelFactory;
import com.vladv.newsreader.navigator.ArticleNavigator;

import io.reactivex.rxjava3.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();
    private Disposable disposable;
    private ArticleNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        ViewModelFactory factory =new ViewModelFactory(this.getApplication());
        MainViewModel viewModel = factory.create(MainViewModel.class);


        navigator = new ArticleNavigator(getSupportFragmentManager());

        disposable = viewModel.events.subscribe(
                ArticleEventModel -> navigator.onArticleEvent(ArticleEventModel),
                throwable -> Log.d(TAG, "")
        );

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }



    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (disposable != null) {
            disposable.dispose();
        }
    }
}