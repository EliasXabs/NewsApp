package com.csc498.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

public class NewsInfo extends AppCompatActivity {

    Intent list;
    int id, title_index;
    SQLiteDatabase sql;
    Cursor c;
    TextView title, author, location, description, date;
    SpannableString title_underlined;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_news_info);

        list = getIntent();
        id = list.getIntExtra("news_id", -1);
        title = findViewById(R.id.i_title);
        author = findViewById(R.id.i_author);
        description = findViewById(R.id.i_description);
        date = findViewById(R.id.i_date);
        location = findViewById(R.id.i_location);

        sql = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);
        sql.execSQL("CREATE Table IF NOT EXISTS news (id INTEGER PRIMARY KEY,title VARCHAR, author VARCHAR, published_at VARCHAR, location VARCHAR, description VARCHAR)");
        c = sql.rawQuery("SELECT * FROM news WHERE id = ?", new String[] {Integer.toString(id)});
        c.moveToFirst();

        title_underlined = new SpannableString(c.getString(c.getColumnIndex("title")));
        title_underlined.setSpan(new UnderlineSpan(), 0, title_underlined.length(), 0);

        title.setText(title_underlined);
        author.setText("Author: "+c.getString(c.getColumnIndex("author")));
        description.setText(c.getString(c.getColumnIndex("description")));
        date.setText(c.getString(c.getColumnIndex("published_at")));
        location.setText(c.getString(c.getColumnIndex("location")));

    }
}
