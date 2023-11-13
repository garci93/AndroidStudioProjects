package com.example.pizzeria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PizzaConfeccionar extends AppCompatActivity {
    private Button btnAtrasConfeccionar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_confeccionar);
        btnAtrasConfeccionar = findViewById(R.id.btnAtrasConfeccionar);

        btnAtrasConfeccionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}