package com.example.acdat_enero;

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
    private static final String DATABASE_NOMBRE = "RepasoACDAT";
    public static final String TABLE_PALABRAS = "t_nombre";
    private List<String> listaPalabras;

    public DB(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PALABRAS + "(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL)");

        insertarPalabra(db, "Silvia");
        insertarPalabra(db, "Ajuan");
        insertarPalabra(db, "Soto");
        insertarPalabra(db, "Airam");
        insertarPalabra(db, "Alvaro");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List getPalabras(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS, null);
        listaPalabras = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String pablabra = cursor.getString(cursor.getColumnIndex("nombre"));

                listaPalabras.add(pablabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }

    public String getPalabrasNueva(SQLiteDatabase db, String nombreBuscar) {
        Cursor cursor = db.rawQuery("SELECT nombre FROM " + TABLE_PALABRAS + " WHERE nombre=?", new String[]{nombreBuscar});
        String palabraBuscar = "";

        if (cursor.moveToFirst()) {
            do {
                String pablabra = cursor.getString(cursor.getColumnIndex("nombre"));

                palabraBuscar = pablabra ;
            } while (cursor.moveToNext());
        }
        return palabraBuscar;
    }

    public List getPalabrasN(SQLiteDatabase db, int cant) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " LIMIT " + cant, null);
        listaPalabras = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String pablabra = cursor.getString(cursor.getColumnIndex("nombre"));

                listaPalabras.add(pablabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }

    public void insertarPalabra(SQLiteDatabase db, String nombre) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        db.insert(TABLE_PALABRAS, null, values);
    }

    public void borrarPalabra(SQLiteDatabase db, String nombre) {
        db.delete(TABLE_PALABRAS, "nombre=?", new String[]{nombre});
    }

    public void modificarPalabra(SQLiteDatabase db, String nombre, String nombreNuevo) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombreNuevo);
        db.update(TABLE_PALABRAS, values, "nombre=?", new String[]{nombre});
    }
}
