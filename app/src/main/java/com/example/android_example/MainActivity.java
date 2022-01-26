package com.example.android_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button btn_add, btn_view_all;
    EditText edit_name, edit_age;
    CheckBox switch_active;
    ListView list_view_user_list;
    ArrayAdapter userArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) { //code runs on app start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //relates to the activity_main.xml

        btn_add = findViewById(R.id.btn_add);
        btn_view_all = findViewById(R.id.btn_view_all);
        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        switch_active = findViewById(R.id.is_active);
        list_view_user_list = findViewById(R.id.lview_customer_list);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

        ShowUsersOnListView(databaseHelper);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel new_user;
                try {
                    new_user = new UserModel(-1, edit_name.getText().toString(), Integer.parseInt(edit_age.getText().toString()), switch_active.isChecked());
                    Toast.makeText(MainActivity.this, new_user.toString(), Toast.LENGTH_SHORT).show();
                }catch(Exception e){
                    new_user = new UserModel(-1, "error", 0, false);
                    Toast.makeText(MainActivity.this, "Error creating customer", Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                boolean success = databaseHelper.addOne(new_user);
                Toast.makeText(MainActivity.this, "Success = " + success, Toast.LENGTH_SHORT).show();
                ShowUsersOnListView(databaseHelper);
            }
        });

        btn_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                ShowUsersOnListView(databaseHelper);
                //Toast.makeText(MainActivity.this, all.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowUsersOnListView(DatabaseHelper databaseHelper) {
        userArrayAdapter = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, databaseHelper.getUsers());
        list_view_user_list.setAdapter(userArrayAdapter);
    }
}