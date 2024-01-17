package com.example.examenacdat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.List;

public class Configurar extends BaseActivity implements View.OnClickListener {

    RadioButton facil;
    RadioButton medio;
    RadioButton dificil;

    ListView listView;
    List listaPalabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        facil = findViewById(R.id.rFacil);
        medio = findViewById(R.id.rMedio);
        dificil = findViewById(R.id.rDificil);
        listView = findViewById(R.id.listViewP);
        listaPalabras = db.getPalabras(dbRead);
        setListView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_configurar;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btGuardar":
                if (facil.isChecked()) {
                    saveDificult(3);
                } else if (medio.isChecked()) {
                    saveDificult(5);
                } else if (dificil.isChecked()) {
                    saveDificult(10);
                }
                break;
            case "btVolver":
                Intent volver = new Intent(this, Inicio.class);
                startActivity(volver);
                break;
        }
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
    }
}