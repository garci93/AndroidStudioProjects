package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class ConfirmarPedido extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar_pedido);
    }

    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("color_fondo",0);
        int colorSeleccionado = sharedPreferences.getInt("colorFondoClave",R.color.lightRed);
        getWindow().getDecorView().setBackgroundColor(colorSeleccionado);
    }
}