package com.example.appcitas;

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

    private static final int DATABASE_VERSION = 8;
    private static final String DATABASE_NOMBRE = "RecupACDAT";
    public static final String TABLE_PALABRAS = "t_citas";
    public static final String COLUMN_ID = "codigo";
    public static final String COLUMN_CITA = "cita";
    public static final String COLUMN_AUTOR = "autor";
    public static final String COLUMN_NUM_VECES = "numVeces";
    public static final String COLUMN_VALORACION = "valoracion";

    private List<String> listaPalabras;

    public DB(@Nullable Context context) {
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_PALABRAS + "(" +
                "codigo INTEGER PRIMARY KEY AUTOINCREMENT," +
                "cita TEXT NOT NULL," +
                "autor TEXT NOT NULL," +
                "numVeces INTEGER NOT NULL," +
                "valoracion TEXT NOT NULL)");

        insertarCitaPredeterminado(db, "Sólo sé que no sé nada","Sócrates",0,"Buena");
        insertarCitaPredeterminado(db, "No estamos obligados a hacer arte ni historia, sino ganar dinero","Michael Eisner",0,"Mala");
        insertarCitaPredeterminado(db, "Hay dos cosas infinitas, el Universo y la estupidez humana","Albert Einstein",0,"Buena");
        insertarCitaPredeterminado(db, "Más vale la pena en el rostro que la mancha en el corazón","Miguel de Cervantes",0,"Buena");
        insertarCitaPredeterminado(db, "El valor de una idea radica en el uso de la misma","Thomas A. Edison",0,"Buena");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE t_citas");
        onCreate(db);
    }

    public String getDetallesCita(SQLiteDatabase db, String id) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " WHERE codigo=?", new String[]{id});
        String detalles = "";

        if (cursor.moveToFirst()) {
            do {
                String pablabra = cursor.getString(cursor.getColumnIndex(COLUMN_CITA));
                String autor = cursor.getString(cursor.getColumnIndex(COLUMN_AUTOR));
                String numVeces = cursor.getString(cursor.getColumnIndex(COLUMN_NUM_VECES));
                String valoracion = cursor.getString(cursor.getColumnIndex(COLUMN_VALORACION));

                detalles = pablabra + "|" + autor + "|" + numVeces + "|" + valoracion;
            } while (cursor.moveToNext());
        }
        return detalles;
    }

    public List getCitas(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS, null);
        listaPalabras = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                String pablabra = cursor.getString(cursor.getColumnIndex(COLUMN_CITA));

                listaPalabras.add(pablabra);
            } while (cursor.moveToNext());
        }
        return listaPalabras;
    }

    public String getCitasNueva(SQLiteDatabase db, String nombreBuscar) {
        Cursor cursor = db.rawQuery("SELECT cita FROM " + TABLE_PALABRAS + " WHERE cita=?", new String[]{nombreBuscar});
        String citaBuscar = "";

        if (cursor.moveToFirst()) {
            do {
                String cita = cursor.getString(cursor.getColumnIndex(COLUMN_CITA));

                citaBuscar = cita ;
            } while (cursor.moveToNext());
        }
        return citaBuscar;
    }

    public List getCitasN(SQLiteDatabase db, int cant) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " LIMIT " + cant, null);
        listaPalabras = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String cita = cursor.getString(cursor.getColumnIndex(COLUMN_CITA));

                listaPalabras.add(cita);
            } while (cursor.moveToNext());
        }

        return listaPalabras;
    }

    public void insertarCitaPredeterminado(SQLiteDatabase db, String cita, String autor, int numVeces, String valoracion) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CITA, cita);
        values.put(COLUMN_AUTOR, autor);
        values.put(COLUMN_NUM_VECES, numVeces);
        values.put(COLUMN_VALORACION, valoracion);
        db.insert(TABLE_PALABRAS, null, values);
    }

    public void modificarCita(SQLiteDatabase db, int idCita, String nuevaCita, String nuevoAutor, int nuevoNumVeces, String nuevaValoracion) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CITA, nuevaCita);
        values.put(COLUMN_AUTOR, nuevoAutor);
        values.put(COLUMN_NUM_VECES, nuevoNumVeces);
        values.put(COLUMN_VALORACION, nuevaValoracion);

        String whereClause = "codigo=?";
        String[] whereArgs = { String.valueOf(idCita) };

        db.update(TABLE_PALABRAS, values, whereClause, whereArgs);
    }

    public void borrarCita(SQLiteDatabase db, int idCita) {
        String whereClause = "codigo=?";
        String[] whereArgs = { String.valueOf(idCita) };

        db.delete(TABLE_PALABRAS, whereClause, whereArgs);
    }

    public void insertarCita(SQLiteDatabase db, String cita, String autor) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CITA, cita);
        values.put(COLUMN_AUTOR, autor);
        values.put(COLUMN_NUM_VECES, 0);
        values.put(COLUMN_VALORACION, "BUENA");
        db.insert(TABLE_PALABRAS, null, values);
    }

    public void borrarCitas(SQLiteDatabase db) {
        db.delete(TABLE_PALABRAS, "true", null);
        insertarCita(db, "Sólo sé que no sé nada","Sócrates");
        insertarCita(db, "Hay dos cosas infinitas, el Universo y la estupidez humana","Albert Einstein");
        insertarCita(db, "Más vale la pena en el rostro que la mancha en el corazón","Miguel de Cervantes");

    }
}
