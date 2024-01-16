package com.example.examenandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnJugar;
    private Button btnPuntuaciones;
    private Button btnConfigurar;
    private Button btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        btnJugar = findViewById(R.id.btnJugar);
        btnPuntuaciones = findViewById(R.id.btnPuntuaciones);
        btnConfigurar = findViewById(R.id.btnConfigurar);
        btnSalir = findViewById(R.id.btnSalir);
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnJugar":
                Intent jugar = new Intent(this, Jugar.class);
                startActivity(jugar);
                break;
            case "btnPuntuaciones":
                Intent puntuaciones = new Intent(this, Puntuaciones.class);
                startActivity(puntuaciones);
                break;
            case "btnConfigurar":
                Intent configurar = new Intent(this, Configurar.class);
                startActivity(configurar);
                break;
            case "btnSalir":
                mostrarDialogoSalida();
                finish();
                break;

        }
    }
    private void mostrarDialogoSalida() {
        new AlertDialog.Builder(this)
                .setMessage("¿Deseas salir?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }
}