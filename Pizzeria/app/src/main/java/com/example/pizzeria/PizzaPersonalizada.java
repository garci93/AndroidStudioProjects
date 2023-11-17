package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PizzaPersonalizada extends AppCompatActivity {
    private ArrayList<Pizza> pizzas;
    private Button btnAtrasPersonalizada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_personalizada);
        pizzas = Servicio.getInstance().getPizzas();
        btnAtrasPersonalizada = findViewById(R.id.btnAtrasPersonalizada);

        btnAtrasPersonalizada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPreferences = getSharedPreferences("color_fondo",0);
        int colorSeleccionado = sharedPreferences.getInt("colorFondoClave",R.color.lightRed);
        getWindow().getDecorView().setBackgroundColor(colorSeleccionado);
    }
}