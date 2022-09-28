package com.csc498.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class NewsPage extends AppCompatActivity {

    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        shared = this.getSharedPreferences("com.csc498.newsapp", Context.MODE_PRIVATE);
        String s = shared.getString("username", "");
    }
}