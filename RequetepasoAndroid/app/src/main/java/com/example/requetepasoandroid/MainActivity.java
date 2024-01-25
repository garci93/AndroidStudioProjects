package com.example.requetepasoandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener{
    private Button btnCRUD;
    private Button btnConfigurar;
    private Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        btnCRUD = findViewById(R.id.btnCRUD);
        btnConfigurar = findViewById(R.id.btnConfigurar);
        btnSalir = findViewById(R.id.btnSalir);
    }

        @Override
        protected int getLayoutRes() {
            return R.layout.activity_main;
        }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnCRUD":
                Intent crud = new Intent(this, CRUD.class);
                startActivity(crud);
                break;
            case "btnConfigurar":
                Intent configurar = new Intent(this, Configurar.class);
                startActivity(configurar);
                break;
            case "btnSalir":
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

}