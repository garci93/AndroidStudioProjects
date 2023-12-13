package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txtUsuario;
    private TextView txtPassword;
    String user;
    String pass;
    private Button btnIniciarSesion;

    SQLiteDatabase dbReader;
    SQLiteDatabase dbWriter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtUsuario = findViewById(R.id.txtUsuario);
        txtPassword = findViewById(R.id.txtPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        dbReader = new DB(this).getReadableDatabase();
        dbWriter = new DB(this).getWritableDatabase();
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("color_fondo",0);
        int colorSeleccionado = sharedPreferences.getInt("colorFondoClave", R.color.lightRed);
        getWindow().getDecorView().setBackgroundColor(colorSeleccionado);
    }

    @Override
    public void onClick(View v) {
        user = txtUsuario.getText().toString();
        pass = txtPassword.getText().toString();
        switch (getResources().getResourceEntryName(v.getId())){
            case "btnIniciarSesion":
                Cursor cursor = dbReader.rawQuery("SELECT * from "+ DB.TABLE_CLIENTES,null);
                if (cursor.moveToFirst()){
                    do {
                        @SuppressLint("Range") String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                        @SuppressLint("Range") String contrasena = cursor.getString(cursor.getColumnIndex("contrasena"));
                        if (user.equals(nombre) && pass.equals(contrasena)){
                            Intent login = new Intent(this,Inicio.class);
                            startActivity(login);
                            break;
                        }
                    } while (cursor.moveToNext());
                }
                break;
        }
    }
}