package com.example.examenandroid;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.examenandroid.DAOPalabras.DAOPalabras;

public class ListaDePalabras extends AppCompatActivity {


    ListView listaPalabras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_de_palabras);

        listaPalabras = findViewById(R.id.listaDepalabras);

        DAOPalabras daoPalabras = new DAOPalabras(this);
        listaPalabras.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, daoPalabras.getListaDePalabras()));


        listaPalabras.setOnItemClickListener((parent, view, position, id) -> {
            AlertDialog alertDialog = new AlertDialog.Builder(ListaDePalabras.this).create();
            alertDialog.setTitle("¿Quieres borrar o modificar la palabra?");
            alertDialog.setMessage("Selecciona una opcion");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Borrar", (dialog, which) -> {
                daoPalabras.borrarPalabra(listaPalabras.getItemAtPosition(position).toString());
                listaPalabras.setAdapter(new ArrayAdapter<String>(ListaDePalabras.this, android.R.layout.simple_list_item_1, daoPalabras.getListaDePalabras()));
            });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Modificar", (dialog, which) -> {
                AlertDialog alertDialog1 = new AlertDialog.Builder(ListaDePalabras.this).create();
                alertDialog1.setTitle("Modificar palabra");
                alertDialog1.setMessage("Escribe la palabra");

                alertDialog1.setButton(AlertDialog.BUTTON_POSITIVE, "Modificar", (dialog1, which1) -> {
                    daoPalabras.modificarPalabra(listaPalabras.getItemAtPosition(position).toString(), alertDialog1.getButton(AlertDialog.BUTTON_NEUTRAL).getText().toString());
                    listaPalabras.setAdapter(new ArrayAdapter<String>(ListaDePalabras.this, android.R.layout.simple_list_item_1, daoPalabras.getListaDePalabras()));
                });

                alertDialog1.show();


            });

            alertDialog.show();
        });
    }
}