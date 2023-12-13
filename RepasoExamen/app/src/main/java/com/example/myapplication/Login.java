package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends BaseActivity implements View.OnClickListener {
    String user;
    String pass;
    EditText usuario;
    EditText contrasena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        usuario = findViewById(R.id.txtUsuario);
        contrasena = findViewById(R.id.textContrasena);


    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        user = usuario.getText().toString();
        pass = contrasena.getText().toString();
        switch (getResources().getResourceEntryName(v.getId())) {
            case "buttonLogin":
                Cursor cursor = dbRead.rawQuery("SELECT * FROM " + DB.TABLE_CLIENTES, null);

                if (cursor.moveToFirst()) {
                    do {
                        String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                        String passDB = cursor.getString(cursor.getColumnIndex("contrasena"));

                        if (user.equals(nombre) && pass.equals(passDB)) {
                            Intent log = new Intent(this, Principal.class);
                            startActivity(log);
                            break;
                        }
                    } while (cursor.moveToNext());
                }
                break;
            case "buttonRegistro":
                ContentValues values = new ContentValues();
                values.put("nombre", user);
                values.put("contrasena", pass);

                dbWrite.insert(DB.TABLE_CLIENTES, null, values);
                break;
        }
    }
}