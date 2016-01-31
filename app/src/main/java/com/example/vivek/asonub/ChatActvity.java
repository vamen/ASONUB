package com.example.vivek.asonub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.parse.Parse;
import com.parse.ParseObject;

public class ChatActvity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Toolbar toolbar= (Toolbar) findViewById(R.id.chatActbar);
        setSupportActionBar(toolbar);

    }
}
