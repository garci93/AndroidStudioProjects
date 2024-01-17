package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class PuntuacionActivity extends AppCompatActivity {

    TextView textViewPMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);

        textViewPMax = findViewById(R.id.textViewPMax);

        SharedPreferences sharedPreferences = getSharedPreferences("preferencias", MODE_PRIVATE);

        String puntacionMax = sharedPreferences.getString("puntuacionMax", "0");

        if(puntacionMax != null){
            textViewPMax.setText(puntacionMax);

        }else{
            textViewPMax.setText("0");
        }

    }
}