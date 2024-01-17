package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Switch;

public abstract class BaseActivity extends AppCompatActivity {
    protected int color1 = R.color.colorBackground1;
    protected int color2 = R.color.colorBackground2;
    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        dbRead = new DB(this).getReadableDatabase();
        dbWrite = new DB(this).getWritableDatabase();

        int colorFondo = getColorFondo();
        changeBackgroundColor(colorFondo);
    }

    protected abstract int getLayoutRes();
    protected boolean isBackgroundColor2() {
        return getBackgroundColorPreference();
    }

    private int getColorFondo() {
        return isBackgroundColor2() ? color2 : color1;
    }

    protected void changeBackgroundColor(int color) {
        View contentView = findViewById(android.R.id.content);
        if (contentView != null) {
            contentView.setBackgroundColor(getColor(color));
        }
    }

    void saveBackgroundColorPreference(boolean esColor2) {
        SharedPreferences sharedPreferences = getSharedPreferences("configuracion", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("esColor2", esColor2);
        editor.apply();
    }

    boolean getBackgroundColorPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("configuracion", MODE_PRIVATE);
        return sharedPreferences.getBoolean("esColor2", false);
    }

    void saveFavPreference(boolean fav) {
        SharedPreferences sharedPreferences = getSharedPreferences("configuracion", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("fav", fav);
        editor.apply();
    }

    boolean getFavPreference() {
        SharedPreferences sharedPreferences = getSharedPreferences("configuracion", MODE_PRIVATE);
        return sharedPreferences.getBoolean("fav", false);
    }
}
