package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PizzaPersonalizada extends AppCompatActivity {
    private Servicio servicio = new Servicio();
    private ArrayList<Pizza> pizzas;
    private Button btnAtrasPersonalizada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_personalizada);
        pizzas = servicio.getPizzas();
        btnAtrasPersonalizada = findViewById(R.id.btnAtrasPersonalizada);

        btnAtrasPersonalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}