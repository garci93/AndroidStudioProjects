package com.example.examenacdat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Responder extends BaseActivity implements View.OnClickListener {
    ArrayList<String> listaPalabrasDatos;
    List<String> listaPalabras;
    ArrayList<String> listaPalabrasRes;
    EditText palabraRes;
    TextView punt;
    ListView listView;
    int cont = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());

        Intent datos = getIntent();
        listaPalabrasDatos = datos.getStringArrayListExtra("lista");
        listaPalabras = db.getPalabras(dbRead);
        listaPalabrasRes = new ArrayList<>();
        palabraRes = findViewById(R.id.palabraRes);
        punt = findViewById(R.id.txtPunt);
        listView = findViewById(R.id.listViewP);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_responder;
    }

    public boolean compRes() {
        boolean res = false;
        for (String palabra : listaPalabrasDatos) {
            if (palabraRes.getText().toString().equals(palabra)) {
                res = true;
                break;
            } else {
                res = false;
            }
        }
        return res;
    }

    @Override
    public void onClick(View v) {
        switch (getResources().getResourceEntryName(v.getId())) {
            case "btComp":
                if(cont <= dificultad) {
                    if (compRes()) {
                        listaPalabrasRes.add(palabraRes.getText().toString());
                        cont++;
                        punt.setText("Puntuación: " + cont);
                        setListView();
                    }else {
                        listaPalabrasRes.add(palabraRes.getText().toString());
                        punt.setText("Puntuación: " + cont);
                        setListView();
                    }
                }else {
                    mostrarPuntuacion();
                }
                break;
            case "btSalir":
                finish();
                break;
        }
    }

    public void mostrarPuntuacion() {
        new AlertDialog.Builder(this)
            .setMessage("Tu puntuación es: " + punt.getText().toString())
            .setCancelable(false)
            .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
            .show();
    }

    public void setListView() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaPalabrasRes) ;
        listView.setAdapter(adapter);
    }
}