package com.example.ejerciciorecuadcat2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.List;

public class Configuracion extends BaseActivity implements View.OnClickListener {

    Spinner spinnerNombres;

    List<String> listaNombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        spinnerNombres = findViewById(R.id.spinnerNombres);

        listaNombres = db.getPalabras(dbRead);

        setSpinner();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_configuracion;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "buttonAtras":
                Intent atras = new Intent(this, MainActivity.class);
                startActivity(atras);
                break;
        }
    }

    public void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listaNombres);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNombres.setAdapter(adapter);
    }
}
