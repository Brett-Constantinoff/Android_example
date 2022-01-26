package com.example.android_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //code runs on app start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //relates to the activity_main.xml
    }
}