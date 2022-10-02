package com.csc498.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsPage extends AppCompatActivity {

    SharedPreferences shared;
    String s;
    TextView welcome_message;
    SQLiteDatabase sql;
    Cursor c;
    ArrayList<String> titles;
    ArrayList<Integer> ids;
    ListView news_list;
    ArrayAdapter<String> adapter;
    Intent description, post_news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_page);

        welcome_message = findViewById(R.id.Welcome);
        news_list = findViewById(R.id.newsList);

        shared = this.getSharedPreferences("com.csc498.newsapp", Context.MODE_PRIVATE);
        s = shared.getString("username", "");
        titles = new ArrayList<String>();
        ids = new ArrayList<Integer>();


        welcome_message.setText("Welcome back, " + s + " !");
        try {

            sql = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS news (id INTEGER PRIMARY KEY,title VARCHAR, author VARCHAR, published_at VARCHAR, location VARCHAR, description VARCHAR)");
            //sql.execSQL("INSERT INTO news(title, author, published_at, location, description) VALUES ('test2', 'Elias', '11am', 'Lebanon', 'testing loop')");

            c = sql.rawQuery("Select id, title from news ORDER BY id DESC", null);
            int id_i = c.getColumnIndex("id");
            int title_i = c.getColumnIndex("title");
            c.moveToFirst();
            titles.add(c.getString(title_i));
            ids.add(c.getInt(id_i));

            // found that its going to an infinite loop smh (using c.movetoNext())
            // found that moveToNext return a boolean value will be using it in the condition
            // somehow the cursor is going backwards????? cause of the query ig im dumb
            // starting at id 2 ending id 1

            while (c.moveToNext() != false) {
                titles.add(c.getString(title_i));
                ids.add(c.getInt(id_i));
            }

            adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, titles);
            news_list.setAdapter(adapter);

            news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    description = new Intent(getApplicationContext(), NewsInfo.class);
                    description.putExtra("news_id", ids.get(i));
                    startActivity(description);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void post(View view){
        post_news = new Intent(this, PostNews.class);
        startActivity(post_news);
    }
}