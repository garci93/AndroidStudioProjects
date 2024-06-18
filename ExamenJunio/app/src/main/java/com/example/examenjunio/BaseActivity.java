package com.example.examenjunio;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public abstract class BaseActivity extends AppCompatActivity {

    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;
    DB db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        db = new DB(this);
        dbRead = new DB(this).getReadableDatabase();
        dbWrite = new DB(this).getWritableDatabase();
    }

    protected abstract int getLayoutRes();
}