package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Calculadora extends AppCompatActivity implements View.OnClickListener {

    private Button btnIgual;
    private TextView txtExp;
    private String cadena;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        btnIgual = findViewById(R.id.btnIgual);

        btnIgual.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnIgual){
            try {
                ExpressionBuilder expressionBuilder = new ExpressionBuilder(cadena);
                Expression exp = expressionBuilder.build();
                double res = exp.evaluate();
                txtExp.setText(""+res);
                cadena = ""+res;
            } catch (Exception e) {
                txtExp.setText("ERR");
                cadena="";
            }
        }
    }
}
