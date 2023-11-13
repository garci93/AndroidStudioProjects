package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Inicio extends AppCompatActivity {

    private Button btnElegirPizza;
    private Button btnConfigurar;
    private Button btnSitioWeb;
    private Button btnSalir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);

        btnElegirPizza = findViewById(R.id.btnElegirPizza);
        btnConfigurar = findViewById(R.id.btnConfigurar);
        btnSitioWeb = findViewById(R.id.btnSitioWeb);
        btnSalir = findViewById(R.id.btnSalir);
        btnElegirPizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, ElegirPizza.class);
                startActivity(i);
            }
        });
        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Inicio.this, Configurar.class);
                startActivity(i);
            }
        });
        btnSitioWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.pizzeriadafrancesco.es/";
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(i);
            }
        });
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}