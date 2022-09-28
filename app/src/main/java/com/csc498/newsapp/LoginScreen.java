package com.csc498.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {

    EditText name;
    SharedPreferences shared;
    SharedPreferences.Editor editor;
    Intent newspage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        name = (EditText) findViewById(R.id.name);
        shared = this.getSharedPreferences("com.csc498.newsapp", Context.MODE_PRIVATE);
        editor = shared.edit();
        newspage = new Intent(this, NewsPage.class);

    }

    public void next(View v){

        editor.putString("username", name.getText().toString());
        editor.commit();
        startActivity(newspage);

    }
}