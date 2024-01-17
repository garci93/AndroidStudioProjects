package com.example.pizzeria;

import android.content.Context;
import android.content.SharedPreferences;

public class Usuario {
    private String nombre;
    private String passwd;
    private SharedPreferences archivoUsuarios;

    public Usuario(Context context, String nombre, String passwd) {
        register(context, nombre, passwd);
    }

    public boolean logIn(Context context, String nombre, String passwd) {
        archivoUsuarios = context.getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        String usuario = archivoUsuarios.getString("Usuario", "");
        String pass = archivoUsuarios.getString("Contraseña", "");

        return nombre.equals(usuario) && passwd.equals(pass);
    }

    public void register(Context context, String usuario, String passwd) {
        archivoUsuarios = context.getSharedPreferences("DatosUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = archivoUsuarios.edit();
        editor.putString("Usuario", usuario);
        editor.putString("Contraseña", passwd);
        editor.apply();
    }
}
