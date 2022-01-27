package com.example.android_example;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    Button btn_add, btn_view_all, search_btn;
    EditText edit_name, edit_age, search_user;
    CheckBox switch_active;
    ListView list_view_user_list;
    ArrayAdapter userArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //code runs on app start
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //relates to the activity_main.xml

        btn_add = findViewById(R.id.btn_add);
        btn_view_all = findViewById(R.id.btn_view_all);
        search_btn = findViewById(R.id.search_btn);
        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        switch_active = findViewById(R.id.is_active);
        list_view_user_list = findViewById(R.id.lview_customer_list);
        search_user = findViewById(R.id.search_user);
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

        ShowUsersOnListView(databaseHelper, databaseHelper.getUsers());

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
                ShowUsersOnListView(databaseHelper, databaseHelper.getUsers());
            }
        });

        btn_view_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                ShowUsersOnListView(databaseHelper, databaseHelper.getUsers());
            }
        });

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchedUser = search_user.getText().toString();
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                try {
                    ShowUsersOnListView(databaseHelper, databaseHelper.getOne(searchedUser));
                    Toast.makeText(MainActivity.this, "User Found", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "No User Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        list_view_user_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                UserModel userClicked = (UserModel) adapterView.getItemAtPosition(i);
                databaseHelper.deleteOne(userClicked);
                ShowUsersOnListView(databaseHelper, databaseHelper.getUsers());
                Toast.makeText(MainActivity.this, "Deleted " + userClicked.getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void ShowUsersOnListView(DatabaseHelper databaseHelper, List<UserModel> users) {
        userArrayAdapter = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, users);
        list_view_user_list.setAdapter(userArrayAdapter);
    }
}