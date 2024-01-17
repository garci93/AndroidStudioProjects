package com.example.pizzeria;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.os.Process;

public class Principal extends BaseActivity implements View.OnClickListener {
    boolean esColor2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        OnBackPressedCallback atras = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                mostrarDialogoSalida();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, atras);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_principal;
    }
    @Override
    public void onClick(View v) {
        switch (v.getResources().getResourceEntryName(v.getId())) {
            case "pedir":
                Intent pedir = new Intent(this, Pedidos.class);
                startActivity(pedir);
                break;
            case "web":
                Intent web = new Intent(this, Web.class);
                startActivity(web);
                break;
            case "configuracion":
                Intent config = new Intent(this, Configuracion.class);
                startActivity(config);
                break;
            case "salir":
                mostrarDialogoSalida();
                break;
        }
    }
    private void mostrarDialogoSalida() {
        new AlertDialog.Builder(this)
            .setMessage("¿Seguro que deseas salir?")
            .setCancelable(false)
            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(getApplicationContext(), LogIn.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
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