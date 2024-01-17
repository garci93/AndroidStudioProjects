package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfigurarActivity extends AppCompatActivity implements View.OnClickListener {


    int numeroPalabras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configurar);
        numeroPalabras = 15;
    }


    @Override
    public void onClick(View v) {


        if(v.getId() == R.id.buttonfacil){
            NumeroPalabras.setNumeroPalabra(10);
        }else if(v.getId() == R.id.buttonmedio){
            NumeroPalabras.setNumeroPalabra(15);
        } else if (v.getId() == R.id.buttonDificil) {

            NumeroPalabras.setNumeroPalabra(20);
        } else if (v.getId() == R.id.buttonSalirD) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            this.finish();
        }else if(v.getId() == R.id.buttonListarTodasPalabras){
            Intent intent = new Intent(this, ListaDePalabras.class);
            startActivity(intent);

            this.finish();
        }
    }
}