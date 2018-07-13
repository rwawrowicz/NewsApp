package com.example.android.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final int LOADER_ID = 1;
    private TextView emptyTextView;
    NewsAdapter adapter;
    ListView newsListView;
    private static final String REQUEST_URL =
            "https://content.guardianapis.com/search?q=netflix&order-by=newest&page-size=20&show-tags=contributor&api-key=test";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        newsListView = findViewById(R.id.list);
        emptyTextView = findViewById(R.id.empty_view);
        newsListView.setEmptyView(emptyTextView);

        ConnectivityManager callback = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = callback.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if (isConnected) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(LOADER_ID, null, this);
        } else {
            ProgressBar progressBar = findViewById(R.id.loading_spinner);
            progressBar.setVisibility(View.GONE);
            TextView textView = findViewById(R.id.empty_view);
            textView.setText("No internet connection!");
        }

    }

    private void updateUI(final List<News> newsToShow){
        newsListView = findViewById(R.id.list);
        adapter = new NewsAdapter(this, newsToShow);

        newsListView.setAdapter(adapter);
        newsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Uri url = Uri.parse(newsToShow.get(i).getUrl());
                Intent urlIntent = new Intent(Intent.ACTION_VIEW, url);
                startActivity(urlIntent);
            }
        });
    }

    @Override
    public Loader<List<News>> onCreateLoader(int i, Bundle bundle) {
        return new NewsLoader(this, REQUEST_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> earthquakes) {
        if (earthquakes == null) {
            return;
        }
        emptyTextView.setText(R.string.nothing_to_show);
        ProgressBar progressBar = findViewById(R.id.loading_spinner);
        progressBar.setVisibility(View.GONE);
        updateUI(earthquakes);
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        adapter.clear();
    }


}
