package com.example.examenjunio2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class DB2 extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NOMBRE = "ExamenJunio";
    public static final String TABLE_PARTIDAS = "t_partidas";
    public static final String COLUMN_ID = "codigo";
    public static final String COLUMN_JUGADOR = "jugador";
    public static final String COLUMN_PUNTUACION = "puntuacion";

    private List<String> listaPartidas;

    public DB2(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PARTIDAS + "(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "jugador TEXT NOT NULL," +
                "puntuacion INTEGER NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE t_partidas");
        onCreate(db);
    }


    public List getPartidas(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PARTIDAS, null);
        listaPartidas = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String jugador = cursor.getString(cursor.getColumnIndex(COLUMN_JUGADOR));
                String puntos = cursor.getString(cursor.getColumnIndex(COLUMN_PUNTUACION));
                String partida = jugador + puntos;

                listaPartidas.add(partida);
            } while (cursor.moveToNext());
        }
        return listaPartidas;
    }

    public void insertarPartida(SQLiteDatabase db, String jugador, String puntuacion) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_JUGADOR, jugador);
        values.put(COLUMN_PUNTUACION, puntuacion);
        db.insert(TABLE_PARTIDAS, null, values);
    }
}