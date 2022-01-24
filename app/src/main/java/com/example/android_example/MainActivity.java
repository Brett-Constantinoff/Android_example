package com.example.android_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //code runs on app start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //relates to the activity_main.xml
    }

    public void onBtnClick(View view){
        TextView txt_first_name = findViewById(R.id.firstNameView); //R is the resources for the project
        TextView txt_last_name = findViewById(R.id.lastNameView);
        TextView txt_email = findViewById(R.id.emailView);
        EditText edit_first_name = findViewById(R.id.editFirstName);
        EditText edit_last_name = findViewById(R.id.editLastName);
        EditText edit_email = findViewById(R.id.editEmail);
        txt_first_name.setText(edit_first_name.getText().toString());
        txt_last_name.setText(edit_last_name.getText().toString());
        txt_email.setText(edit_email.getText().toString());
    }
}