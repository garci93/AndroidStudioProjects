package com.example.ejerciciorecuadcat2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CRUD extends BaseActivity implements View.OnClickListener {

    ListView listView;

    List<String> listaPalabras;

    EditText txtNombre;

    EditText txtNombreNueva;

    private String selected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        listaPalabras = db.getPalabras(dbRead);
        listView = findViewById(R.id.listView);
        txtNombre = findViewById(R.id.txtNombre);
        txtNombreNueva = findViewById(R.id.txtNombreNuevo);
        setListView();

        listView.setOnItemClickListener((parent, view, position, id) -> {
            selected = (String) parent.getItemAtPosition(position);
            txtNombre.setText(selected);
            Toast.makeText(CRUD.this, "Se ha seleccionado:" + selected,Toast.LENGTH_SHORT).show();
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("¿Quieres borrar la palabra o modificarla?");
            alertDialog.setMessage("Selecciona una opcion");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Borrar", (dialog, which) -> {
                borrarPalabra(listView.getItemAtPosition(position).toString());
                listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras));
            });

            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar", (dialog, which) -> {
                dialog.cancel();

            });
            alertDialog.show();
        });
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_crud;
    }
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "buttonAñadir":
                db.insertarPalabra(dbWrite, txtNombre.getText().toString());
                listaPalabras = db.getPalabras(dbRead);
                setListView();
                break;
            case "buttonModificar":
                String palabraModificar = db.getPalabrasNueva(dbRead, selected);
                db.modificarPalabra(dbWrite, palabraModificar, txtNombreNueva.getText().toString());
                listaPalabras = db.getPalabras(dbRead);
                setListView();
                break;
            case "buttonVolver":
                Intent volver = new Intent(this, MainActivity.class);
                startActivity(volver);
                break;
        }
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabras) ;
        listView.setAdapter(adapter);
    }

    public void borrarPalabra(String nombre)
    {

        this.db.borrarPalabra(dbWrite,nombre);
        this.listaPalabras.remove(nombre);
        this.setListView();
    }

}