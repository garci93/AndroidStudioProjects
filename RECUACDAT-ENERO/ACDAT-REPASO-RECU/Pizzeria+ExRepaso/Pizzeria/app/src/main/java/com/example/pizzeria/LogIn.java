package com.example.pizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogIn extends BaseActivity implements View.OnClickListener {
    Usuario usuario;
    EditText nombre;
    EditText passwd;
    boolean esColor2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        //usuario = new Usuario(this,"Airam", "1234");
        nombre = findViewById(R.id.usuario);
        passwd = findViewById(R.id.passwd);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_log;
    }
    @Override
    public void onClick(View v) {
        String botonId = this.getResources().getResourceEntryName(v.getId());
        String user = nombre.getText().toString();
        String pass = passwd.getText().toString();
        if (botonId.equals("LogIn")){

            Cursor cursor = dbRead.rawQuery("SELECT * FROM " + DB.TABLE_CLIENTES, null);

            if (cursor.moveToFirst()) {
                do {
                    String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
                    String passDB = cursor.getString(cursor.getColumnIndex("pass"));

                    if (user.equals(nombre) && pass.equals(passDB)) {
                        Intent log = new Intent(this, Principal.class);
                        startActivity(log);
                        break;
                    }
                } while (cursor.moveToNext());

            }

           /*if (usuario.logIn(this, user, pass)) {
               Intent log = new Intent(this, Principal.class);
               startActivity(log);
           }else {
               AlertDialog.Builder builder = new AlertDialog.Builder(this.getApplication());
               builder.setMessage("Usuario no encontrado").setTitle("Error").show();

           }*/
        }
        if (botonId.equals("Register")) {
            ContentValues values = new ContentValues();
            values.put("nombre", user);
            values.put("pass", pass);

            dbWrite.insert(DB.TABLE_CLIENTES, null, values);

            //usuario.register(this, user, pass);
        }
    }
}