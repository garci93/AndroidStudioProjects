package com.example.examenjunio;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Jugar extends BaseActivity implements View.OnClickListener {
    int preguntaActual;
    String dificultad;
    String jugadorActual;
    RadioGroup radioGroup;
    RadioButton resp1;
    RadioButton resp2;
    RadioButton resp3;
    RadioButton resp4;
    int respuesta;
    int num1;
    int num2;
    int oper;
    int puntos;
    TextView numPreguntaActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jugar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        puntos = 0;
        SharedPreferences sharedPreferences = getSharedPreferences("dificultad",0);
        dificultad = sharedPreferences.getString("dificultadClave", "baja");
        preguntaActual = getIntent().getIntExtra("preguntaActual", 1);
        numPreguntaActual = findViewById(R.id.numPreguntaActual);
        numPreguntaActual.setText("Pregunta " + String.valueOf(preguntaActual));
        generarPregunta();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);

                if (radioButton.getId() == resp1.getId())
                    respuesta = Integer.parseInt(resp1.getText().toString());
                else if (radioButton.getId() == resp2.getId())
                    respuesta = Integer.parseInt(resp2.getText().toString());
                else if (radioButton.getId() == resp3.getId())
                    respuesta = Integer.parseInt(resp3.getText().toString());
                else if (radioButton.getId() == resp4.getId())
                    respuesta = Integer.parseInt(resp4.getText().toString());
            }
        });
    }

    @Override
    protected int getLayoutRes() {
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnAvanzar":
                if (esCorrecta(radioGroup, respuesta)){
                    puntos++;
                }
                if (preguntaActual <= 3){
                    preguntaActual++;
                    numPreguntaActual.setText("Pregunta " + String.valueOf(preguntaActual));
                    generarPregunta();
                } else {
                    db.insertarPartida(dbWrite, jugadorActual, puntos);
                    finish();
                }
        }
    }

    public void generarPregunta(){
        num1 = (int) (Math.random() * 10);
        num2 = (int) (Math.random() * 10);
        oper = (int) (Math.random() * 2);
        if (dificultad.equals("media")){
            num1 += 10;
            num2 += 10;
        } else if (dificultad.equals("alta")){
            num1 += 20;
            num2 += 20;
        }
        TextView tituloPregunta = findViewById(R.id.tituloPregunta);
        if (oper == 0)
            tituloPregunta.setText(num1 + " + " + num2);
        else
            tituloPregunta.setText(num1 + " - " + num2);
    }

    public boolean esCorrecta(RadioGroup radioGroup, int checkedId){
        String respuestaElegida = radioGroup.findViewById(checkedId).toString();

        int correcta = 0;
        switch (oper){
            case 0:
                correcta = num1 + num2;
                break;
            case 1:
                correcta = num1 - num2;
                break;
        }
        return Integer.parseInt(respuestaElegida) == correcta;
    }
}