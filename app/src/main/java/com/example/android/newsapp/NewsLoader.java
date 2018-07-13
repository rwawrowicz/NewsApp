package com.example.android.newsapp;

import android.content.AsyncTaskLoader;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wawr1 on 09.07.2018.
 */

public class NewsLoader extends AsyncTaskLoader<List<News>> {

    String mUrl;


    public NewsLoader(Context  context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<News> loadInBackground() {
        if (mUrl == null){
            return null;
        }
        ArrayList<News> news = QueryUtils.processNews(mUrl);
        return news;
    }
}
