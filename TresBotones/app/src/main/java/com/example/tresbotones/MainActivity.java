package com.example.tresbotones;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.function.ToIntFunction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View view) {
        TextView txtCantidad = (TextView) findViewById(R.id.txtCantidad);
        txtCantidad.setText(Integer.toString(txtCantidad.getText()+1));
    }
}