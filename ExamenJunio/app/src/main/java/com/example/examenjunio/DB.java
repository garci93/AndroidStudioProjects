package com.example.examenjunio;

import android.app.Dialog;
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
    private static final String DATABASE_NOMBRE = "ExamenJunio";
    public static final String TABLE_PARTIDAS = "t_partidas";
    public static final String COLUMN_ID = "codigo";
    public static final String COLUMN_JUGADOR = "jugador";
    public static final String COLUMN_PUNTUACION = "puntuacion";

    private List<String> listaPartidas;

    public DB(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PARTIDAS + "(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "jugador TEXT NOT NULL," +
                "puntuacion INTEGER NOT NULL)");
        //partida de prueba, para comprobar que se inserta correctamente
        insertarPartida(db, "Jugador 1",3 );
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
                int puntos = cursor.getInt(cursor.getColumnIndex(COLUMN_PUNTUACION));
                String partida = jugador + puntos;

                listaPartidas.add(partida);
            } while (cursor.moveToNext());
        }
        return listaPartidas;
    }

    public List getPartidasMias(SQLiteDatabase db, String jugador) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PARTIDAS + " WHERE " + COLUMN_JUGADOR + " = '" + jugador + "'", null);
        listaPartidas = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String jugadorPartida = cursor.getString(cursor.getColumnIndex(COLUMN_JUGADOR));
                int puntos = cursor.getInt(cursor.getColumnIndex(COLUMN_PUNTUACION));
                String partida = jugadorPartida + puntos;

                listaPartidas.add(partida);
            } while (cursor.moveToNext());
        }
        return listaPartidas;
    }

    public String getNombreJugadorPartida(SQLiteDatabase db, String id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PARTIDAS + " WHERE " + COLUMN_ID + " = " + id + " ORDER BY " + COLUMN_PUNTUACION + " DESC", null);
        String jugador = "";
        if (cursor.moveToFirst()) {
            jugador = cursor.getString(cursor.getColumnIndex(COLUMN_JUGADOR));
        }
        return jugador;
    }

    public void insertarPartida(SQLiteDatabase db, String jugador, int puntuacion) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_JUGADOR, jugador);
        values.put(COLUMN_PUNTUACION, puntuacion);
        db.insert(TABLE_PARTIDAS, null, values);
    }
}
