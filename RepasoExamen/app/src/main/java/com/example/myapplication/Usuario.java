package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

public class Usuario {
    private String nombre;
    private String passwd;
    private SharedPreferences archivoUsuarios;

    public Usuario(Context context, String nombre, String passwd) {
        registro(context, nombre, passwd);
    }

    public void registro(Context context, String usuario, String passwd) {
        archivoUsuarios = context.getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = archivoUsuarios.edit();
        editor.putString("Usuario", usuario);
        editor.putString("Contraseña", passwd);
        editor.apply();
    }
}