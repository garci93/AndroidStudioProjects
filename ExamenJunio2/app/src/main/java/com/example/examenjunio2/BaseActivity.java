package com.example.examenjunio2;

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
    DB2 db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        db = new DB2(this);
        dbRead = new DB2(this).getReadableDatabase();
        dbWrite = new DB2(this).getWritableDatabase();
    }

    protected abstract int getLayoutRes();
}