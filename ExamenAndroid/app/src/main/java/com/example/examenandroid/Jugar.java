package com.example.examenandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Jugar extends BaseActivity implements View.OnClickListener {
    ListView listView;
    List<String> listaPalabras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        listView = findViewById(R.id.listView);

        listaPalabras = db.getPalabrasN(dbRead, dificultad);

        setListView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_jugar;
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
    }

    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnAtrasDesdeJugar":
                Intent atras = new Intent(this, MainActivity.class);
                startActivity(atras);
                break;
            case "btnSiguienteJugar":
                Intent siguiente = new Intent(this, Jugar2.class);
                startActivity(siguiente);
                break;

        }
    }
}