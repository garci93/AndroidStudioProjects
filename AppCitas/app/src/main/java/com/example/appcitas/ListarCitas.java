package com.example.appcitas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListarCitas extends BaseActivity implements View.OnClickListener {
    ListView listView;
    List<String> listaPalabras;
    LinearLayout layoutLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        listaPalabras = db.getCitas(dbRead);
        layoutLista = findViewById(R.id.layoutLista);
        listView = findViewById(R.id.listView);
        setListView();
    }



    @Override
    protected int getLayoutRes() {
        return R.layout.activity_listar_citas;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btnVolverListar":
                finish();
        }
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                String[] detalles = db.getDetallesCita(dbRead, String.valueOf(position + 1)).split("\\|");
                String autor = "Autor: " + detalles[1];
                String numVeces = "" + detalles[2] + " vistas";
                String valoracion = "Valoración: " + detalles[3];
                AlertDialog detallesCita = new AlertDialog.Builder(ListarCitas.this)
                        .setTitle(autor
                                + "\n" + numVeces + "     " + valoracion)
                        .setCancelable(false)
                        .setItems(new String[]{"Modificar", "Borrar"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        // Modificar
                                        Intent intent = new Intent(ListarCitas.this, Configuracion.class);
                                        intent.putExtra("cita", selectedItem);
                                        startActivity(intent);
                                        break;
                                    case 1:
                                        // Borrar
                                        db.borrarCita(dbWrite, position + 1);
                                        listaPalabras = db.getCitas(dbRead);
                                        setListView();
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel())
                        .create();
                detallesCita.show();
            }
        });
    }
}