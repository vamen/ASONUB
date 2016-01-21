package com.example.vivek.asonub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.parse.Parse;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setSupportActionBar(toolbar);
        Parse.enableLocalDatastore(this);

        Parse.initialize(this);


    }
}
