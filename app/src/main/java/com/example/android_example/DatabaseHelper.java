package com.example.android_example;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String COLUMN_USER_NAME = "USER_NAME";
    public static final String COLUMN_USER_AGE = "USER_AGE";
    public static final String COLUMN_ACTIVE_USER = "ACTIVE_USER";
    public static final String COLUMN_ID = "ID";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "user.db", null, 1);
    }

    //creates data base and all tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table_statement = "CREATE TABLE " + USER_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_USER_NAME + " TEXT, " + COLUMN_USER_AGE + " INT, " + COLUMN_ACTIVE_USER + " BOOL)";

        sqLiteDatabase.execSQL(create_table_statement);
    }

    //called when database changes
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean addOne(UserModel user){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME, user.getName());
        cv.put(COLUMN_USER_AGE, user.getAge());
        cv.put(COLUMN_ACTIVE_USER, user.is_active());

        return sqLiteDatabase.insert(USER_TABLE, null, cv) != -1;
    }
}
