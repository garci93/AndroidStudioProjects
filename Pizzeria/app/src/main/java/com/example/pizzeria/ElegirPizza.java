package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ElegirPizza extends AppCompatActivity {

    private Button btnPizzaPersonalizada;
    private Button btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elegir_pizza);
        btnPizzaPersonalizada = findViewById(R.id.btnPizzaPersonalizada);
        btnAtras = findViewById(R.id.btnAtrasElegirPizza);

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnPizzaPersonalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ElegirPizza.this, PizzaPersonalizada.class);
                startActivity(i);
            }
        });
    }
}