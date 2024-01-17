package com.example.examenrecuperaxionrepaso;

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
    private static final String DATABASE_NOMBRE = "Examen";
    public static final String TABLE_PALABRAS = "t_palabras";
    private List<String> listaPalabras;

    public DB(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PALABRAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "pablabra TEXT NOT NULL)");

        insertarPalabra(db, "Hola");
        insertarPalabra(db, "Coco");
        insertarPalabra(db, "Grito");
        insertarPalabra(db, "Casa");
        insertarPalabra(db, "Calle");
        insertarPalabra(db, "Cama");
        insertarPalabra(db, "Cocina");
        insertarPalabra(db, "Cocodrilo");
        insertarPalabra(db, "Cocina");
        insertarPalabra(db, "Mesa");
        insertarPalabra(db, "Silla");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List getPalabras(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS, null);
        listaPalabras = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String pablabra = cursor.getString(cursor.getColumnIndex("pablabra"));

                listaPalabras.add(pablabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }

    public List getPalabrasN(SQLiteDatabase db, int cant) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " LIMIT " + cant, null);
        listaPalabras = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String pablabra = cursor.getString(cursor.getColumnIndex("pablabra"));

                listaPalabras.add(pablabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }

    public void insertarPalabra(SQLiteDatabase db, String pablabra) {
        ContentValues values = new ContentValues();
        values.put("palabra", pablabra);
        db.insert(TABLE_PALABRAS, null, values);
    }

    public void borrarPalabra(SQLiteDatabase db, String palabra) {
        db.delete(TABLE_PALABRAS, "pablabra=?", new String[]{palabra});
    }
}
