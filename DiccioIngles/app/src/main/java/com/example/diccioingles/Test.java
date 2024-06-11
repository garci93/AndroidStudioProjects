package com.example.diccioingles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class Test extends BaseActivity implements View.OnClickListener {
    int preguntaActual;
    List<String> resultado;
    TextView pregunta;
    RadioButton rb1;
    RadioButton rb2;
    RadioButton rb3;
    RadioButton rb4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        preguntaActual = 0;
        resultado = db.getPreguntas(dbRead,5);
        pregunta = findViewById(R.id.preguntaPalabra);
        rb1 = findViewById(R.id.radioOp1);
        rb2 = findViewById(R.id.radioOp2);
        rb3 = findViewById(R.id.radioOp3);
        rb4 = findViewById(R.id.radioOp4);
        //randomizar las respuestas
        pregunta.setText(resultado.get(0));
        Collections.shuffle(resultado);
        rb1.setText(resultado.get(0));
        rb2.setText(resultado.get(1));
        rb3.setText(resultado.get(2));
        rb4.setText(resultado.get(3));
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_test;
    }

    @Override
    public void onClick(View v) {

    }
}