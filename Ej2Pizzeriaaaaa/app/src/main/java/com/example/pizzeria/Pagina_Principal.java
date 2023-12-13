package com.example.pizzeria;

import androidx.activity.OnBackPressedCallback;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class  Pagina_Principal extends MainActivity implements View.OnClickListener {
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
            .setMessage("¿Deseas salir?")
            .setCancelable(false)
            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent config = new Intent(getApplicationContext(), Login.class);
                    startActivity(config);
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