package com.csc498.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PostNews extends AppCompatActivity {

    EditText in_title, in_author, in_location, in_date, in_description;
    String title, author, location, date, description;
    SQLiteDatabase sql;
    Intent news_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_news);

        in_title = findViewById(R.id.intitle);
        in_author = findViewById(R.id.inAuthor);
        in_location = findViewById(R.id.inLocation);
        in_date = findViewById(R.id.inDate);
        in_description = findViewById(R.id.inDescription);

        in_title.setHintTextColor(getResources().getColor(R.color.grey));
        in_author.setHintTextColor(getResources().getColor(R.color.grey));
        in_location.setHintTextColor(getResources().getColor(R.color.grey));
        in_date.setHintTextColor(getResources().getColor(R.color.grey));
        in_description.setHintTextColor(getResources().getColor(R.color.grey));

    }

    public void post(View view){

        title = in_title.getText().toString();
        author = in_author.getText().toString();
        date = in_date.getText().toString();
        location = in_location.getText().toString();
        description = in_description.getText().toString();



        if(!title.equals("") && !author.equals("")
        && !date.equals("")&& !location.equals("")
        && !description.equals("")){

            sql = this.openOrCreateDatabase("newsdb", MODE_PRIVATE, null);
            sql.execSQL("CREATE Table IF NOT EXISTS news (id INTEGER PRIMARY KEY,title VARCHAR, author VARCHAR," +
                    " published_at VARCHAR, location VARCHAR, description VARCHAR)");
            sql.execSQL("INSERT INTO news(title, author, published_at, location, description) " +
                    "VALUES (?, ?, ?, ?, ?)", new String[]{in_title.getText().toString(), in_author.getText().toString(),
                                                            in_date.getText().toString(), in_location.getText().toString(),
                                                            in_description.getText().toString()});

            news_list = new Intent(getApplicationContext(), NewsPage.class);
            Toast.makeText(this, "News posted!", Toast.LENGTH_SHORT).show();
            startActivity(news_list);
        }
        else if(title.equals("")){
            Toast.makeText(this, "Please enter a Title", Toast.LENGTH_SHORT).show();
            in_title.setHintTextColor(getResources().getColor(R.color.red));
        }
        else if(author.equals("")){
            Toast.makeText(this, "Please enter an Author", Toast.LENGTH_SHORT).show();
            in_title.setHintTextColor(getResources().getColor(R.color.grey));
            in_author.setHintTextColor(getResources().getColor(R.color.red));
        }
        else if(location.equals("")){
            Toast.makeText(this, "Please enter a Location", Toast.LENGTH_SHORT).show();
            in_author.setHintTextColor(getResources().getColor(R.color.grey));
            in_location.setHintTextColor(getResources().getColor(R.color.red));
        }
        else if(date.equals("")){
            Toast.makeText(this, "Please enter a Date", Toast.LENGTH_SHORT).show();
            in_location.setHintTextColor(getResources().getColor(R.color.grey));
            in_date.setHintTextColor(getResources().getColor(R.color.red));
        }
        else if(description.equals("")){
            Toast.makeText(this, "Please enter a Description", Toast.LENGTH_SHORT).show();
            in_date.setHintTextColor(getResources().getColor(R.color.grey));
            in_description.setHintTextColor(getResources().getColor(R.color.red));
        }

    }
}