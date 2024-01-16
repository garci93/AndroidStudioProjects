package com.example.examenandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DB extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "ExamenAndroid";
    public static final String TABLE_PALABRAS = "t_palabras";
    private List<String> listaPalabras;

    public DB(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PALABRAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "palabra TEXT NOT NULL," +
                "estado TEXT NOT NULL)");

        insertarPalabra(db, "belleza");
        insertarPalabra(db, "basket");
        insertarPalabra(db, "alarma");
        insertarPalabra(db, "sufrir");
        insertarPalabra(db, "café");
        insertarPalabra(db, "serenidad");
        insertarPalabra(db, "hojaldre");
        insertarPalabra(db, "normal");
        insertarPalabra(db, "código");
        insertarPalabra(db, "silla");
        insertarPalabra(db, "juego");
        insertarPalabra(db, "cama");
        insertarPalabra(db, "calle");
        insertarPalabra(db, "fresa");
        insertarPalabra(db, "cielo");
        insertarPalabra(db, "bastón");
        insertarPalabra(db, "castillo");
        insertarPalabra(db, "dedo");
        insertarPalabra(db, "efímero");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List getPalabras(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS, null);
        listaPalabras = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String palabra = cursor.getString(cursor.getColumnIndex("palabra"));

                listaPalabras.add(palabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }

    public List getPalabrasN(SQLiteDatabase db, int cant) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " LIMIT " + cant, null);
        listaPalabras = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String palabra = cursor.getString(cursor.getColumnIndex("palabra"));

                listaPalabras.add(palabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }


    private void insertarPalabra(SQLiteDatabase db, String palabra) {
        ContentValues Cvalues = new ContentValues();
        Cvalues.put("palabra", palabra);
        Cvalues.put("estado", "nueva");
        db.insert(TABLE_PALABRAS, null, Cvalues);
    }


}
