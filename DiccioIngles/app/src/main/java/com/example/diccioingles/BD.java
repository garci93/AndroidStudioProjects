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
    private static final int DATABASE_VERSION = 7;
    private static final String DATABASE_NOMBRE = "DiccioIngles.db";
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
                "incorrecta1 TEXT NOT NULL," +
                "incorrecta2 TEXT NOT NULL," +
                "incorrecta3 TEXT NOT NULL," +
                "sonido TEXT NOT NULL," +
                "fechaIntro DATE NOT NULL," +
                "fechaUltimo DATE," +
                "numAciertos INTEGER NOT NULL," +
                "PRIMARY KEY (espanol, ingles))");

        insertarPalabra(db, "granada","pomegranate","palabra","grenada","grapefruit","greenade","pomegranate.mp3");
        insertarPalabra(db, "granada","grenade","palabra","granade","greenery","gradient","grenade.mp3");
        insertarPalabra(db, "casa","house","palabra","case","horse","hog","house.mp3");
        insertarPalabra(db, "casa","home","palabra","hole","room","building","home.mp3");
        insertarPalabra(db, "pueblo","town","palabra","country","council","person","town.mp3");
        insertarPalabra(db, "ciudad","city","palabra","civilian","cuisine","cycle","city.mp3");
        insertarPalabra(db, "dar por sentado","take for granted","expresion","remain seated","take a seat","give for granted","take_for_granted.mp3");
        insertarPalabra(db, "sala de estar","living room","expresion","place to be","drawing room","bathroom","living_room.mp3");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE t_palabras");
        onCreate(db);
    }

    public List<String> getPalabras(SQLiteDatabase db) {
        List<String> listaPalabras = new ArrayList<>();


        String query = "SELECT espanol FROM " + TABLE_PALABRAS;
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                int colIndexEspanol = cursor.getColumnIndex("espanol");
                do {
                    String palabra = cursor.getString(colIndexEspanol);
                    listaPalabras.add(palabra);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
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

    public List<String> getPalabrasN(SQLiteDatabase db, int cant) {
        List<String> listaPalabras = new ArrayList<>();

        if (cant <= 0) {
            return listaPalabras;
        }

        String query = "SELECT espanol FROM " + TABLE_PALABRAS + " LIMIT " + cant;
        Cursor cursor = db.rawQuery(query, null);

        try {
            if (cursor.moveToFirst()) {
                int colIndexEspanol = cursor.getColumnIndex("espanol");
                do {
                    String palabra = cursor.getString(colIndexEspanol);
                    listaPalabras.add(palabra);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return listaPalabras;
    }

    public List getPreguntas(SQLiteDatabase db, int cant) {
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " LIMIT " + cant, null);
        listaPalabras = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                String palabra = cursor.getString(cursor.getColumnIndex("espanol"));
                String corr = cursor.getString(cursor.getColumnIndex("ingles"));
                String inc1 = cursor.getString(cursor.getColumnIndex("incorrecta1"));
                String inc2 = cursor.getString(cursor.getColumnIndex("incorrecta2"));
                String inc3 = cursor.getString(cursor.getColumnIndex("incorrecta3"));
                listaPalabras.add(corr);
                listaPalabras.add(palabra);
                listaPalabras.add(inc1);
                listaPalabras.add(inc2);
                listaPalabras.add(inc3);
            } while (cursor.moveToNext());
        }

        return listaPalabras;
    }

    public boolean insertarPalabra(SQLiteDatabase db, String espanol, String ingles, String tipo, String incorrecta1, String incorrecta2, String incorrecta3, String sonido) {
        ContentValues values = new ContentValues();
        if (db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " WHERE espanol=? AND ingles=?", new String[]{espanol, ingles}).getCount() == 0) {
            values.put("espanol", espanol);
            values.put("ingles", ingles);
            values.put("tipo", tipo);
            values.put("incorrecta1", incorrecta1);
            values.put("incorrecta2", incorrecta2);
            values.put("incorrecta3", incorrecta3);
            values.put("sonido", sonido);
            values.put("fechaIntro", "CURRENT_DATE()");
            values.put("fechaUltimo", "CURRENT_DATE()");
            values.put("numAciertos", 0);
            db.insert(TABLE_PALABRAS, null, values);
            return true;
        }
        return false;
    }

    public boolean modificarPalabra(SQLiteDatabase db, String espanolNuevo, String inglesNuevo, String espanolViejo, String inglesViejo, String incorrecta1, String incorrecta2, String incorrecta3) {
        ContentValues values = new ContentValues();
        if (db.rawQuery("SELECT * FROM " + TABLE_PALABRAS + " WHERE espanol=? AND ingles=?", new String[]{espanolNuevo, inglesNuevo}).getCount() == 0) {
            values.put("espanol", espanolNuevo);
            values.put("ingles", inglesNuevo);
            values.put("incorrecta1", incorrecta1);
            values.put("incorrecta2", incorrecta2);
            values.put("incorrecta3", incorrecta3);
            //juntar en una sola sentencia
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET espanol=? WHERE espanol=? AND ingles=?", new String[]{espanolNuevo, espanolViejo, inglesViejo});
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET ingles=? WHERE espanol=? AND ingles=?", new String[]{inglesNuevo, espanolViejo, inglesViejo});
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET incorrecta1=? WHERE espanol=? AND ingles=?", new String[]{incorrecta1, espanolNuevo, inglesNuevo});
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET incorrecta2=? WHERE espanol=? AND ingles=?", new String[]{incorrecta2, espanolNuevo, inglesNuevo});
            db.execSQL("UPDATE " + TABLE_PALABRAS + " SET incorrecta3=? WHERE espanol=? AND ingles=?", new String[]{incorrecta3, espanolNuevo, inglesNuevo});
            return true;
        }
        return false;
    }
}
