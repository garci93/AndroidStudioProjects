package com.example.requetepasoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        db = new DB(this);
        dbRead = new DB(this).getReadableDatabase();
        dbWrite = new DB(this).getWritableDatabase();
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
    }

    protected abstract int getLayoutRes();
}