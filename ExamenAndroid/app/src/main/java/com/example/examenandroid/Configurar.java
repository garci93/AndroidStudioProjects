package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Configurar extends AppCompatActivity {
    private RadioGroup radioDificultadGroup;
    private RadioButton radioDificultad1;
    private RadioButton radioDificultad2;
    private RadioButton radioDificultad3;
    private Button btnListar;
    private Button btnVolverInicio;

    private String dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);
        btnVolverInicio = findViewById(R.id.btnVolverInicio);
        btnListar = findViewById(R.id.btnListar);
        btnVolverInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Configurar.this, ListarActivity.class);
                startActivity(i);
            }
        });
    }
}