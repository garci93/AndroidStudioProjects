package com.example.appcitas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ListarCitas extends BaseActivity implements View.OnClickListener {
    ListView listView;
    List<String> listaPalabras;
    private String txtNombre;
    private String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        listaPalabras = db.getCitas(dbRead);
        listView = findViewById(R.id.listView);
        setListView();
        /*listView.setOnItemClickListener((parent, view, position, id) -> {
            selected = (String) parent.getItemAtPosition(position);

            txtNombre.setText(selected);
            Toast.makeText(ListarCitas.this, "Se ha seleccionado:" + selected,Toast.LENGTH_SHORT).show();
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Información de la cita");
            alertDialog.setMessage("Selecciona una opcion");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Borrar", (dialog, which) -> {
                borrarPalabra(listView.getItemAtPosition(position).toString());
                listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras));
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", (dialog, which) -> {
                dialog.cancel();

            });
            alertDialog.show();
        });*/
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
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
    }
}