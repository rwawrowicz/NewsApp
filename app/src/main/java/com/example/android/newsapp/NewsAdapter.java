package com.example.android.newsapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Created by wawr1 on 10.07.2018.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    public NewsAdapter(Context context, List<News> news){
        super(context, 0, news);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.single_item, parent, false);

        }

        News news = getItem(position);
        TextView dateTextView = listItemView.findViewById(R.id.date);
        TextView contributorTextView = listItemView.findViewById(R.id.author);
        TextView titleTextView = listItemView.findViewById(R.id.title);
        String section = news.getSection() + "/ ";
        String title = news.getTitle();
        Spannable sectionColored = new SpannableString(section);
        sectionColored.setSpan(new ForegroundColorSpan(Color.RED), 0, sectionColored.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleTextView.setText(sectionColored);
        Spannable titleColored = new SpannableString(title);

        titleColored.setSpan(new ForegroundColorSpan(listItemView.getResources().getColor(R.color.primaryTextColor)), 0, titleColored.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        titleTextView.append(titleColored);

        String date = news.getDate();
        String contributor = news.getContributor();
        dateTextView.setText(dateFormatter(stringToDate(date)));
        contributorTextView.setText(contributor);

        return listItemView;
    }

    private Date stringToDate(String dateInString){
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        try {
            return formatter.parse(dateInString.replaceAll("Z$", "+0000"));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String dateFormatter(Date dateObject){
        SimpleDateFormat timeFormat = new SimpleDateFormat("LLL dd yyyy, HH:mm");
        return timeFormat.format(dateObject);

    }
}
