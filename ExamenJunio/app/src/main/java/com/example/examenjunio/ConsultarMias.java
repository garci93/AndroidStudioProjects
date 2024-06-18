package com.example.examenjunio;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class ConsultarMias extends BaseActivity implements View.OnClickListener {

    ListView listView;
    List<String> listaPartidas;
    LinearLayout layoutLista;
    String jugadorActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        jugadorActual = getIntent().getStringExtra("jugadorActual");
        listaPartidas = db.getPartidasMias(dbRead,jugadorActual);
        layoutLista = findViewById(R.id.layoutLista);
        listView = findViewById(R.id.listView);
        setListView();
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_consultar_mias;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnVolverMias":
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
                AlertDialog detallesCita = new AlertDialog.Builder(ConsultarMias.this)
                        .setTitle(jugador)
                        .setCancelable(false)
                        .setNegativeButton("Cerrar", (dialog, which) -> dialog.cancel())
                        .create();
                detallesCita.show();
            }
        });
    }
}