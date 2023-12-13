package com.example.myapplication;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Principal extends BaseActivity {
    private ListView listView;
    private TextView txtLista;

    private String cogerDatosSpinner, cogerDatosList;

    private Button aceptar;

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        aceptar = findViewById(R.id.buttonAceptar);
        listView = findViewById(R.id.listView);
        mostrarLista();
        spinner();

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cogerDatosSpinner = (String) spinner.getSelectedItem();
                finish();
            }
        });
        OnBackPressedCallback atras = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                irAtras();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, atras);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_principal;
    }

    public void mostrarLista() {
        ArrayList<String> listaElementos = new ArrayList<>();
        listaElementos.add("Santi");
        listaElementos.add("Airam");
        listaElementos.add("Ajuan");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaElementos) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                txtLista = view.findViewById(android.R.id.text1);

                txtLista.setTextSize(20);
                txtLista.setText(listaElementos.get(position));

                return view;
            }
        };

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cogerDatosList = listaElementos.get(position);
                finish();
            }
        });
    }

    public void OnClick(View v) {
        switch (v.getResources().getResourceEntryName(v.getId())) {
            case "buttonVolver":
                irAtras();
                break;
        }


    }

    private void irAtras() {
        new AlertDialog.Builder(this)
                .setMessage("¿Deseas salir?")
                .setCancelable(false)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void spinner(){
        ArrayList<String> listaSpinner = new ArrayList<>();
        listaSpinner.add("Prueba1");
        listaSpinner.add("Prueba2");
        listaSpinner.add("Prueba3");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaSpinner);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = findViewById(R.id.spinner);

        spinner.setAdapter(adapter);


    }
}