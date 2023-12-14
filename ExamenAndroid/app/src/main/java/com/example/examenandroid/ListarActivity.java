package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ListarActivity extends AppCompatActivity {
    private Button btnAtrasDesdeListar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        btnAtrasDesdeListar = findViewById(R.id.btnAtrasDesdeListar);
        btnAtrasDesdeListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}