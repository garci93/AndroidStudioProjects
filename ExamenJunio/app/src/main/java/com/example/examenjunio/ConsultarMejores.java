package com.example.examenjunio;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class ConsultarMejores extends BaseActivity implements View.OnClickListener {

    ListView listView;
    List<String> listaPartidas;
    LinearLayout layoutLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        listaPartidas = db.getPartidas(dbRead);
        layoutLista = findViewById(R.id.layoutLista);
        listView = findViewById(R.id.listView);
        setListView();
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_consultar_mejores;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnVolverMejores":
                finish();
        }
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPartidas) ;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = db.getNombreJugadorPartida(dbRead, String.valueOf(position + 1));
                String jugador = "Jugador: " + nombre;
                AlertDialog detallesCita = new AlertDialog.Builder(ConsultarMejores.this)
                        .setTitle(jugador)
                        .setCancelable(false)
                        .setNegativeButton("Cerrar", (dialog, which) -> dialog.cancel())
                        .create();
                detallesCita.show();
            }
        });
    }
}