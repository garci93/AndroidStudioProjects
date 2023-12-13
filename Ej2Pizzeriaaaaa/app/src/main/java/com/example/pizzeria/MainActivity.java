package com.example.pizzeria;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pizzeria.DBHelper.DBHelper;

public abstract class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    protected SQLiteDatabase db;
    protected int color1 = R.color.colorFondo1;
    protected int color2 = R.color.colorFondo2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();

        int colorFondo = getColorFondo();
        changeBackgroundColor(colorFondo);
    }

    protected abstract int getLayoutRes();

    protected boolean isColorFondo2() {
        return getBackgroundColorPreference();
    }

    private int getColorFondo() {
        return isColorFondo2() ? color2 : color1;
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
