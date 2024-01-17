package com.example.examenrecuperaxionrepaso;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class JugarActivity2 extends BaseActivity implements View.OnClickListener {
    ArrayList<String> listaPalabrasDatos;
    List<String> listaPalabras;
    ArrayList<String> listaPalabrasRes;
    EditText palabraRes;
    TextView punt;
    ListView listView;
    ProgressBar progressBar;

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
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(listaPalabras.size());
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_jugar2;
    }

    public boolean compRes() {
        boolean res = false;
        for (String palabra : listaPalabrasDatos) {
            if (palabraRes.getText().toString().equals(palabra)) {
                res = true;
                progressBar.setProgress(progressBar.getProgress() + 1);
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
            case "buttonComprobar":
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
                .setMessage("" + punt.getText().toString())
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