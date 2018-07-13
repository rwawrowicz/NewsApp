package com.example.android.newsapp;

/**
 * Created by wawr1 on 04.07.2018.
 */

public class News {
    private String mTitle;
    private String mSection;
    private String mContributor;
    private String mDate;
    private String mUrl;

    public News(String title, String section, String contributor, String date, String url){
        mTitle = title;
        mSection = section;
        mContributor = contributor;
        mDate = date;
        mUrl = url;
    }

    public String getTitle(){
        return mTitle;
    }

    public String getSection(){
        return  mSection;
    }

    public String getContributor(){
        return mContributor;
    }

    public String getDate(){
        return  mDate;
    }

    public String getUrl(){
        return mUrl;
    }

}
