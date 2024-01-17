package com.example.examenandroid.BD;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BDPalabras extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "palabras_db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "TodasLasPalabras";
    private static final String COLUMN_PALABRA = "palabra";

    public BDPalabras(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_PALABRA + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public long insertarPalabra( String palabras) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PALABRA, palabras);

        return db.insert(TABLE_NAME, null, values);
    }

    public List<String> getPalabras(){
        List<String> palabrasList = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String palabra = cursor.getString(cursor.getColumnIndex(COLUMN_PALABRA));
                palabrasList.add(palabra);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return palabrasList;
        }

    }

