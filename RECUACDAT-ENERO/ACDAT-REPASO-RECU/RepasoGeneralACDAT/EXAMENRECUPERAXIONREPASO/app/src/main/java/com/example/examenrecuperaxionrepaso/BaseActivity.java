package com.example.examenrecuperaxionrepaso;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

public abstract class BaseActivity extends AppCompatActivity {

    SQLiteDatabase dbRead;
    SQLiteDatabase dbWrite;
    DB db;
    int dificultad;
    protected int color1 = R.color.colorFondo1;
    protected int color2 = R.color.colorFondo2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        db = new DB(this);
        dbRead = new DB(this).getReadableDatabase();
        dbWrite = new DB(this).getWritableDatabase();
        dificultad = getDificult();

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

    public void saveDificult(int dificultad) {
        SharedPreferences sharedPreferences = getSharedPreferences("configuracion", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("dificultad", dificultad);
        editor.apply();
    }

    int getDificult() {
        SharedPreferences sharedPreferences = getSharedPreferences("configuracion", MODE_PRIVATE);
        return sharedPreferences.getInt("dificultad", 5);
    }


}