package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PizzaRepetir extends AppCompatActivity {
    private Button btnAtrasRepetir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_repetir);
        btnAtrasRepetir = findViewById(R.id.btnAtrasRepetir);
        btnAtrasRepetir.setOnClickListener(new View.OnClickListener() {
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