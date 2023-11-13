package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElegirPizza extends AppCompatActivity {

    private Button btnPizzaPersonalizada;
    private Button btnConfeccionar;
    private Button btnRepetirUltimo;
    private Button btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_pizza);
        btnPizzaPersonalizada = findViewById(R.id.btnPizzaPersonalizada);
        btnConfeccionar = findViewById(R.id.btnConfeccionar);
        btnRepetirUltimo = findViewById(R.id.btnRepetirUltimo);
        btnAtras = findViewById(R.id.btnAtrasElegirPizza);

        btnPizzaPersonalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ElegirPizza.this, PizzaPersonalizada.class);
                startActivity(i);
            }
        });
        btnConfeccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ElegirPizza.this, PizzaConfeccionar.class);
                startActivity(i);
            }
        });
        btnRepetirUltimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ElegirPizza.this, PizzaRepetir.class);
                startActivity(i);
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}