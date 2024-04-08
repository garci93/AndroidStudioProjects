package com.example.diccioingles;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public abstract class BaseActivity extends AppCompatActivity {

    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;
    BD db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        db = new BD(this);
        dbRead = new BD(this).getReadableDatabase();
        dbWrite = new BD(this).getWritableDatabase();
    }

    protected abstract int getLayoutRes();
}