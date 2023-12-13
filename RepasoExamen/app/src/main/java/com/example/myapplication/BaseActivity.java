package com.example.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        dbRead = new DB(this).getReadableDatabase();
        dbWrite = new DB(this).getWritableDatabase();
    }

    protected abstract int getLayoutRes();

}
