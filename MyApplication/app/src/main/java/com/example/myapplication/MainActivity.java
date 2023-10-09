package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btnSaludar = findViewById(R.id.btnSaludar);
        Button btnDespedir = findViewById(R.id.btnDespedir);

        btnSaludar.setOnClickListener(this);
        btnDespedir.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TextView txtMensaje = (TextView) findViewById(R.id.txtSaludo);
        int id = view.getId();
        if (id == R.id.btnSaludar) {
            txtMensaje.setText("Hola!!!");
        } else if (id == R.id.btnDespedir) {
            txtMensaje.setText("Adiós!!!");
        }
    }

//    public void cambiaMensaje(View view)
//    {
//        TextView txtMensaje = (TextView) findViewById(R.id.txtSaludo);
//        txtMensaje.setText("Esto funciona!!!");
//    }
}