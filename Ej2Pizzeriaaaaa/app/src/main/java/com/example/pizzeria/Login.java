package com.example.pizzeria;

import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends MainActivity implements View.OnClickListener {
    Usuario usuario;
    EditText nombre;
    EditText contraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        usuario = new Usuario(this,"AntonioJuan", "1");
        nombre = findViewById(R.id.usuario);
        contraseña = findViewById(R.id.passwd);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }
    @Override
    public void onClick(View v) {
        String botonId = this.getResources().getResourceEntryName(v.getId());
        String user = nombre.getText().toString();
        String pass = contraseña.getText().toString();
        if (botonId.equals("Login")){
           if (usuario.login(this, user, pass)) {
               Intent log = new Intent(this, Pagina_Principal.class);
               startActivity(log);
           }else {
               AlertDialog.Builder builder = new AlertDialog.Builder(this.getApplication());
               builder.setMessage("Usuario no encontrado, regístrese").setTitle("Error");

           }
        }
        if (botonId.equals("Registro")) {
            usuario.registro(this, user, pass);
            AlertDialog.Builder builder = new AlertDialog.Builder(this.getApplication());
            builder.setMessage("El registro se ha completado correctamente").setTitle("Registro");

        }
    }
}