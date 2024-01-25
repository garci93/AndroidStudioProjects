package com.example.requetepasoandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "RequetepasoAndroid";
    public static final String TABLE_PERSONAS = "t_personas";
    private List<String> listaPersonas;

    public DB(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PERSONAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        insertarPersona(db, "Santi");
        insertarPersona(db, "Jose");
        insertarPersona(db, "Ignacio");
        insertarPersona(db, "Airam");
        insertarPersona(db, "Alejandro");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List getPersonas(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PERSONAS, null);
        listaPersonas = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String persona = cursor.getString(cursor.getColumnIndex("nombre"));

                listaPersonas.add(persona);
            } while (cursor.moveToNext());
        }
        return listaPersonas;
    }


    private void insertarPersona(SQLiteDatabase db, String nombre) {
        ContentValues Cvalues = new ContentValues();
        Cvalues.put("nombre", nombre);
        db.insert(TABLE_PERSONAS, null, Cvalues);
    }

    private void modificarPersona(SQLiteDatabase db, String nombre) {
        ContentValues Cvalues = new ContentValues();
        Cvalues.put("nombre", nombre);
        db.update(TABLE_PERSONAS, Cvalues, "nombre = ?", new String[]{nombre});
    }


}