package com.example.diccioingles;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BD extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NOMBRE = "DiccioIngles";
    public static final String TABLE_PALABRAS = "t_palabras";
    public static List<String> listaPalabras;
    private String[] unaPalabra;

    public BD(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PALABRAS + "(" +
                "espanol INTEGER NOT NULL," +
                "ingles TEXT NOT NULL," +
                "tipo TEXT NOT NULL," +
                "sonido TEXT NOT NULL," +
                "fechaIntro DATE NOT NULL," +
                "fechaUltimo DATE," +
                "numAciertos INTEGER NOT NULL," +
                "PRIMARY KEY (espanol, ingles))");

        insertarPalabra(db, "granada","pomegranate","palabra","pomegranate.mp3");
        insertarPalabra(db, "granada","grenade","palabra","grenade.mp3");
        insertarPalabra(db, "casa","house","palabra","house.mp3");
        insertarPalabra(db, "casa","home","palabra","home.mp3");
        insertarPalabra(db, "pueblo","town","palabra","town.mp3");
        insertarPalabra(db, "ciudad","city","palabra","city.mp3");
        insertarPalabra(db, "dar por sentado","take for granted","expresion","take_for_granted.mp3");
        insertarPalabra(db, "sala de estar","living room","expresion","living_room.mp3");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE t_palabras");
        onCreate(db);
    }

    public List getPalabras(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS, null);
        listaPalabras = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String palabra = cursor.getString(cursor.getColumnIndex("espanol"));

                listaPalabras.add(palabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }

    public String[] getUnaPalabra(SQLiteDatabase db, String palabra) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS, null);
        unaPalabra = new String[4];
        if (cursor.moveToFirst()) {
            do {
                unaPalabra[0] = cursor.getString(cursor.getColumnIndex("espanol"));
                unaPalabra[1] = cursor.getString(cursor.getColumnIndex("ingles"));
                unaPalabra[2] = cursor.getString(cursor.getColumnIndex("numVeces"));
                unaPalabra[3] = cursor.getString(cursor.getColumnIndex("fecha"));
            } while (cursor.moveToNext());
        }
        return unaPalabra;
    }

    public List getPalabrasN(SQLiteDatabase db, int cant) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " LIMIT " + cant, null);
        listaPalabras = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String palabra = cursor.getString(cursor.getColumnIndex("espanol"));

                listaPalabras.add(palabra);
            } while (cursor.moveToNext());
        }

        return listaPalabras;
    }

    public boolean insertarPalabra(SQLiteDatabase db, String espanol, String ingles, String tipo, String sonido) {
        ContentValues values = new ContentValues();
        if (db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " WHERE espanol=? AND ingles=?", new String[]{espanol, ingles}).getCount() == 0) {
            values.put("espanol", espanol);
            values.put("ingles", ingles);
            values.put("tipo", tipo);
            values.put("sonido", sonido);
            values.put("fechaIntro", "CURRENT_DATE()");
            values.put("fechaUltimo", "CURRENT_DATE()");
            values.put("numAciertos", 0);
            db.insert(TABLE_PALABRAS, null, values);
            return true;
        }
        return false;
    }

    public boolean modificarPalabra(SQLiteDatabase db, String espanolNuevo, String inglesNuevo, String espanolViejo, String inglesViejo) {
        ContentValues values = new ContentValues();
        if (db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " WHERE espanol=? AND ingles=?", new String[]{espanolNuevo, inglesNuevo}).getCount() == 0) {
            values.put("espanol", espanolNuevo);
            values.put("ingles", inglesNuevo);
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET espanol=? WHERE espanol=? AND ingles=?", new String[]{espanolNuevo, espanolViejo, inglesViejo});
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET ingles=? WHERE espanol=? AND ingles=?", new String[]{inglesNuevo, espanolViejo, inglesViejo});
            return true;
        }
        return false;
    }
}
