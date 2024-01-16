package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.List;

public class Configurar extends BaseActivity implements View.OnClickListener {
    private RadioGroup radioDificultadGroup;
    private RadioButton radioFacil;
    private RadioButton radioMedio;
    private RadioButton radioDificil;

    private String dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        radioFacil = findViewById(R.id.radioFacil);
        radioMedio = findViewById(R.id.radioMedio);
        radioDificil = findViewById(R.id.radioDificil);
        setContentView(getLayoutRes());

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_configurar;
    }

    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnGuardar":
                if (radioFacil.isChecked()) {
                    saveDificult(3);
                } else if (radioMedio.isChecked()) {
                    saveDificult(5);
                } else if (radioDificil.isChecked()) {
                    saveDificult(10);

                }

                break;
            case "btnListar":
                Intent listar = new Intent(this, ListarActivity.class);
                startActivity(listar);
                break;
            case "btnVolverInicio":
                Intent volver = new Intent(this, MainActivity.class);
                startActivity(volver);
                break;

        }
    }
}