package com.csc498.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ImageView bg;
    TextView news;
    Intent newspage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newspage = new Intent(this, LoginScreen.class);
        news = (TextView) findViewById(R.id.Logo);
        bg = (ImageView) findViewById(R.id.splashbg);
        View decorView = getWindow().getDecorView();

        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Note: Try to search for a better way to delay animations *looks inefficient

        bg.animate().scaleY(2000).setDuration(2000).withEndAction(new Runnable() {
            @Override
            public void run() {
                news.animate().alpha(0).setDuration(2000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(newspage);
                    }
                });
            }
        }).start();

    }
}